package org.example.bankingportal.Util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoggedInUser {

    private static String getLoggedUsers() {
        var users = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (users != null) {
            if (users instanceof UserDetails) {
                return ((UserDetails) users).getUsername();
            } else if (users instanceof String) {
                return (String) users.toString();
            }
        }
        return null;
    }
}
