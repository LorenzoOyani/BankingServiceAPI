package org.example.bankingportal.Util;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseEntity<T, U> {

    public abstract U convertToEntity(T t, Object... args);

    public abstract T convertFromEntity(U u, Object... args);

    public Collection<U> convertToEntityCollection(Collection<T> ts, Object... args) {
        return ts.stream().map(entity -> convertToEntity(entity, args)).collect(Collectors.toList());
    }

    public Collection<T> convertFromEntityCollection(Collection<U> ts, Object... args) {
        return ts.stream().map(entity -> convertFromEntity(entity, args)).collect(Collectors.toList());
    }
}