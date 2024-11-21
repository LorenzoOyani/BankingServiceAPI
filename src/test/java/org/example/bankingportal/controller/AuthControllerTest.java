//package org.example.bankingportal.controller;
//
//import org.example.bankingportal.payload.JwtTokenResponse;
//import org.example.bankingportal.payload.UserRegistrationRequest;
//import org.example.bankingportal.service.AuthService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//class AuthControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private AuthService authService;
//
//    @InjectMocks
//    private AuthController authController;
//
//    @Test
//    void registerUser() throws Exception {
//
//        JwtTokenResponse tokenResponse = JwtTokenResponse.builder()
//                .token("uyi2d34")
//                .refreshToken("jis444")
//                .build();
//
//        when(authService.registerUser(any(UserRegistrationRequest.class))).thenReturn(tokenResponse);
//
//        String registrationRequestJson = "{"
//                + "\"email\":\"test@example.com\","
//                + "\"name\":\"John Doe\","
//                + "\"password\":\"password123\","
//                + "\"address\":\"123 Main St\","
//                + "\"countryCode\":\"US\""
//                + "}";
//
//
//        mockMvc.perform(post("api/v1/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(registrationRequestJson))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.token").value("uyi2d34"))
//                .andExpect(jsonPath("$.refreshToken").value("jis444"));
//
//
//    }
//}