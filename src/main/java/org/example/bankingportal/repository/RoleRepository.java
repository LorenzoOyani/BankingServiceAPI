package org.example.bankingportal.repository;

import org.example.bankingportal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r from Role r WHERE r.id=:roleId")
    Optional<Role> findRoleById(Long roleId);
}
