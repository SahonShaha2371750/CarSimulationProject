module com.example.carsimulationproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carsimulationproject to javafx.fxml;
    exports com.example.carsimulationproject;
    exports com.example.carsimulationproject.VinithAnimations;
    opens com.example.carsimulationproject.VinithAnimations to javafx.fxml;
    exports com.example.carsimulationproject.ShonGUI;
    opens com.example.carsimulationproject.ShonGUI to javafx.fxml;
    exports com.example.carsimulationproject.ObaidahPhysics;
    opens com.example.carsimulationproject.ObaidahPhysics to javafx.fxml;
}