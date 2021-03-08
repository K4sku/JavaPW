module atj {
    requires javafx.controls;
    requires javafx.fxml;

    opens atj to javafx.fxml;
    exports atj;
}