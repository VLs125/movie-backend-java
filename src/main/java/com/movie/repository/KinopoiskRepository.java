package com.movie.repository;

import com.movie.model.Kinopoisk;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface KinopoiskRepository extends JpaRepository<Kinopoisk, UUID> {
}
