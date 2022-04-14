module Loan {
    requires javafx.controls;
    requires javafx.fxml;

    opens Controllers to javafx.fxml;
    exports Controllers;
    opens Helper to javafx.fxml;
    exports Helper;
}