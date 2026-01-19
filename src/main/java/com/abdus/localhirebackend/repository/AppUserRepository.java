package com.abdus.localhirebackend.repository;

import com.abdus.localhirebackend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
}
