package com.rudy.ryanto.actuator.repository;

import com.rudy.ryanto.actuator.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
