package Helper;

import java.text.DecimalFormat;

public class Calculate {
    DecimalFormat df = new DecimalFormat("#.##");

    public double percentToPay(double credits, double percent){
        double percentToPay = credits * (percent/100/12);
        percentToPay = Double.parseDouble(df.format(percentToPay));
        return percentToPay;
    }
    public double monthly(double monthlyNoPercent, double percentToPay){
        double monthlyPay = monthlyNoPercent + percentToPay;
        monthlyPay = Double.parseDouble(df.format(monthlyPay));
        return monthlyPay;
    }
    public double totalCredit(double credits, double monthlyNoPercent){;
        credits = credits - monthlyNoPercent;
        credits = Double.parseDouble(df.format(credits));
        return credits;
    }
    public double totalPercent(double totalPercentToPay, double percentToPay){
        totalPercentToPay = percentToPay + totalPercentToPay;
        totalPercentToPay = Double.parseDouble(df.format(totalPercentToPay));
        return totalPercentToPay;
    }
    public double totalMonthlyPay(double totalMonthlyPay, double monthlyPay){
        totalMonthlyPay = monthlyPay + totalMonthlyPay;
        totalMonthlyPay = Double.parseDouble(df.format(totalMonthlyPay));
        return totalMonthlyPay;
    }
    public double totalPayment(double totalMonthlyPay, double credits){
        double totalPayment = totalMonthlyPay + credits;
        totalPayment = Double.parseDouble(df.format(totalPayment));
        return totalPayment;
    }
}
