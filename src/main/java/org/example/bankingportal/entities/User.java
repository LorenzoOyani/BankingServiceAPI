package org.example.bankingportal.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;
import org.example.bankingportal.Util.BaseEntity;
import org.hibernate.annotations.NaturalId;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "account_users",
        uniqueConstraints
                = @UniqueConstraint(columnNames = {"name", "password", "phoneNumber"})
)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_req")
    @SequenceGenerator(name = "user_req", sequenceName = "user_req", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Long id;

    @Basic(optional = false)
    @NaturalId
    String name;

    @Basic(optional = false)
    String password;

    @Basic(optional = false)
    String email;

    @Basic(optional = false)
    String phoneNumber;

    @Basic(optional = false)
    String address;

    @Basic(optional = false)
    String countryCode;

    @OneToOne(mappedBy = "Account_.USER", cascade = {CascadeType.ALL})
    @JoinColumn(name = "account_id")
    Account account;

    @ManyToMany
    @JsonIgnore
    Set<Role> roles;


    @Override
    public boolean equals(Object other) {
        return other instanceof User
                && ((User) other).getName().equals(getName());
    }


}
