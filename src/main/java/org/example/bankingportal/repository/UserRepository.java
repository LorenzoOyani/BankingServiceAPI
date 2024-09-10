package org.example.bankingportal.repository;

import org.example.bankingportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
