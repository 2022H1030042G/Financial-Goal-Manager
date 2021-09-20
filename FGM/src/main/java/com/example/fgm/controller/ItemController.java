package com.example.fgm.controller;

import com.example.fgm.model.Item;
import com.example.fgm.repository.MicroSavingsRepository;
import com.example.fgm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private MicroSavingsRepository repo1;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // display list of Goals
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listItem", itemService.getAllItem());
        model.addAttribute("listSavings", repo1.findAll());
        return "index";
    }

    @GetMapping("/showNewItemForm")
    public String showNewItemForm(Model model) {
        // create model attribute to bind form data
        Item item = new Item();
        model.addAttribute("item", item);
        return "new_item";
    }

    //@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item, @Valid Item itemValid, Errors error) {
        // save item to database
        if(error.hasErrors())
            return "new_item";
        itemService.saveItem(item);
        return "redirect:/";
    }

    /*@PostMapping("/saveAllItem")
    public String saveAllItem(@ModelAttribute("item") List<Item> items) {
        // save item to database
        itemService.saveAllItem(items);
        return "redirect:/";
    }*/


    @GetMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable(value = "id") long id) {

        // call delete item method
        this.itemService.deleteItemById(id);
        return "redirect:/";
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get item from the service
        Item item = itemService.getItemById(id);

        // set item as a model attribute to pre-populate the form
        model.addAttribute("item", item);
        return "update_item";
    }
}
