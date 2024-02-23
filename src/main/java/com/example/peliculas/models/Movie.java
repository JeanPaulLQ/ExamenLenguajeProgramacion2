package com.example.peliculas.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "name",nullable = false)
    public String name;
    @Column(name = "director",nullable = false)
    public String director;
    @Column(name = "release_date",nullable = true)
    public LocalDate releaseDate;
    @ManyToOne
    @JoinColumn(name = "idGenre",nullable = false)
    public Genre idGenre;

}
