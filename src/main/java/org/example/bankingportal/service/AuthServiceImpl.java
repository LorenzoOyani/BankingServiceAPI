//package org.example.bankingportal.service;
//
//import org.example.bankingportal.JwtServices.JwtTokenProviderService;
//import org.example.bankingportal.entities.User;
//import org.example.bankingportal.payload.TokenResponse;
//import org.example.bankingportal.payload.UserRegistrationRequest;
//import org.example.bankingportal.repository.UserRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//    }
//
//
//    @Override
//    public TokenResponse registerUser(UserRegistrationRequest requests) {
//        User user = User.builder()
//                .name(requests.getName())
//                .email(requests.getEmail())
//                .password(passwordEncoder.encode(requests.getPassword()))
//                .countryCode(requests.getCountryCode())
//                .phoneNumber(requests.getPhone())
//                .build();
//
//        userRepository.save(user);
//        return TokenResponse.builder().
//                token(token).
//                build();
//    }
//
//
//
//}
