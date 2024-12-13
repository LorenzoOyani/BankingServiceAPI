package org.example.bankingportal.concurrency;

import io.jsonwebtoken.lang.Assert;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class InMemoryUserDetailsManagers implements UserDetailsManager {
    private final Map<String, MutableUserDetails> users = new ConcurrentHashMap<>();
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private AuthenticationManager authenticationManager;

    InMemoryUserDetailsManagers(UserDetails... userDetails) {
        UserDetails[] usersArr;
        usersArr = userDetails;
        int var3 = usersArr.length;
        for (int var4 = 0; var4 < var3; ++var4) {
            UserDetails user = usersArr[var4];
            this.createUser(user);
        }
    }

    InMemoryUserDetailsManagers(Collection<UserDetails> userDetails) {
        Iterator<UserDetails> var4 = userDetails.iterator();
        while (var4.hasNext()) {
            UserDetails user = var4.next();
            this.createUser(user);

        }
    }

    @Override
    public void createUser(UserDetails user) {
        Assert.isTrue(!(userExists(user.getUsername())), "User " + user.getUsername() + " already exists");

        this.users.put(user.getUsername().toLowerCase(), new MutableUser(user));
    }

    @Override
    public void updateUser(UserDetails user) {


    }

    @Override
    public void deleteUser(String username) {
        this.users.remove(username.toLowerCase());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AuthenticationCredentialsNotFoundException("authentication is null");

        } else {
            String username = authentication.getName();
            log.info("changing the password for user {}", username);
            if (this.authenticationManager != null) {
                log.info("Re-authenticating the user, with username {} ", username);
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
            } else {
                log.info("can't authenticate username with password.");
            }

            MutableUserDetails user = this.users.get(username.toLowerCase());
            Assert.state(user != null, "User " + username + " does not exist");
            user.setPassword(newPassword);

        }
    }

    public UserDetails updatePassword(UserDetails user, String newPassword) {
        String username = user.getUsername().toLowerCase();
        MutableUserDetails userDetails = this.users.get(username.toLowerCase());
        userDetails.setPassword(newPassword);
        return userDetails;

    }

    @Override
    public boolean userExists(String username) {
        return this.users.containsKey(username.toLowerCase());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = this.users.get(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " does not exist");
        } else {
            return new User(username, user.getPassword(), user.getAuthorities());
        }
    }
}
