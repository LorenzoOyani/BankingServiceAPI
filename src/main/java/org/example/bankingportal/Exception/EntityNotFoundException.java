package org.example.bankingportal.Exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase()  + " entity does not exist" );
    }
}
