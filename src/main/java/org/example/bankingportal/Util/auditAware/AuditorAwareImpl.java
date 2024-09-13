package org.example.bankingportal.Util.auditAware;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@NonNullApi
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override

    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            authentication.isAuthenticated();
        } else {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return Optional.of(((UserDetails) principal).getUsername());
        }

        return Optional.empty();
    }
}
