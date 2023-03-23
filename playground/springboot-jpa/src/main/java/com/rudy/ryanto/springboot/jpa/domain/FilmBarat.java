package com.rudy.ryanto.springboot.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "FILM_BARAT")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FilmBarat {
    @Id
    @Column(name = "KODE_FILM")
    private String kodeFilm;
    @Column(name = "NAMA_FILM")
    private String namaFilm;
    @Column(name = "JUMLAH_PENONTON")
    private Integer jumlahPenonton;
    @Column(name = "KODE_JENIS_FILM")
    private String kodeJenisFilm;
}
