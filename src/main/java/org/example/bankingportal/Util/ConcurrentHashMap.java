package org.example.bankingportal.Util;

import lombok.NonNull;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface ConcurrentHashMap<K, V> extends Map<K, V> {

    @Override
    default V getOrDefault(Object key, V defaultValue) {
        V v;
        return (v = this.get(key)) != null ? v : defaultValue;
    }

    default void forEach(BiConsumer<? super K, ? super V> action) {
        for (Map.Entry<K, V> entry : this.entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (NullPointerException e) {
                continue;
            }
            action.accept(k, v);
        }

    }

    default boolean remove(Object key, Object value) {
        Object currVal = this.get(key);
        if (currVal == null && !containsKey(key)) {
            return false;
        }
        remove(key);
        return true;
    }

    default boolean containsKey(Object key) {
        return this.get(key) != null;
    }

    default boolean replace(K key, V oldValue, V newValue) {
       Object currVal = this.get(key);
       if(Objects.equals(currVal, oldValue) ||
       currVal == null && !containsKey(key)
       ) {
           return false;
       }
       this.put(key, newValue);
       return true;
    }

    default V computeIfAbsent(K key, @NonNull Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        V v;
        if ((v = this.get(key)) == null) {
            V newValue;
            if ((newValue = mappingFunction.apply(key)) != null) {
                this.put(key, newValue);
                return newValue; // recomputed!
            }
        }
        return v;
    }

    default V compute(K key, @NonNull BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V v;
        v = this.get(key);
        V newValue = remappingFunction.apply(key, v);
        if (newValue == null) {
            if (v != null) {
                this.remove(key);
            }
            return null;
        }
        this.put(key, newValue);
        return newValue;
    }
}
