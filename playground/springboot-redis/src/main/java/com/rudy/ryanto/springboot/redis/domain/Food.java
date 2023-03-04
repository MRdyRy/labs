package com.rudy.ryanto.springboot.redis.domain;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private String idFood;
    private String foodName;
    private Double price;
}
