module com.app.bank_application {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.app.bank_application to javafx.fxml;
    exports com.app.bank_application;
}