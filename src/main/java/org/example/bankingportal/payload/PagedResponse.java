package org.example.bankingportal.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public record PagedResponse<T>(

    List<T> content,
    int pageNumber,
    int pageSize,
    int totalPages,
    long totalElements,
    boolean lastPage

){}
