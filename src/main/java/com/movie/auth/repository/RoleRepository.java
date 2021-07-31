package com.movie.auth.repository;

import com.movie.auth.model.ERoles;
import com.movie.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERoles roles);
}
