package com.movie.service;

import com.movie.model.Kinopoisk;
import com.movie.repository.KinopoiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KinopoiskServiceImpl implements KinopoiskService{

    @Autowired
    private KinopoiskRepository kinopoiskRepository;

    @Override
    public void addMovie(Kinopoisk kinopoisk) {
        kinopoiskRepository.save(kinopoisk);
    }

    @Override
    public void addMovieList(List<Kinopoisk> kinopoisk) {
        kinopoiskRepository.saveAll(kinopoisk);
    }

    @Override
    public List<Kinopoisk> getAllMovie() {
        return kinopoiskRepository.findAll();
    }

    @Override
    public Kinopoisk getMovieById(UUID id) {
        return kinopoiskRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateMovie(Kinopoisk movie, UUID id) {
        if(kinopoiskRepository.existsById(id)){
            movie.setId(id);
            kinopoiskRepository.save(movie);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean deleteMovie(UUID id) {
        if(kinopoiskRepository.existsById(id)){
            kinopoiskRepository.deleteById(id);
            return true;
        }
        return false;

    }
}
