package org.example.bankingportal.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.example.bankingportal.Util.BaseEntity;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @Column(name = "role_id", nullable = false)
    Long id;

    @Basic(optional = false)
    @NaturalId
    String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "roles", referencedColumnName = "role_id"
            ),
            inverseJoinColumns = @JoinColumn(name = "permissions", referencedColumnName = "id")
    )
    @ToString.Exclude
    Set<Permissions> permissions = new HashSet<>();

    public boolean equals(Object other) {
        return other instanceof Role
                && ((Role) other).getName().equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


}
