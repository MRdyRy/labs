package com.rudy.ryanto.springboot.jpa.repository;

import com.rudy.ryanto.springboot.jpa.domain.FilmIndo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmIndoRepository extends JpaRepository<FilmIndo,String> {
}
