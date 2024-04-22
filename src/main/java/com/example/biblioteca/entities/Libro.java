package com.example.biblioteca.entities;

import com.example.biblioteca.enums.LibroDisponibilitaEnum;
import jakarta.persistence.*;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titolo;
    private String autore;
    private Integer annoPubblicazione;
    @Enumerated
    @Column(name = "disponibilta_libro_enum")
    private LibroDisponibilitaEnum disponibilitaEnum = LibroDisponibilitaEnum.DISPONIBILE;

    public Libro() {
    }

    public Libro(Long id, String titolo, String autore, Integer annoPubblicazione, LibroDisponibilitaEnum disponibilitaEnum) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.disponibilitaEnum = disponibilitaEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public LibroDisponibilitaEnum getDisponibilitaEnum() {
        return disponibilitaEnum;
    }

    public void setDisponibilitaEnum(LibroDisponibilitaEnum disponibilitaEnum) {
        this.disponibilitaEnum = disponibilitaEnum;
    }

}
