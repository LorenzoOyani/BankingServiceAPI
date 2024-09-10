package org.example.bankingportal.service;

import org.example.bankingportal.entities.User;
import org.example.bankingportal.payload.TokenResponse;
import org.example.bankingportal.payload.UserRegistrationRequest;
import org.example.bankingportal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public TokenResponse registerUser(UserRegistrationRequest requests) {
        User user = User.builder()
                .name(requests.getName())
                .email(requests.getEmail())
                .password(passwordEncoder.encode(requests.getPassword()))
                .countryCode(requests.getCountryCode())
                .phoneNumber(requests.getPhone())
                .build();

        userRepository.save(user);


        return null;
    }
}
