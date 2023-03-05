package com.rudy.ryanto.springboot.redis.domain;

import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Food implements Serializable {
    private String idFood;
    private String foodName;
    private Double price;
}
