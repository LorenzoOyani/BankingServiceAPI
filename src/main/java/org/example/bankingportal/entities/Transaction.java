package org.example.bankingportal.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Transactions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_gen")
    @SequenceGenerator(name = "transactions_gen", sequenceName = "transactions_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    LocalDateTime transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    Account sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_account_id")
    Account targetAccount;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;


}
