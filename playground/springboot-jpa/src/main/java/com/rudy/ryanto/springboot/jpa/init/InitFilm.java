package com.rudy.ryanto.springboot.jpa.init;

import com.rudy.ryanto.springboot.jpa.domain.FilmBarat;
import com.rudy.ryanto.springboot.jpa.domain.FilmIndo;
import com.rudy.ryanto.springboot.jpa.domain.JenisFilm;
import com.rudy.ryanto.springboot.jpa.repository.FilmBaratRepository;
import com.rudy.ryanto.springboot.jpa.repository.FilmIndoRepository;
import com.rudy.ryanto.springboot.jpa.repository.JenisFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class InitFilm implements CommandLineRunner {

    @Autowired
    FilmIndoRepository filmIndoRepository;
    @Autowired
    FilmBaratRepository filmBaratRepository;
    @Autowired
    JenisFilmRepository jenisFilmRepository;


    @Override
    public void run(String... args) throws Exception {
        try{
            populateJenisFilm();
            populateFilmBarat();
            populateFilmIndo();

        }catch (Exception e){
            throw e;
        }
    }

    private void populateFilmIndo() {
        var data = List.of(
                FilmIndo.builder()
                        .kodeFilm("H01")
                        .namaFilm("DILAN 1990")
                        .jumlahPenonton(3000000)
                        .kodeJenisFilm("J05")
                        .build(),
                FilmIndo.builder()
                        .kodeFilm("H02")
                        .namaFilm("PENGABDI SETAN")
                        .jumlahPenonton(2500000)
                        .kodeJenisFilm("J02")
                        .build(),
                FilmIndo.builder()
                        .kodeFilm("H03")
                        .namaFilm("AADC 2")
                        .jumlahPenonton(5000000)
                        .kodeJenisFilm("J05")
                        .build(),
                FilmIndo.builder()
                        .kodeFilm("H04")
                        .namaFilm("THE RAID")
                        .jumlahPenonton(1750000)
                        .kodeJenisFilm("J01")
                        .build(),
                FilmIndo.builder()
                        .kodeFilm("H05")
                        .namaFilm("GUNDALA")
                        .jumlahPenonton(1500000)
                        .kodeJenisFilm("J01")
                        .build()
        );
        filmIndoRepository.saveAll(data);
    }

    private void populateFilmBarat() {
        var data = List.of(
                FilmBarat.builder()
                        .kodeFilm("F01")
                        .namaFilm("AVENGER")
                        .jumlahPenonton(4000000)
                        .kodeJenisFilm("J01")
                        .build(),
                FilmBarat.builder()
                        .kodeFilm("F02")
                        .namaFilm("CONJURING")
                        .jumlahPenonton(2500000)
                        .kodeJenisFilm("J02")
                        .build(),
                FilmBarat.builder()
                        .kodeFilm("F03")
                        .namaFilm("SPIDERMAN")
                        .jumlahPenonton(3500000)
                        .kodeJenisFilm("J01")
                        .build(),
                FilmBarat.builder()
                        .kodeFilm("F04")
                        .namaFilm("TITANIC")
                        .jumlahPenonton(1000000)
                        .kodeJenisFilm("J05")
                        .build(),
                FilmBarat.builder()
                        .kodeFilm("F05")
                        .namaFilm("TOY STORY")
                        .jumlahPenonton(1250000)
                        .kodeJenisFilm("J03")
                        .build()
        );
        filmBaratRepository.saveAll(data);
    }

    private void populateJenisFilm() {
        var data = List.of(
                JenisFilm.builder()
                        .kodeJenisFilm("J01")
                        .namaJenisFilm("AKSI")
                        .build(),
                JenisFilm.builder()
                        .kodeJenisFilm("J02")
                        .namaJenisFilm("HOROR")
                        .build(),
                JenisFilm.builder()
                        .kodeJenisFilm("J03")
                        .namaJenisFilm("ANIMASI")
                        .build(),
                JenisFilm.builder()
                        .kodeJenisFilm("J04")
                        .namaJenisFilm("KOMEDI")
                        .build(),
                JenisFilm.builder()
                        .kodeJenisFilm("J05")
                        .namaJenisFilm("DRAMA")
                        .build()
        );
        jenisFilmRepository.saveAll(data);
    }
}
