package com.example.carsimulationproject.View;


import com.example.carsimulationproject.Controller.PhysicsEquations;
import com.example.carsimulationproject.View.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainScreen mainScreen = new MainScreen();

        // Testing if the method works
        /*PhysicsEquations physicsEquations = new PhysicsEquations();
        System.out.print(physicsEquations.timeAtAPoint(50, 80, 100, 0, 1, "flat"));*/



        Scene scene = new Scene(mainScreen.initialize());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
