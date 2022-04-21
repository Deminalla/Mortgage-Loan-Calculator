package Helper;

public class FillTable extends FillTotal {
    private int monthNR;
    public FillTable(int month, double loan, double interest, double monthly, double left_to_pay) {
        super(month, loan, interest, monthly, left_to_pay);
        this.monthNR = month;
    }

    public int getMonthNR() {
        return monthNR;
    }
    public void setMonth(int monthNR) {
        this.monthNR = monthNR;
    }
    
    @Override //overrides toString in FillTotal
    public String toString(){
        return getMonthNR() + "  |  " + getLoan() + "  |   " + getInterest() + "   |  " + getMonthly() + "    | " + getLeft_to_pay();
    }
}
