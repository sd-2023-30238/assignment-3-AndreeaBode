package com.example.demo.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String descriere;
    @Column
    private String stres;
    @Column
    private String energie;
    @Column
    private String durata;
    @Column
    private LocalDate data;
    @Column
    @JsonProperty("tag")
    private String tag;

    public Dream() {}

    public Dream(String descriere, String stres, String energie, String durata, LocalDate data, String tag) {
        this.descriere = descriere;
        this.stres = stres;
        this.energie = energie;
        this.durata = durata;
        this.data = data;
        this.tag = tag;
    }

    public LocalDate getData() {
        return data;
    }

    public void setLocalDate(LocalDate data) {
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getStres() {
        return stres;
    }

    public void setStres(String stres) {
        this.stres = stres;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "Dream{" +
                "localDate=" + data +
                '}';
    }

}
