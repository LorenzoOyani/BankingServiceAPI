package org.example.bankingportal.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Bank_users")
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

    @Column(name = "email", unique = true)
    @Basic(optional = false)
    String email;

    @Column(name = "phone_number", unique = true)
    @Basic(optional = false)
    String phoneNumber;

    @Basic(optional = false)
    String address;

    @Basic(optional = false)
    String countryCode;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Account> account = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    Role role;

    public void addAccount(Account account) {
        this.account.add(account);
        account.setUser(this);
    }

    public void removeAccount(Account account) {
        this.account.remove(account);
        account.setUser(null);

    }


}
