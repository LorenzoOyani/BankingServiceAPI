package org.example.bankingportal.concurrency;

public interface Computable<A, V> {
    V compute(A a) throws InterruptedException;
}
