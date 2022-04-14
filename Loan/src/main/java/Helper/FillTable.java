package Helper;

public class FillTable {
    private int month;
    private double loan, interest, monthly, left_to_pay;
    public FillTable(int month, double loan, double interest, double monthly, double left_to_pay) {
        this.month = month;
        this.loan = loan;
        this.interest = interest;
        this.monthly = monthly;
        this.left_to_pay = left_to_pay;
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }

    public double getLoan() {
        return loan;
    }
    public void setLoan(double loan) {
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

