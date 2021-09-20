package com.example.fgm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import java.util.List;
@Validated
@AllArgsConstructor
@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @NotNull
    private long id;

    @NotEmpty(message = "Goal can't be empty!")
    private String goal;

    @NotNull
    @Min(1)
    private long target_amt;

    public Item(long id, String goal, long target_amt) {
        this.id = id;
        this.goal = goal;
        this.target_amt = target_amt;
    }


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Micro_Savings> micro_savings2;

    public Item() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public long getTarget_amt() {
        return target_amt;
    }

    public void setTarget_amt(long target_amt) {
        this.target_amt = target_amt;
    }

    public List<Micro_Savings> getMicro_savings2() {
        return micro_savings2;
    }

    public void setMicro_savings2(List<Micro_Savings> micro_savings2) {
        this.micro_savings2 = micro_savings2;
    }
}
