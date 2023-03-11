package com.rudy.ryanto.springboot.amq.domain;

import lombok.Data;

@Data
public class Food {
    private String foodId;
    private String foodName;
    private double price;
}
