package org.example.bankingportal.service;

import org.example.bankingportal.entities.User;
import org.example.bankingportal.payload.UserRegistrationRequest;

public interface UserService {


    User createUser(UserRegistrationRequest user);

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

    void updateUser(long id, User user);

    void deleteUser(long id,User user);


}
