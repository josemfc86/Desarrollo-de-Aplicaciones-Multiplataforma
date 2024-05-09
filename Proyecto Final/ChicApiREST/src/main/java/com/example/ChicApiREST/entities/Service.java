package com.example.ChicApiREST.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="service")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private float price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Service() {
    }

    public Service(Long id, String name, float price, Genre genre) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.genre = genre;
    }

     public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}

