package com.example.fgm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InfoResponse {

    private long id;
    private long target_amt;

    private String goal;
    private long micros;
    private Item item;

    public InfoResponse(String goal) {
        this.goal = goal;
    }

    public InfoResponse(long micros) {
        this.micros = micros;
    }

    public InfoResponse(Item item) {
        this.item = item;
    }

    public InfoResponse(String goal, long micros, Item item) {
        this.goal = goal;
        this.micros = micros;
        this.item = item;
    }

    public InfoResponse(long id, String goal, long target_amt) {
        this.id = id;
        this.target_amt = target_amt;
        this.goal = goal;
    }
}