package org.example.bankingportal.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Messages {

        private Long id;

        @NotEmpty
        private String content;
        @NotEmpty
        private String createdBy;
        private Instant createdAt;

}
