package com.rudy.ryanto.springboot.jpa.repository;

import com.rudy.ryanto.springboot.jpa.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
}
