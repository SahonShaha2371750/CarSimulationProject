module com.example.carsimulationproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carsimulationproject to javafx.fxml;
    exports com.example.carsimulationproject;
    //exports com.example.carsimulationproject.VinithAnimations;
    //opens com.example.carsimulationproject.VinithAnimations to javafx.fxml;
    //exports com.example.carsimulationproject.ShonGUI;
    //opens com.example.carsimulationproject.ShonGUI to javafx.fxml;
    exports com.example.carsimulationproject.Model;
    opens com.example.carsimulationproject.Model to javafx.fxml;
    //exports com.example.carsimulationproject.View;
    //opens com.example.carsimulationproject.View to javafx.fxml;
    exports com.example.carsimulationproject.Controller;
    opens com.example.carsimulationproject.Controller to javafx.fxml;
}