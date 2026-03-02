package com.bank.loans;

import static com.bank.accounts.SavingsAccount.calculateInterest;

public class Loan {
    int loanAmount;
    public int emi;

    public Loan(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int calculateEMI(int p, int t) {
        emi = (calculateInterest(p, t)) / 12;
        return emi;
    }

    public void display() {
        System.out.println("Loan Amount: " + loanAmount);
        System.out.println("Loan EMI: " + emi);
        System.out.println("================================================");
    }
}
