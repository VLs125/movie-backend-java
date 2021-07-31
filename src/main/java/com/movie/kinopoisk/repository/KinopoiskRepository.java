package com.movie.kinopoisk.repository;

import com.movie.kinopoisk.model.Kinopoisk;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface KinopoiskRepository extends JpaRepository<Kinopoisk, UUID> {
}
