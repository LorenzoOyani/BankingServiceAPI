package org.example.bankingportal.service;

import org.example.bankingportal.entities.User;
import org.example.bankingportal.payload.UserRegistrationRequest;
import org.example.bankingportal.payload.UserResponse;

import java.util.Optional;

/**
 * Service interface for managing users in the banking portal.
 */
public interface UserService {

    /**
     * Creates a new user based on the provided registration request.
     *
     * @param userRequest the registration request containing user details
     * @return the created user
     */
    UserResponse createUser(UserRegistrationRequest userRequest);

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user
     * @return an Optional containing the user, if found
     */
    Optional<UserResponse> findUserByEmail(String email);

    /**
     * Authenticates a user using their email and password.
     *
     * @param email    the user's email
     * @param password the user's password
     * @return an Optional containing the authenticated user, if credentials are valid
     */
//    Optional<User> authenticateUser(String email, String password);

    /**
     * Updates the user details for a given user ID.
     *
     * @param id   the ID of the user to update
     * @param user the updated user details
     */
    void updateUser(long id, User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteUserById(long id);
}
