package org.example.bankingportal.concurrency;

import java.math.BigInteger;

public class ExpensiveFunc implements Computable<String, BigInteger>{


    @Override
    public BigInteger compute(String s) throws InterruptedException {
        return new BigInteger(s);
    }


}
