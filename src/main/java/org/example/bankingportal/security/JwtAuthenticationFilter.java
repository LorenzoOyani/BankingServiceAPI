package org.example.bankingportal.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
@Log4j2
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final AuthUserDetailService authUserDetailService;

    private final JwtAuthTokenProvider authTokenProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
            }
            String username = null;

            String bearerToken = null;

            if (Objects.nonNull(header) && header.startsWith("Bearer ")) {
                bearerToken = header.substring(7);

                username = authTokenProvider.extractUsername(bearerToken);

            }

            if (Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = authUserDetailService.loadUserByUsername(username);
                boolean isTokenValid = authTokenProvider.validateToken(bearerToken, userDetails);

                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContext context = (SecurityContext) SecurityContextHolder.createEmptyContext().getAuthentication();
                    context.setAuthentication(authentication);

                }
            }
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", e);
        } catch (UnsupportedJwtException |
                 BadCredentialsException |
                 MalformedJwtException e) {
            log.error("filter error {}", e.getMessage());
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response);

    }
}
