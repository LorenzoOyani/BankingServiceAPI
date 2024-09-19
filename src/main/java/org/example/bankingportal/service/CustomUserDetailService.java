package org.example.bankingportal.service;

import jakarta.transaction.Transactional;
import org.example.bankingportal.entities.User;
import org.example.bankingportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = userRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(users); //returns an Impl of UserDetails interface.
    }


    @Transactional
    public UserDetails loadUserById(Long id) {
        User users = userRepository.findById(id).orElse(null);
        if (users == null) {
            throw new UsernameNotFoundException("can't find user with id: " + id);
        }

        return new CustomUserDetails(users);
    }

}
