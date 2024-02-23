package com.example.peliculas.Services;

import com.example.peliculas.models.Movie;
import com.example.peliculas.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;
    public List<Movie> getMovieList(){
        return repository.findAll();
    }
    public void createMovie(Movie movie){
        repository.save(movie);
    }
    public void deleteMovie(Long id){
        repository.deleteById(id);
    }
    public Movie findMovieBy(Long id){
        return repository.findById(id).orElse(null);
    }
}
