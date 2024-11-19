package com.example.carsimulationproject;


import com.example.carsimulationproject.View.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //BorderPane root = new BorderPane();
        MainScreen mainScreen = new MainScreen();

       /* MenuBar menuBar = new MenuBar();
        Menu showCode = new Menu("Show Code");
        Menu showAssets = new Menu("Show Assets and Images");
        Menu changeBackground = new Menu("Change Background");
        menuBar.getMenus().addAll(showCode, showAssets, changeBackground);


        MenuButton changeCar = new MenuButton("Change Car");
        MenuItem car = new MenuItem("Car");
        MenuItem truck = new MenuItem("Truck");
        changeCar.getItems().addAll(car, truck);

        MenuButton changeEngine = new MenuButton("Change Engine");
        MenuItem strongEngine = new MenuItem("Engine Ultra S-500");
        MenuItem weakEngine = new MenuItem("Engine F-001");
        changeEngine.getItems().addAll(strongEngine, weakEngine);

        MenuButton changeTires = new MenuButton("Change Tires");
        MenuItem regularTire = new MenuItem("Regular Tires");
        MenuItem winterTire = new MenuItem("Winter Tires");
        changeTires.getItems().addAll(regularTire,winterTire);

        MenuButton changeWeather = new MenuButton("Change Weather");
        MenuItem sunny = new MenuItem("Sunny Weather");
        MenuItem rainy = new MenuItem("Rainy Weather");
        changeWeather.getItems().addAll(sunny, rainy);

        MenuButton changeTrack = new MenuButton("Change Track");
        MenuItem straight = new MenuItem("Track 1");
        MenuItem downhill = new MenuItem("Track 2");
        changeTrack.getItems().addAll(straight, downhill);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(changeCar, changeEngine,changeTires, changeWeather, changeTrack);

        root.setTop(menuBar);
        root.setLeft(vBox);*/


        //play the animation
        /*Animate animation = new Animate();
        animation.setLayoutX(400);
        animation.setLayoutY(300);
        animation.playanimation();

        mainScreen.root.getChildren().add(animation);*/



        Scene scene = new Scene(mainScreen.initialize());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
