package com.bank;

import com.bank.customer.Customer;

public class Main {
    public static void main(String[] args) {
        Customer c = new Customer("Ram");
        c.createAccount(10000);
        System.out.println("Account created successfully");
        System.out.println("================================================");
        c.account.display();
        c.account.deposit(1000);
        c.account.display();
        c.account.withdraw(500);
        c.account.display();
        c.applyLoan(100000);
        c.loan.display();
    }
}
