package org.example.bankingportal.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accountusers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_req")
    @SequenceGenerator(name = "user_req", sequenceName = "user_req", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private String countryCode;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    @JoinColumn(name = "account_id")
    private Account account;

}
