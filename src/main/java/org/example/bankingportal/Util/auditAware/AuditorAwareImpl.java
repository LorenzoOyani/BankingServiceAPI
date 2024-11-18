package org.example.bankingportal.Util.auditAware;

import io.micrometer.common.lang.NonNullApi;
import lombok.extern.slf4j.Slf4j;
import org.example.bankingportal.security.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
@NonNullApi
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        } else {
            Object principal = authentication.getPrincipal();
            String username;
            if (principal instanceof CustomUserDetails userDetails) {
                username = userDetails.getUsername();
            }else{
                username  = principal.toString();
            }
            log.info("Current auditor: {}", username);
            return Optional.of(username);
        }
    }

}
