//package org.example.bankingportal.service;
//
//import org.example.bankingportal.payload.UserRegistrationRequest;
//import org.example.bankingportal.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class UserServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;;
//
//    @InjectMocks
//    private AuthServiceImpl userService;
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void registerUser_and_expect_success() {
//        UserRegistrationRequest registrationRequest =
//                UserRegistrationRequest.builder()
//                        .name("John")
//                        .email("john@example.com")
//                        .address("northampton")
//                        .password("password")
//                        .countryCode("23232")
//                        .phone("09123220303")
//                        .build();
//
//
//        Authentication mockAuthentication = new UsernamePasswordAuthenticationToken(
//                registrationRequest.getEmail(), registrationRequest.getPassword()
//        );
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mockAuthentication);
//
//
//
//
//
//    }
//}