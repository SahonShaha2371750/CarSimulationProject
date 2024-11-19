package com.example.carsimulationproject.View;

import com.example.carsimulationproject.Model.Animate;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MainScreen {
    public BorderPane root = new BorderPane();

    public BorderPane initialize() {
        Font menuButtonFont = Font.font("Arial", FontWeight.BOLD, 14);
        MenuBar menuBar = new MenuBar();

        Menu showCode = new Menu("Show Code");
        Menu showAssets = new Menu("Show Assets and Images");
        Menu changeBackground = new Menu("Change Background");
        menuBar.getMenus().addAll(showCode, showAssets, changeBackground);

        MenuButton changeCar = new MenuButton("Change Car");
        MenuItem car = new MenuItem("Car");
        MenuItem truck = new MenuItem("Truck");
        changeCar.getItems().addAll(car, truck);
        car.setOnAction(actionEvent -> {int carMass = 50;});
        truck.setOnAction(actionEvent -> {int truckMass = 100;});

        MenuButton changeEngine = new MenuButton("Change Engine");
        MenuItem strongEngine = new MenuItem("Engine Ultra S-500");
        MenuItem weakEngine = new MenuItem("Engine F-001");
        changeEngine.getItems().addAll(strongEngine, weakEngine);
        strongEngine.setOnAction(actionEvent -> {int strongEngineForce = 100;});
        weakEngine.setOnAction(actionEvent -> {int weakEngineForce = 50;});

        MenuButton changeTires = new MenuButton("Change Tires");
        MenuItem regularTire = new MenuItem("Regular Tires");
        MenuItem winterTire = new MenuItem("Winter Tires");
        changeTires.getItems().addAll(regularTire,winterTire);
        regularTire.setOnAction(actionEvent -> {int regularFriction = 0;});
        winterTire.setOnAction(actionEvent -> {int winterFriction = 25;});

        MenuButton changeWeather = new MenuButton("Change Weather");
        MenuItem sunny = new MenuItem("Sunny Weather");
        MenuItem rainy = new MenuItem("Rainy Weather");
        changeWeather.getItems().addAll(sunny, rainy);
        sunny.setOnAction(actionEvent -> {int sunnyFriction = 0;});
        rainy.setOnAction(actionEvent -> {int rainyFriction = 10;});

        MenuButton changeTrack = new MenuButton("Change Track");
        MenuItem straight = new MenuItem("Straight Track");
        MenuItem downhill = new MenuItem("Downhill Track");
        MenuItem uphill = new MenuItem("Uphill Track");
        changeTrack.getItems().addAll(straight, downhill, uphill);
        downhill.setOnAction(actionEvent -> {customizeTrack();});
        uphill.setOnAction(actionEvent -> {customizeTrack();});

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

        root.setStyle("-fx-background-color: lightBlue;");


        // Adding Everything to the Root
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(menuButtonGrid);

        HBox energyLevels = new HBox();
        energyLevels.setAlignment(Pos.CENTER);
        energyLevels.getChildren().addAll(energyGrid);

        root.setTop(menuBar);
        root.setBottom(energyLevels);
        root.setLeft(vBox);


        // Playing the animation
        Animate animation = new Animate();
        animation.setLayoutX(400);
        animation.setLayoutY(300);
        animation.playanimation();
        root.getChildren().add(animation);

        return root;
    }

    public int[] customizeTrack() {
        Dialog<int[]> dialogBox = new Dialog<>();
        dialogBox.setTitle("Customize the track");
        dialogBox.setHeaderText("Enter the length of the track and an angle");

        TextField length = new TextField();
        length.setPromptText("Length");

        TextField angle = new TextField();
        angle.setPromptText("Angle");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Length:"), 0, 0);
        grid.add(length, 1, 0);
        grid.add(new Label("Angle:"), 0, 1);
        grid.add(angle, 1, 1);

        dialogBox.getDialogPane().setContent(grid);

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogBox.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

        int[] result = dialogBox.showAndWait().orElse(null);
        int[] array = new int[2];
        if (result != null) {
            array[0] = Integer.parseInt(length.getText());
            array[1] = Integer.parseInt(angle.getText());
        }
        else {
            System.out.println("Dialog canceled or invalid input.");
        }

        return array;

    }
}
