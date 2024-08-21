module org.example.supermarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.dinidu.samplecrud to javafx.fxml; // This allows JavaFX to access your controllers or any classes in this package
    opens com.dinidu.samplecrud.dto to javafx.base; // Open the DTO package to javafx.base so it can access the properties
    exports com.dinidu.samplecrud; // This exports the package
    exports com.dinidu.samplecrud.controller; // This exports the controller package
    opens com.dinidu.samplecrud.controller to javafx.fxml; // This allows JavaFX to access classes in the controller package
}