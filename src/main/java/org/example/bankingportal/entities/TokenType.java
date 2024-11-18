package org.example.bankingportal.entities;

import lombok.Getter;

@Getter
public enum TokenType {

    BEARER("Bearer");

    private final String value;
    TokenType(String value) {
        this.value = value;
    }
}
