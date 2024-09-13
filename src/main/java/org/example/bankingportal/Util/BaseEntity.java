package org.example.bankingportal.Util;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.Optional;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {


    @Column(name = "CREATEDAT", nullable = false)
    Instant createdAt;

    @Column(name = "CREATEDBY", nullable = false)
    String createdBy;

    @Column(name = "UPDATEDAT", nullable = false)
    Instant updatedAt;

    @Column(name = "UPDATEDBY", nullable = false)
    String updatedBy;

}