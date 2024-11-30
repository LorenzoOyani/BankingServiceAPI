package org.example.bankingportal.Util;

public class LoggedInUser {

    private static final ThreadLocal<String> userHolder = new ThreadLocal<>();

    public static void logIn(String user) {
        userHolder.set(user);
    }

    public static void logOut() {
        userHolder.remove();
    }

    public static String get() {
        return userHolder.get();
    }
}
