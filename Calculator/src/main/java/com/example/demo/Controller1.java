package com.example.demo;

import java.lang.Math;
import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class Controller1 {

    @FXML
    private RadioButton annuity;

    @FXML
    private Button done;

    @FXML
    private RadioButton linear;

    @FXML
    private TextField loanAmount;

    @FXML
    private ToggleGroup p_type;

    @FXML
    private TextField percentage;

    @FXML
    private TextField put_off_end;

    @FXML
    private TextField put_off_start;

    @FXML
    private TextField putOffPercentage; //REMEMBER TO ADD THIS INTO THE ALGORITHM

    @FXML
    private TextField time_month;

    @FXML
    private TextField time_year;

    @FXML
    void jobWellDone(ActionEvent event)  {
        DecimalFormat df = new DecimalFormat("#.##");
        double loan = Double.parseDouble(loanAmount.getText()); //convert from string to double
        double percent = Integer.parseInt(percentage.getText());
        int months = Integer.parseInt(time_year.getText()) * 12 + Integer.parseInt(time_month.getText());
        int putOffStart = 0, putOffEnd = 0, putOffPercent = 0; //kad jeigu neivesra nk tai bus 0
        if (!put_off_start.getText().trim().isEmpty()){ //if we have written smth in the text field of put_off_start
            putOffStart = Integer.parseInt(put_off_start.getText());
            putOffEnd = Integer.parseInt(put_off_end.getText());
            putOffPercent = Integer.parseInt(putOffPercentage.getText());
        }
        double credits = loan; //how much left to pay
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("table.fxml")); //new FXMLLoader(Main.class.getResource("table.fxml"));
            Parent root = (Parent) loader.load();
            TableController secondsContrl = loader.getController();
            if(linear.isSelected()){
                double monthlyNoPercent = loan/months; //this goes 0 if we putOff
                monthlyNoPercent = Double.parseDouble(df.format(monthlyNoPercent));
                double percentToPay, monthlyPay;

                double totalMontlyNoPercent = months * monthlyNoPercent, totalPercentToPay = 0, totalMonthlyPay = 0;
                totalMontlyNoPercent = Double.parseDouble(df.format(totalMontlyNoPercent));
                for (int i=1; i<=months; i++) {
                    percentToPay = credits * (percent/100/12);
                    percentToPay = Double.parseDouble(df.format(percentToPay));

                    // update ↓ ↓
                    monthlyNoPercent = loan/months;
                    monthlyNoPercent = Double.parseDouble(df.format(monthlyNoPercent));
                    if(i>=putOffStart && i<=putOffEnd){
                        monthlyNoPercent = monthlyNoPercent - monthlyNoPercent/100*putOffPercent;
                        monthlyNoPercent = Double.parseDouble(df.format(monthlyNoPercent));

                    }
                    // update ↑ ↑

                    monthlyPay = monthlyNoPercent + percentToPay;
                    monthlyPay = Double.parseDouble(df.format(monthlyPay));
                    credits = credits - monthlyNoPercent;
                    credits = Double.parseDouble(df.format(credits));

                    totalPercentToPay = percentToPay + totalPercentToPay;
                    totalPercentToPay = Double.parseDouble(df.format(totalPercentToPay));
                    totalMonthlyPay = monthlyPay + totalMonthlyPay;
                    totalMonthlyPay = Double.parseDouble(df.format(totalMonthlyPay));

                    secondsContrl.addData(i, monthlyNoPercent, percentToPay, monthlyPay, credits);

                    if (i==months) { //new window
                        double totalPayment = totalMonthlyPay + credits;
                        totalPayment = Double.parseDouble(df.format(totalPayment));
                        // update ↓ ↓ , used to be: secondsContrl.addTotal(totalPayment, totalMontlyNoPercent, totalPercentToPay, totalMonthlyPay, 0);
                        if (putOffStart==0 && putOffEnd == 0) {
                            secondsContrl.addTotal(totalPayment, totalMontlyNoPercent, totalPercentToPay, totalMonthlyPay, 0);
                        }
                        else{
                            secondsContrl.addTotal(totalPayment, totalMontlyNoPercent, totalPercentToPay, totalMonthlyPay, credits);
                        } // update ↑ ↑
                        Stage stage = new Stage();
                        stage.setTitle("Linear");
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                }
            }
            else if(annuity.isSelected()) {
                double monthlyPay = (loan * (percent/100/12)) / (1 - Math.pow((1 + (percent/100/12)), -months)); //total month payment
                monthlyPay = Double.parseDouble(df.format(monthlyPay));
                double monthlyNoPercent, percentToPay;

                double totalMontlyNoPercent = 0, totalPercentToPay = 0, totalMonthlyPay = months * monthlyPay;
                totalMonthlyPay = Double.parseDouble(df.format(totalMonthlyPay));
                for (int i=1; i<=months; i++){
                    percentToPay = credits * (percent/100/12);
                    percentToPay = Double.parseDouble(df.format(percentToPay));

                    // update ↓ ↓
                    monthlyPay = (loan * (percent/100/12)) / (1 - Math.pow((1 + (percent/100/12)), -months)); //total month payment
                    monthlyPay = Double.parseDouble(df.format(monthlyPay));
                    monthlyNoPercent = monthlyPay - percentToPay;
                    monthlyNoPercent = Double.parseDouble(df.format(monthlyNoPercent)); //kita svetaine keistai apvalina kartais..

                    if(i>=putOffStart && i<=putOffEnd){
                        monthlyNoPercent = monthlyNoPercent - monthlyNoPercent/100*putOffPercent;
                        monthlyNoPercent = Double.parseDouble(df.format(monthlyNoPercent));

                        monthlyPay = monthlyNoPercent + percentToPay;
                        monthlyPay = Double.parseDouble(df.format(monthlyPay));
                    }
                    // update ↑ ↑

                    credits = credits- monthlyNoPercent;
                    credits = Double.parseDouble(df.format(credits));

                    totalMontlyNoPercent = monthlyNoPercent + totalMontlyNoPercent;
                    totalMontlyNoPercent = Double.parseDouble(df.format(totalMontlyNoPercent));
                    totalPercentToPay = percentToPay + totalPercentToPay;
                    totalPercentToPay = Double.parseDouble(df.format(totalPercentToPay));

                    secondsContrl.addData(i, monthlyNoPercent, percentToPay, monthlyPay, credits);

                    if (i==months) { //new window
                        double totalPayment = totalMonthlyPay + credits;
                        totalPayment = Double.parseDouble(df.format(totalPayment));

                        // update ↓ ↓
                        if (putOffStart==0 && putOffEnd == 0) {
                            secondsContrl.addTotal(totalPayment, totalMontlyNoPercent, totalPercentToPay, totalMonthlyPay, 0);
                        }
                        else{
                            secondsContrl.addTotal(totalPayment, totalMontlyNoPercent, totalPercentToPay, totalMonthlyPay, credits);
                        } // update ↑ ↑

                        Stage stage = new Stage();
                        stage.setTitle("Annuity");
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                }
            }
        } catch(Exception e){
            System.out.println("An error occurred.");
        }
    }
}
