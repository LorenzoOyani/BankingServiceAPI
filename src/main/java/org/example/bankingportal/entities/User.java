package org.example.bankingportal.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "account_users",
        uniqueConstraints
                = @UniqueConstraint(columnNames = {"name", "password", "phone_number"})
)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_req")
    @SequenceGenerator(name = "user_req", sequenceName = "user_req", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Long id;

    @Basic(optional = false)
    String firstName;


    @Basic(optional = false)
    String lastName;

    @Basic(optional = false)
    String password;

    @Basic(optional = false)
    String email;

    @Column(name = "phone_number")
    @Basic(optional = false)
    String phoneNumber;

    @Basic(optional = false)
    String address;

    @Basic(optional = false)
    String countryCode;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
    Account account;

    @Enumerated(EnumType.STRING)
    Role role;


}
