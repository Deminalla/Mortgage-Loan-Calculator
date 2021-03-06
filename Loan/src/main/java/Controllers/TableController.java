package Controllers;
import Helper.FillTable;
import Helper.FillTotal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableController implements Initializable{

    @FXML
    private Button filterButton;

    @FXML
    private TextField filterEnd;

    @FXML
    private TextField filterStart;

    @FXML
    private TableView<FillTable> tableView;

    @FXML
    private TableColumn<FillTable, Double> interestColumn;

    @FXML
    private TableColumn<FillTable, Double> left_to_payColumn;

    @FXML
    private TableColumn<FillTable, Double> loanColumn;

    @FXML
    private TableColumn<FillTable, Double> monthlyColumn;

    @FXML
    private TableColumn<FillTable, Integer> monthColumn;

    @FXML
    private TableColumn<FillTotal, Double> totalColumn;

    @FXML
    private TableColumn<FillTotal, Double> totalCredits;

    @FXML
    private TableColumn<FillTotal, Double> totalInterest;

    @FXML
    private TableColumn<FillTotal, Double> totalLoan;

    @FXML
    private TableColumn<FillTotal, Double> totalLoanInterest;

    @FXML
    private TableView<FillTotal> totalView;

    private ObservableList<FillTable> chosenList = FXCollections.observableArrayList();
    private ArrayList<Double> saveAlternative = new ArrayList<Double>(); //this one is for gathering the data of the non-chosen payment type so it can be shown in the linechart
    private ObservableList<FillTotal> totalValuesList = FXCollections.observableArrayList();
    int type_nr = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        monthColumn.setCellValueFactory(new PropertyValueFactory<FillTable, Integer>("monthNR")); // " " is for variable name
        loanColumn.setCellValueFactory(new PropertyValueFactory<FillTable, Double>("loan"));
        interestColumn.setCellValueFactory(new PropertyValueFactory<FillTable, Double>("interest"));
        monthlyColumn.setCellValueFactory(new PropertyValueFactory<FillTable, Double>("monthly"));
        left_to_payColumn.setCellValueFactory(new PropertyValueFactory<FillTable, Double>("left_to_pay"));

        totalColumn.setCellValueFactory(new PropertyValueFactory<FillTotal, Double>("totalAmount"));
        totalLoan.setCellValueFactory(new PropertyValueFactory<FillTotal, Double>("loan"));
        totalInterest.setCellValueFactory(new PropertyValueFactory<FillTotal, Double>("interest"));
        totalLoanInterest.setCellValueFactory(new PropertyValueFactory<FillTotal, Double>("monthly"));
        totalCredits.setCellValueFactory(new PropertyValueFactory<FillTotal, Double>("left_to_pay"));
    }

    @FXML
    public void addData(int month, double loan, double interest, double monthly, double left_to_pay){
        FillTable data = new FillTable(month, loan, interest, monthly, left_to_pay);
        chosenList = tableView.getItems();
        chosenList.add(data);
        tableView.setItems(chosenList);
    }
    @FXML
    public void saveData(int month, double monthly, int type){
        if (month==1){ //just in case, if we are using the program multiple times at once
            saveAlternative.clear();
        }
        saveAlternative.add(monthly);
        type_nr = type;
    }

    @FXML
    public void addTotal(double totalAmount, double loan, double interest, double monthly, double left_to_pay){
        FillTotal all = new FillTotal(totalAmount, loan, interest, monthly, left_to_pay);
        totalValuesList = totalView.getItems();
        totalValuesList.add(all);
        totalView.setItems(totalValuesList);
    }

    @FXML
    void filterMonths(ActionEvent event) {
        FilteredList<FillTable> fillterMonths_pls = new FilteredList<>(chosenList, b->true);

        fillterMonths_pls.setPredicate(month_nr -> {
            if(Objects.equals(filterStart.getText(), "") || Objects.equals(filterEnd.getText(), "")){
                return true;
            }

            if(month_nr.getMonthNR()>=Integer.parseInt(filterStart.getText()) && month_nr.getMonthNR()<=Integer.parseInt(filterEnd.getText())){
                return true;
            }
            return false;
        });

        SortedList<FillTable> sortedData = new SortedList<>(fillterMonths_pls);

        tableView.setItems(sortedData);
    }

    @FXML
    void showChart(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chart.fxml"));
            Parent root = (Parent) loader.load();
            ChartController thirdContrl = loader.getController();
            thirdContrl.printChart(chosenList, saveAlternative, type_nr);
            Stage stage = new Stage();
            stage.setTitle("Line Chart");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e){
            System.out.println("An error occurred.");
        }
    }

    @FXML
    void printToFile(ActionEvent event) {
        try{
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write("Month | Loan | Interest | Loan with interest | Left to pay\n");
            for(FillTable row : chosenList){
                myWriter.write(row.toString()); //toString is a function in FillTable (and FillTotal)
                myWriter.write("\n");
            }
            for(FillTotal row : totalValuesList){
                myWriter.write(row.toString());
                myWriter.write("\n");
            }
            myWriter.close();
        }catch(Exception e){
            System.out.println("An error occurred.");
        }
    }
}
