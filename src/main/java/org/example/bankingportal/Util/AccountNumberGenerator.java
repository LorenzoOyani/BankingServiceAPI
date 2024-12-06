package org.example.bankingportal.Util;

import java.security.SecureRandom;

public class AccountNumberGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final String PREFIX = "0021";

    public static String generateAccountNumber() {
        String randomDigits = String.format("%07d", random.nextInt(1_000_000_000));
        String baseNumber = PREFIX + randomDigits;
        int checkSum = calculateCheckSum(baseNumber);
        return baseNumber + checkSum;
    }

    private static int calculateCheckSum(String baseNumber) {
        int sum = 0;
        boolean alternate = false;
        for(int i = 0; i < baseNumber.length(); i++) {
            int n = Character.getNumericValue(baseNumber.charAt(i));
            if(alternate) {
                n *= 2;
                if(n > 9){
                    n -= 9 ;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }
}
