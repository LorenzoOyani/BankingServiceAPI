package org.example.bankingportal.hibernate;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
class Book {

    @Id
    @GeneratedValue
    Long id;

    String title;

    @Basic(optional=false)
    String isbn;

    String getIsbn() {
        return isbn;
    }

    Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }


    @Override
    public boolean equals(Object other) {
        return other instanceof Book                   // check type with instanceof, not getClass()
                && ((Book) other).getIsbn().equals(isbn);  // compare natural ids
    }
    @Override
    public int hashCode() {
        return isbn.hashCode();  // hashcode based on the natural id
    }
}