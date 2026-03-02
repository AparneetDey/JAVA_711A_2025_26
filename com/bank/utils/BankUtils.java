package com.bank.utils;

public class BankUtils {
    public static int generateAccountNumber() {
        return (int) (Math.random() * 1000000);
    }

    public static boolean validateMinimumBalance(int withdraw, int balance) {
        return balance >= withdraw;
    }
}
