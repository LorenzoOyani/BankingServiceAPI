package org.example.bankingportal.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    private String accountNumber;
    private String accountType;
    private String accountStatus;
    private int balance;
    private int pin;

    @NotNull
    @OneToOne
    private User user;



}
