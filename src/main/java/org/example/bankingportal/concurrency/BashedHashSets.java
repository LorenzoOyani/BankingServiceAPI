package org.example.bankingportal.concurrency;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BashedHashSets {
    private final Set<Object> sets;
    private final Semaphore semaphore; // this semaphore can turn any collection to blocking collection with the help of a bound

    BashedHashSets(int bound){
        sets = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(bound);
    }

    public boolean add(Object o) throws InterruptedException {
        semaphore.acquire();
        boolean added = false; // initial state of this variable
        try{
            added = sets.add(o); // a new updated state inside an acquired lock
            return added;

        }finally{
            if(!added){
                semaphore.release();
            }
        }

    }

    public boolean remove(Object o) throws InterruptedException {
        semaphore.acquire();
        boolean removed = false;
        try{
            removed = sets.remove(o);
            return removed;
        }finally{
            if(!removed){
                semaphore.release();
            }
        }
    }

}
