package org.example.bankingportal.repository;

import org.example.bankingportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u FROM  User u WHERE u.name=:username")
    User findByUsername(@Param("username") String username);

}
