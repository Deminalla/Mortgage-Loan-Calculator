package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChartController1  implements Initializable{

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private CategoryAxis x_axis;

    @FXML
    private NumberAxis y_axis;

    XYChart.Series ann = new XYChart.Series();
    XYChart.Series lin = new XYChart.Series();
    @Override
    public void initialize(URL url, ResourceBundle rB){

    }

    @FXML
    public void printChart(ObservableList<FillTable>listy, ArrayList<Double>savy){
        ann.setName("Annuity"); //pavadinimai daznai buna atvirksciai so figure out a way to keep track of which is which
        lin.setName("Linear");
        int i = 1;
        for (FillTable row : listy){
            ann.getData().add(new XYChart.Data(Integer.toString(i), row.getMonthly())); //so x - month, y - monthly payment
            i++;
        }
        for (int j=0; j<=i-2; j++){ //i-2 and not i-1, nes jis praeitame cikle padidinamas papildomai viena karta
            lin.getData().add(new XYChart.Data(Integer.toString(j+1), savy.get(j)));
        }
        lineChart.getData().addAll(ann, lin);
    }
}