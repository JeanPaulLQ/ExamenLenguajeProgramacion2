package com.example.peliculas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idGenre;
    @Column(name = "name")
    public String name;
}
