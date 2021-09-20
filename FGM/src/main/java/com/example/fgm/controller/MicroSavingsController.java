package com.example.fgm.controller;

import com.example.fgm.model.InfoResponse;
import com.example.fgm.model.Item;
import com.example.fgm.model.Micro_Savings;
import com.example.fgm.repository.ItemRepository;
import com.example.fgm.repository.MicroSavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MicroSavingsController {
    @Autowired
    private MicroSavingsRepository microsRepo;
    @Autowired
    private ItemRepository itemRepo;

    @GetMapping("/ListMicroSavings")
    public String ListMicroSavings(Model model) {
        List<Micro_Savings> savingsList = microsRepo.findAll();
        model.addAttribute("savingsList", savingsList);
        return "list_MicroSavings";
    }

    @GetMapping("/showNewMicroSavingsForm")
    public String showNewMicroSavingsForm(Model model) {
        // create model attribute to bind form data
        List<Item> itemList = itemRepo.findAll();
        //List<InfoResponse> itemList = microsRepo.findSome();
        model.addAttribute("micro_savings", new Micro_Savings());
        model.addAttribute("itemList", itemList);

        //List<InfoResponse> infoResponses = microsRepo.getJoinInfo();
        // model.addAttribute("infoResponses", infoResponses);
        return "new_microSavings";
    }

    @PostMapping("/saveSavings")
    public String saveSavings(@ModelAttribute("micro_savings") Micro_Savings savings, Model model) {
        // save item to database
        /*if(error.hasErrors()){
            return "new_microSavings";
        }*/

        Micro_Savings save = microsRepo.save(savings);long id = save.getItem().getId();

        List<InfoResponse> sum_savings = microsRepo.getJoinInfo(id);

        System.out.println("SUM= " + sum_savings.get(0).getMicros());
        long sum = sum_savings.get(0).getMicros();
        //long id = sum_savings.get(0).getItem().getId();
        System.out.println("ID= " + sum_savings.get(0).getItem().getId());

        Long targetVal = microsRepo.getTargetVal(id);
        System.out.println("targetVal= " + targetVal);


        if(targetVal - sum < 0){
            //get the id, amount of that row which inserted with more Remaining target value.
            long greaterValueId = save.getId2();
            long amt = save.getMicros();
            microsRepo.deleteHigherValue(greaterValueId, amt);
            return "error";
        }
        else if(targetVal - sum == 0){
            List<Item> itemList = itemRepo.findAllNot(id);
            model.addAttribute("micro_savings", new Micro_Savings());
            model.addAttribute("itemList", itemList);
            return "redirect:/";

        }
        return "redirect:/";
    }

    @GetMapping("/getSum/{id}")
    @ResponseBody
    public List<InfoResponse> getJoinInfo(@PathVariable( value = "id") long id){
        return microsRepo.getJoinInfo(id);
    }

    @GetMapping("/getsome")
    @ResponseBody
    public List<InfoResponse> getSome(){
        return microsRepo.findSome();
    }
}