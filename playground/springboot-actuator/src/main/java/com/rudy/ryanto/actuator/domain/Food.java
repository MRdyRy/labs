package com.rudy.ryanto.actuator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idFood;
    @Column(name = "FOOD_NAME")
    private String foodName;
    @Column(name = "price", precision = 2)
    private double price;
}
