package org.example.bankingportal.Util;


import lombok.AllArgsConstructor;
import org.example.bankingportal.entities.User;
import org.example.bankingportal.repositories.UserRepository;
import org.example.bankingportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Service
public abstract class BaseUserService {

    private final UserService userService;
    private final LoggedUser loggedUser;



    public User loggedUser(Map<String, Object> userObject){
        Map<String, Object> user = this.loggedUser.getLoggedInUsers();
        return userService.getUser(user);
    }
}
