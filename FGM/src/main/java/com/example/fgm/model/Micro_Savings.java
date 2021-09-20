package com.example.fgm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
@Validated
@Entity
@Table(name = "Micro_Savings")
public class Micro_Savings {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id2;

    /*@NotNull
    @Min(1)*/
    private long micros;
    private String micros_list;

    @ManyToOne
    @JsonBackReference
    private Item item;

    private int rem_target;

    public long getId2() {
        return id2;
    }

    public void setId2(long id2) {
        this.id2 = id2;
    }

    public long getMicros() {
        return micros;
    }

    public void setMicros(long micros) {
        this.micros = micros;
    }

    public String getMicros_list() {
        return micros_list;
    }

    public void setMicros_list(String micros_list) {
        this.micros_list = micros_list;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
