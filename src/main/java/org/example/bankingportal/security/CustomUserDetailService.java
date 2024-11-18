package org.example.bankingportal.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bankingportal.entities.User;
import org.example.bankingportal.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = userRepository.findByUsername(username);
        if (users == null) {
            return null;
        }else{
            return  new CustomUserDetails(users);
        }
    }


    @Transactional
    public UserDetails loadUserById(Long id) {
        User users = userRepository.findById(id).orElse(null);
        if (users == null) {
            throw new UsernameNotFoundException("can't find user with id: " + id);
        }

        return  new CustomUserDetails(users);
    }


}
