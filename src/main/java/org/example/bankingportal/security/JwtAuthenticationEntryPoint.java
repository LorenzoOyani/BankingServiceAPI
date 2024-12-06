package org.example.bankingportal.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        AuthenticationException authenticationException = (AuthenticationException) request.getAttribute("exception");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        log.info("Authentication exception error message, {}", authenticationException.getMessage());

        Map<String, Object> dataOutput = new HashMap<>();

        String message = authenticationException.getMessage() != null ? authenticationException.getMessage() : null;
        dataOutput.put("message", message);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, dataOutput);
        out.flush();
        ;
    }
}
