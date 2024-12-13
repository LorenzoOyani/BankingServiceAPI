package org.example.bankingportal.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memoirs<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>(); //field instantiation!

    private final Computable<A, V> computable; // Abstraction


    public Memoirs(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A a) throws InterruptedException { // this synchronized methods creates a block for other threads till it finish, hence will cause performance issues
        V result = cache.get(a);
        if (result != null) {
            result = computable.compute(a); // non-null result
            cache.put(a, result); //key-value pairs
        }
        return result; // return a cache result or compute a new one.
    }
}
