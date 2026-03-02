package com.bank.customer;

import com.bank.accounts.SavingsAccount;
import com.bank.loans.Loan;
import com.bank.utils.BankUtils;

public class Customer {
    int customerId;
    String name;
    public SavingsAccount account;
    public Loan loan;

    public Customer(String name) {
        customerId = BankUtils.generateAccountNumber();
        this.name = name;
    }

    public void createAccount(int balance) {
        int accountNumber = BankUtils.generateAccountNumber();
        account = new SavingsAccount(accountNumber, balance);
    }

    public void applyLoan(int loanAmount) {
        loan = new Loan(loanAmount);
        loan.emi = loan.calculateEMI(loanAmount, 1);
        System.out.println("Loan applied successfully");
        System.out.println("Loan Amount: " + loanAmount);
        System.out.println("Loan EMI: " + loan.emi);
        System.out.println("================================================");
    }
}
