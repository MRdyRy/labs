package com.rudy.ryanto.common.domain;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodRecommend {
    private Long idFood;
    private String foodName;
    private Double price;

}
