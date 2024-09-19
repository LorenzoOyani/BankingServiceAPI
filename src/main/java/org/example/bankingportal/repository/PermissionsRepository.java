package org.example.bankingportal.repository;

import org.example.bankingportal.entities.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

    @Query("SELECT p from Permissions p WHERE p.id=:id")
    Set<Permissions> findAllById(Long id);
}
