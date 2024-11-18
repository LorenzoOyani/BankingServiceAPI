package org.example.bankingportal.security;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;


@Slf4j
    @NonNullApi
    public class AuthenticationLoginFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            var httpRequest = request;

            String requestId = httpRequest.getHeader("Request-id");
            if(requestId != null && !Objects.equals(requestId, request.getHeader("Request-id"))) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.addHeader(" mediaType", "application/json");
                response.getWriter().write("error");

            }


            log.info("successfully authenticate user with request-id {}", requestId);

            filterChain .doFilter(httpRequest, response); //fwd to next chain
        }


    }

