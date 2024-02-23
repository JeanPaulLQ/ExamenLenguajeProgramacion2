package com.example.peliculas.controllers;

import com.example.peliculas.Services.GenreService;
import com.example.peliculas.Services.MovieService;
import com.example.peliculas.models.Genre;
import com.example.peliculas.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private GenreService genreService;
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/listMovies")
    public String listMovies(Model model){
        List<Movie> listMovies = movieService.getMovieList();
        model.addAttribute("listMovies",listMovies);
        return "listMovies";
    }
    @GetMapping("/registerMovie")
    public String register(Model model){
        List<Genre> list = genreService.getAllGenres();
        model.addAttribute("listGenre",list);
        return "registerMovie";
    }
    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model){
        Movie movie = movieService.findMovieBy(id);
        model.addAttribute("movie",movie);
        List<Genre> list = genreService.getAllGenres();
        model.addAttribute("listGenre",list);
        return "editMovie";
    }
    @PostMapping("/edit")
    public String updateMovie(@RequestParam("id") Long id,
                              @RequestParam("nameMovie") String name,
                              @RequestParam("directorsMovie") String directorsMovie,
                              @RequestParam("releaseDateMovie") String date,
                              @RequestParam("genreMovie") String genre,
                              Model model){
        Movie movie = movieService.findMovieBy(id);
        movie.name=name;
        movie.director=directorsMovie;
        movie.idGenre = new Genre();
        movie.idGenre.idGenre = Long.parseLong(genre);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date,format);
        movie.releaseDate = localDate;
        movieService.createMovie(movie);

        List<Movie> listMovies = movieService.getMovieList();
        model.addAttribute("listMovies",listMovies);
        return "redirect:/movie/listMovies";
    }
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id,Model model){
        movieService.deleteMovie(id);
        List<Movie> listMovies = movieService.getMovieList();
        model.addAttribute("listMovies",listMovies);
        return "listMovies";
    }
    @PostMapping("/registerMovie")
    public String createMovie(@RequestParam("nameMovie") String name,
                              @RequestParam("directorsMovie") String directorsMovie,
                              @RequestParam("releaseDateMovie") String date,
                              @RequestParam("genreMovie") String genre,
                              Model model) throws ParseException {
        Movie movie = new Movie();
        movie.name=name;
        movie.director=directorsMovie;
        movie.idGenre = new Genre();
        movie.idGenre.idGenre = Long.parseLong(genre);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(date,format);
        movie.releaseDate = fecha;


        movieService.createMovie(movie);
        List<Movie> listMovies = movieService.getMovieList();
        model.addAttribute("listMovies",listMovies);
        return "redirect:/movie/listMovies";

    }
}
