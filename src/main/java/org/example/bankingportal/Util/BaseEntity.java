package org.example.bankingportal.Util;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {


    @Column(name = "CREATED_AT", nullable = false)
    Instant createdAt;

    @Column(name = "CREATED_BY", nullable = false)
    String createdBy;

    @Column(name = "UPDATED_AT", nullable = false)
    Instant updatedAt;

    @Column(name = "UPDATED_BY", nullable = false)
    String updatedBy;

}