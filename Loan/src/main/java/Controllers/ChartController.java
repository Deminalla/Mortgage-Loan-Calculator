package Controllers;
import Helper.FillTable;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.util.ArrayList;

public class ChartController {

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private CategoryAxis x_axis;

    @FXML
    private NumberAxis y_axis;

    @FXML
    public void printChart(ObservableList<FillTable>listy, ArrayList<Double>savy, int type){
        XYChart.Series chosen = new XYChart.Series(); //I chose this one with radiobutton
        XYChart.Series compared = new XYChart.Series(); //this one is needed to compare to my chosen payment type
        if (type==1){
            chosen.setName("Linear");
            compared.setName("Annuity");
        }
        else if(type==2){
            chosen.setName("Annuity");
            compared.setName("Linear");
        }
        int i = 1;
        for (FillTable row : listy){
            chosen.getData().add(new XYChart.Data(Integer.toString(i), row.getMonthly())); //so x - month, y - monthly payment
            i++;
        }
        for (int j=0; j<=i-2; j++){ //i-2 and not i-1, nes jis praeitame cikle padidinamas papildomai viena karta
            compared.getData().add(new XYChart.Data(Integer.toString(j+1), savy.get(j)));
        }
        lineChart.getData().addAll(chosen, compared);
    }
}