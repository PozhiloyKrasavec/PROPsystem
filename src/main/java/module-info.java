module com.example.nastya {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;


    opens com.example.nastya to javafx.fxml;
    exports com.example.nastya;
    exports com.example.nastya.Modal;
    opens com.example.nastya.Modal to javafx.fxml;
}