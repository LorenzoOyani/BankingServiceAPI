package org.example.bankingportal.concurrency;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Board {
    private final Set<Object> sets;


    private final Object lock = new Object();
    private Integer[] values;

    private int count;

    public Board() {
        sets = new HashSet<>();

    }

    private void commitNewValues() {
        synchronized (lock) {

            Set<Object> newSet = new HashSet<>();
            boolean committed = false;
            for (int value : values) {

                newSet.add(value);
                committed = committed || sets.add(newSet);

            }

        }
    }

    public boolean  hasCoverage(){
        int elements = values.length- 1;

        boolean coverageCount = false;
        if(!coverageCount){
            count++;
        }
        return elements >= 0 && elements <= sets.size();
    }
}
