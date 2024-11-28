package com.example.carsimulationproject.View;

import com.example.carsimulationproject.Model.Animate;
import com.example.carsimulationproject.Model.Model;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainScreen {
    public BorderPane root = new BorderPane();
    int engineAcceleration; // enginetype
    int frictionCoefficient; // tire (increase) + weather (decrease)
    int initialVelocity; // Set by user

    public BorderPane initialize() {
        Font menuButtonFont = Font.font("Arial", FontWeight.BOLD, 14);
        MenuBar menuBar = new MenuBar();

        Menu showCode = new Menu("Show Code");
        Menu showAssets = new Menu("Show Assets and Images");
        Menu changeBackground = new Menu("Change Background");
        menuBar.getMenus().addAll(showCode, showAssets, changeBackground);

        MenuButton changeCar = new MenuButton("Change Car"); // Mass which affects normal force which affects friction force which will reduce velocity and acceleration || PROBABLY NOT NEEDED
        MenuItem car = new MenuItem("Car");
        MenuItem truck = new MenuItem("Truck");
        changeCar.getItems().addAll(car, truck);

        MenuButton changeEngine = new MenuButton("Change Engine"); // affects acceleration
        MenuItem strongEngine = new MenuItem("Engine Ultra S-500");
        MenuItem weakEngine = new MenuItem("Engine F-001");
        changeEngine.getItems().addAll(strongEngine, weakEngine);

        MenuButton changeTires = new MenuButton("Change Tires"); // affects the coefficient of friction which lowers friction force
        MenuItem regularTire = new MenuItem("Regular Tires");
        MenuItem winterTire = new MenuItem("Winter Tires");
        changeTires.getItems().addAll(regularTire,winterTire);

        MenuButton changeWeather = new MenuButton("Change Weather"); // affects the coefficient of friction which increases friction force
        MenuItem sunny = new MenuItem("Sunny Weather");
        MenuItem rainy = new MenuItem("Rainy Weather");
        changeWeather.getItems().addAll(sunny, rainy);

        MenuButton changeTrack = new MenuButton("Change Track");
        MenuItem straight = new MenuItem("Straight Track");
        MenuItem downhill = new MenuItem("Downhill Track");
        MenuItem uphill = new MenuItem("Uphill Track");
        changeTrack.getItems().addAll(straight, downhill, uphill);

        downhill.setOnAction(actionEvent -> {
            Model model = new Model();
            root.getChildren().add(model.declinettrack());
        });

        uphill.setOnAction(actionEvent -> {
            Model model = new Model();
            root.getChildren().add(model.inclinettrack());
        });

        Label potentialEnergyLabel = new Label("Potential Energy: ");
        Text potentialEnergyLevel = new Text("0");

        Label kineticEnergyLabel = new Label("Kinetic Energy: ");
        Text kineticEnergyLevel = new Text("0");

        Label mechanicalEnergyLabel = new Label("Mechanical Energy: ");
        Text mechanicalEnergyLevel = new Text("0");


        // STYLING EVERYTHING
        changeCar.setFont(menuButtonFont);
        changeEngine.setFont(menuButtonFont);
        changeTires.setFont(menuButtonFont);
        changeWeather.setFont(menuButtonFont);
        changeTrack.setFont(menuButtonFont);

        GridPane menuButtonGrid = new GridPane();
        menuButtonGrid.setAlignment(Pos.CENTER);
        menuButtonGrid.setHgap(20);
        menuButtonGrid.setVgap(20);

        menuButtonGrid.add(changeCar, 0, 0);
        menuButtonGrid.add(changeEngine, 1, 0);
        menuButtonGrid.add(changeTires, 0, 1);
        menuButtonGrid.add(changeWeather, 1, 1);
        menuButtonGrid.add(changeTrack, 0, 2, 2, 1);

        GridPane energyGrid = new GridPane();
        energyGrid.setHgap(20);
        energyGrid.setVgap(50);
        energyGrid.setAlignment(Pos.CENTER);
        potentialEnergyLabel.setFont(menuButtonFont);
        kineticEnergyLabel.setFont(menuButtonFont);
        mechanicalEnergyLabel.setFont(menuButtonFont);
        potentialEnergyLevel.setFont(Font.font(20));
        kineticEnergyLevel.setFont(Font.font(20));
        mechanicalEnergyLevel.setFont(Font.font(20));

        energyGrid.add(potentialEnergyLabel, 0, 0);
        energyGrid.add(potentialEnergyLevel, 0, 1);
        energyGrid.add(kineticEnergyLabel, 1, 0);
        energyGrid.add(kineticEnergyLevel, 1, 1);
        energyGrid.add(mechanicalEnergyLabel, 2, 0);
        energyGrid.add(mechanicalEnergyLevel, 2, 1);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(menuButtonGrid);

        HBox energyLevels = new HBox();
        energyLevels.setAlignment(Pos.CENTER);
        energyLevels.getChildren().addAll(energyGrid);

        StackPane center = new StackPane();
        center.setStyle("-fx-border-color: black; -fx-border-width: 5px;");


        Animate animation = new Animate();
        animation.setLayoutX(400);
        animation.setLayoutY(300);

        // setonaction for button that will contain the following code
        /**
         * timelineAnimation(findPoints(50, 50, Model.getDistance, Model.getAngle), timeAtPoint(etc)) <- repeat this multiple times for each segment of the track for the "complex track" if it's not complex, we can just call it once because its just one segment
         */

        center.getChildren().add(animation);

        // root.getChildren().add(animation);
        root.setCenter(center);
        //animation.timelineanimation();

        root.setTop(menuBar);
        root.setBottom(energyLevels);
        root.setLeft(vBox);
        return root;
    }


}
