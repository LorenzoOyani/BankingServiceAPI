package org.example.bankingportal.Util;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.example.bankingportal.security.CustomUserDetails;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;


import java.time.Instant;
import java.util.Optional;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {


    @Column(name = "CREATED_AT")
    Instant created_at;

    @Column(name = "CREATED_BY")
    String created_by;

    @Column(name = "UPDATED_AT")
    Instant updated_at;

    @Column(name = "UPDATED_BY")
    String updated_by;


    private String getUsernameFromAuthenticationContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUsername();
        }

        return "anonymousUser";
    }


    @PrePersist
    public void prePersist() {
        this.created_by = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(username -> !"anonymousUser".equals(username))
                .map(Jwt.class::cast)
                .map(jwt -> jwt.getClaim(getUsernameFromAuthenticationContext()))
                .orElse("anonymous").toString();

        this.created_at = Instant.now();

    }

    @PreUpdate
    public void preUpdate() {
        this.updated_by = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(user->  !"anonymous".equals(user))
                .map(Jwt.class::cast)
                .map(jwt -> jwt.getClaim(getUsernameFromAuthenticationContext()))
                .orElse("anonymous").toString();

        this.updated_at = Instant.now();
    }

}