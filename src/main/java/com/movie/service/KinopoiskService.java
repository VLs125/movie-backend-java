package com.movie.service;
import com.movie.model.Kinopoisk;
import java.util.List;
import java.util.UUID;

public interface KinopoiskService {
    void addMovie(Kinopoisk kinopoisk);
    void addMovieList(List<Kinopoisk> kinopoisk);

    List<Kinopoisk> getAllMovie();

    Kinopoisk getMovieById(UUID id);

    boolean updateMovie(Kinopoisk kinopoisk, UUID id);

    boolean deleteMovie(UUID id);
}
