package com.example.demo;

public class FillTotal {
    private double month;
    private double loan, interest, monthly, left_to_pay;
    public FillTotal(double month, double loan, double interest, double monthly, double left_to_pay) {
        this.month = month;
        this.loan = loan;
        this.interest = interest;
        this.monthly = monthly;
        this.left_to_pay = left_to_pay;
    }

    public double getMonth() {
        return month;
    }
    public void setMonth(double month) {
        this.month = month;
    }

    public double getLoan() {
        return loan;
    }
    public void setLoan(double number) {
        this.loan = loan;
    }

    public double getInterest() {
        return interest;
    }
    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getMonthly() {
        return monthly;
    }
    public void setMonthly(double monthly) {
        this.monthly = monthly;
    }

    public double getLeft_to_pay() {
        return left_to_pay;
    }
    public void setLeft_to_pay(double left_to_pay) {
        this.left_to_pay = left_to_pay;
    }

    public String toString(){
        return getMonth() + "  |  " + getLoan() + "  |   " + getInterest() + "   |  " + getMonthly() + "    | " + getLeft_to_pay();
    }
}
