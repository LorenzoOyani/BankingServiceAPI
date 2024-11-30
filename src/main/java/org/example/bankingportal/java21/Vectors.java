package org.example.bankingportal.java21;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class Vectors<E> {

    protected Object[] elementData;
    protected transient int modCount;
    private int elementCount;

    protected int capacityIncrease;


    public Vectors() {
    }

    public synchronized int indexOf(Object o, int index) {
        if (o == null) {
            for (int i = 0; i < elementCount; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < elementCount; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


}
