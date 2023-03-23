package com.rudy.ryanto.springboot.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "JENIS_FILM")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class JenisFilm {

    @Id
    @Column(name = "KODE_JENIS_FILM")
    private String kodeJenisFilm;
    @Column(name = "NAMA_JENIS_FILM")
    private String namaJenisFilm;
}