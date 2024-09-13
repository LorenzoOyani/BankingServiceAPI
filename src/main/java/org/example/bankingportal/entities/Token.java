package org.example.bankingportal.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens_Table")
public class Token implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private Instant expiresAt;


}
