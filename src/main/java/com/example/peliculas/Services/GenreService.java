package com.example.peliculas.Services;

import com.example.peliculas.models.Genre;
import com.example.peliculas.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository repository;
    public List<Genre> getAllGenres(){
        return repository.findAll();
    }
}
