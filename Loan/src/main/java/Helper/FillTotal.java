package Helper;
//Encapsulation
public class FillTotal {
    //Declaring the variables of a class as private
    private double totalAmount;
    private double loan, interest, monthly, left_to_pay;
    public FillTotal(double totalAmount, double loan, double interest, double monthly, double left_to_pay) {
        this.totalAmount = totalAmount;
        this.loan = loan;
        this.interest = interest;
        this.monthly = monthly;
        this.left_to_pay = left_to_pay;
    }

    //Providing public setter and getter methods to modify and view the variables values
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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
        return getTotalAmount() + "  |  " + getLoan() + "  |   " + getInterest() + "   |  " + getMonthly() + "    | " + getLeft_to_pay();
    }
}
