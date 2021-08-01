package com.movie.kinopoisk.controller;


import com.movie.kinopoisk.model.Kinopoisk;
import com.movie.kinopoisk.service.KinopoiskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class KinopoiskController {

    private final KinopoiskService kinopoiskService;

    public KinopoiskController(KinopoiskService kinopoiskService) {
        this.kinopoiskService = kinopoiskService;
    }

    @PostMapping(value = "/movie")
    public ResponseEntity<?> add(@RequestBody Kinopoisk movie) {
        kinopoiskService.addMovie(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping(value = "/movies")
    public ResponseEntity<List<Kinopoisk>> add(@RequestBody List<Kinopoisk> movie) {
        kinopoiskService.addMovieList(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/movie")
    public ResponseEntity<?> getAllMovie(){
        final List<Kinopoisk> movies = kinopoiskService.getAllMovie();
        return movies!=null && !movies.isEmpty()
                ? new ResponseEntity<>(movies,HttpStatus.OK)
                : new ResponseEntity<>("Movie list is empty",HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/movie/{id}")
    public ResponseEntity<Kinopoisk> getMovieById(@PathVariable(name = "id")UUID id){
        final Kinopoisk movie = kinopoiskService.getMovieById(id);
        return movie!=null
                ? new ResponseEntity<>(movie,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/movie/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") UUID id, @RequestBody Kinopoisk movie) {
        final boolean updated = kinopoiskService.updateMovie(movie, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/movie/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") UUID id) {
        final boolean deleted = kinopoiskService.deleteMovie(id);

        return deleted
                ? new ResponseEntity<>("Movie ${id} was deleted",HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

