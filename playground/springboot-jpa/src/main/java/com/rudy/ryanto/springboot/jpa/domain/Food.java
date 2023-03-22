package com.rudy.ryanto.springboot.jpa.domain;

import com.rudy.ryanto.common.domain.AuditTrail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity(name = "Food")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Food extends AuditTrail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFood;

    @Column(name = "FOOD_NAME")
    private String foodName;

    @Column(name = "price", precision = 2)
    private Double price;

    /**
     * 1 = true (delete)
     * 0 = false (active)
     */
    @Column(name = "STATUS_DELETED", length = 1)
    private String statusDeleted;

}
