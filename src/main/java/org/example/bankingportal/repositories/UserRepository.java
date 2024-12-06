package org.example.bankingportal.repositories;

import org.example.bankingportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query(value = "SELECT u FROM  User u WHERE u.firstName= :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM User u WHERE u.email= :email")
    boolean existsUserByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email", nativeQuery = true)
    User findByEmail(String email);

    Optional<User> findUserByEmail(String email);


}
