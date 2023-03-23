package com.rudy.ryanto.springboot.jpa.repository;

import com.rudy.ryanto.springboot.jpa.domain.JenisFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisFilmRepository extends JpaRepository<JenisFilm,String> {
}
