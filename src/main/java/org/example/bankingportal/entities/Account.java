package org.example.bankingportal.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "Account")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "account_id")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(unique = true, nullable = false)
    String accountNumber;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    @Enumerated(EnumType.STRING)
    AccountStatus accountStatus;
    BigDecimal availableBalance;
    String pin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Account other = (Account) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.getAccountNumber(), other.getAccountNumber());
        return equalsBuilder.isEquals();
    }
    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder();
        hashBuilder.append(accountNumber);
        return hashBuilder.toHashCode();
    }
}
