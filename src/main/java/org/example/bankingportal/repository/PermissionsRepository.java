package org.example.bankingportal.repository;

import org.example.bankingportal.entities.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

    Set<Permissions> findAllById(Long id);
}
