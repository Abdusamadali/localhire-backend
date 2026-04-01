package com.abdus.localhirebackend.repository;

import com.abdus.localhirebackend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.OptionalInt;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    boolean existsByUsernameIgnoreCase(String username);

    Optional<AppUser> findByUsername(String username);
}
