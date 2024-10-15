module com.example.carsimulationproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carsimulationproject to javafx.fxml;
    exports com.example.carsimulationproject;
}