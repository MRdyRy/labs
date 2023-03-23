package com.rudy.ryanto.springboot.jpa.repository;

import com.rudy.ryanto.springboot.jpa.domain.FilmBarat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmBaratRepository extends JpaRepository<FilmBarat,String> {
}
