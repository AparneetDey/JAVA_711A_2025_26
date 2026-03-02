package com.bank.accounts;

import com.bank.utils.BankUtils;

class Account {
    int accountNumber;
    int balance;

    public Account(int accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Amount deposited successfully");
        System.out.println("Amount deposited: " + amount);
        System.out.println("================================================");
    }

    public void withdraw(int amount) {
        if(BankUtils.validateMinimumBalance(amount, balance)) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully");
            System.out.println("Amount withdrawn: " + amount);
            System.out.println("================================================");

        } else {
            System.out.println("Account balance is less than the withdrawl amount");
            System.out.println("================================================");
        }
    }

    public void display() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Balance: " + balance);
        System.out.println("================================================");
    }
}

public class SavingsAccount extends Account {
    static int interestRate = 15;
    static int interest;

    public SavingsAccount(int accountNumber, int balance) {
        super(accountNumber, balance);
    }

    public static int calculateInterest(int P, int T) {
        interest = (P*interestRate*T) / 100;
        return interest;
    }
}