package com.example.carsimulationproject.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//By Shon
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //BorderPane root = new BorderPane();
        MainScreen mainScreen = new MainScreen();

        Scene scene = new Scene(mainScreen.initialize());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
