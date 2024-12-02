package com.example.carsimulationproject.View;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import com.example.carsimulationproject.Model.Animate;
import com.example.carsimulationproject.Model.Trackselections;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreen {
    public BorderPane root = new BorderPane();
    int vehicleMass = 100;
    public int engineAcceleration; // enginetype
    int frictionCoefficient = 0; // tire (increase) + weather (depends on type of track)
    public int initialVelocity; // Set by user
    int height; // <- this will be replaced by a method
    Path chosenTrack;

    public BorderPane initialize() {
        // root.getStylesheets().add(getClass().getResource("/light-theme.css").toExternalForm());
        Font menuButtonFont = Font.font("Arial", 14);// FontWeight.BOLD,
        MenuBar menuBar = new MenuBar();
        Animate animate = new Animate();
        StackPane center = new StackPane();

        Menu showCode = new Menu("Show Code");
        MenuItem physicsEquations = new MenuItem("PhysicsEquations");
        physicsEquations.setOnAction(e -> { showCodePhysicsEquations(); });
        MenuItem animateCode = new MenuItem("Animate");
        animateCode.setOnAction(e -> { showCodeAnimate(); });
        MenuItem modelClass = new MenuItem("Model");
        modelClass.setOnAction(e -> { showCodeModel(); });
        MenuItem mainScreen = new MenuItem("MainScreen");
        mainScreen.setOnAction(e -> { showCodeMainScreen(); });

        showCode.getItems().addAll(physicsEquations, animateCode, modelClass, mainScreen);


        Menu showAssets = new Menu("Show Assets and Images");
        Menu changeTheme = new Menu("Light Mode");
        MenuItem enableLightMode = new MenuItem("Enable Light Mode");
        MenuItem enableDarkMode = new Menu("Enable Dark Mode");
        changeTheme.getItems().addAll(enableLightMode, enableDarkMode);
        menuBar.getMenus().addAll(showCode, showAssets, changeTheme);

        MenuButton changeCar = new MenuButton("Change Car"); // Mass which affects normal force which affects friction force which will reduce velocity and acceleration || PROBABLY NOT NEEDED
        MenuItem car = new MenuItem("Car");
        MenuItem truck = new MenuItem("Truck");
        changeCar.getItems().addAll(car, truck);

        MenuButton changeEngine = new MenuButton("Change Engine"); // affects acceleration
        MenuItem strongEngine = new MenuItem("Engine Ultra S-500");
        MenuItem weakEngine = new MenuItem("Engine F-001");
        weakEngine.setOnAction(actionEvent -> {engineAcceleration = 10;});
        strongEngine.setOnAction(actionEvent -> {engineAcceleration = 50;});
        changeEngine.getItems().addAll(strongEngine, weakEngine);

        MenuButton changeTires = new MenuButton("Change Tires"); // affects the coefficient of friction which lowers friction force
        MenuItem regularTire = new MenuItem("Regular Tires");
        MenuItem winterTire = new MenuItem("Winter Tires");
        changeTires.getItems().addAll(regularTire,winterTire);
        regularTire.setOnAction(actionEvent -> {frictionCoefficient += 1;});
        winterTire.setOnAction(actionEvent -> {frictionCoefficient += 10;});

        MenuButton changeWeather = new MenuButton("Change Weather"); // affects the coefficient of friction which increases friction force
        MenuItem sunny = new MenuItem("Sunny Weather");
        MenuItem rainy = new MenuItem("Rainy Weather");
        changeWeather.getItems().addAll(sunny, rainy);
        sunny.setOnAction(actionEvent -> {frictionCoefficient += 1;});
        rainy.setOnAction(actionEvent -> {frictionCoefficient += 10;});


        MenuButton changeTrack = new MenuButton("Change Track");
        MenuItem combo = new MenuItem("Combo Track");
        MenuItem downhill = new MenuItem("Downhill Track");
        MenuItem uphill = new MenuItem("Uphill Track");
        changeTrack.getItems().addAll(combo, downhill, uphill);

        combo.setOnAction(actionEvent -> {
            Trackselections ts = new Trackselections();
            /*center.getChildren().clear();
            center.getChildren().add(ts.combotrack());*/
            chosenTrack = ts.combotrack();

        });

        downhill.setOnAction(actionEvent -> {
            Trackselections ts = new Trackselections();
            /*center.getChildren().clear();
            center.getChildren().add(ts.declinetrack());*/
            chosenTrack = ts.declinetrack();
        });

        uphill.setOnAction(actionEvent -> {
            Trackselections ts = new Trackselections();
            /*center.getChildren().clear();
            center.getChildren().add(ts.inclinettrack());*/
            chosenTrack = ts.inclinettrack();
        });

        TextField userSetVelocity = new TextField();

        Label potentialEnergyLabel = new Label("Potential Energy: ");
        Text potentialEnergyLevel = new Text("0");

        Label kineticEnergyLabel = new Label("Kinetic Energy: ");
        Text kineticEnergyLevel = new Text("0");

        Label mechanicalEnergyLabel = new Label("Mechanical Energy: ");
        Text mechanicalEnergyLevel = new Text("0");

        Button go = new Button("Go!");
        Button reset = new Button("Reset!");


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
        menuButtonGrid.add(userSetVelocity, 0,3,1,1);

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

        VBox goAndResetButtons = new VBox();
        goAndResetButtons.setAlignment(Pos.CENTER);
        goAndResetButtons.getChildren().addAll(go, reset);


        center.setStyle("-fx-border-color: black; -fx-border-width: 5px;");


        root.setCenter(center);
        root.setTop(menuBar);
        root.setBottom(energyLevels);
        root.setLeft(vBox);
        root.setRight(goAndResetButtons);



        // LIGHT MODE COLOR SWITCHES
        enableLightMode.setOnAction(actionEvent -> {
            center.setStyle("-fx-border-color: #b190bb; -fx-border-width: 5px; -fx-background-color: #7190a8;");
            root.setStyle("-fx-background-color: #f6f8f9");

            potentialEnergyLabel.setStyle("-fx-text-fill: #0e1416");
            potentialEnergyLevel.setStyle("-fx-text-fill: white");
            kineticEnergyLabel.setStyle("-fx-text-fill: #0e1416");
            kineticEnergyLevel.setStyle("-fx-text-fill: #0e1416");
            mechanicalEnergyLabel.setStyle("-fx-text-fill: #0e1416");
            mechanicalEnergyLevel.setStyle("-fx-text-fill: #0e1416");

            changeCar.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            car.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            truck.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            changeEngine.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            strongEngine.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            weakEngine.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            changeTires.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            regularTire.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            winterTire.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            changeWeather.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            rainy.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            sunny.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            changeTrack.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            combo.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            uphill.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            downhill.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
        });


        enableDarkMode.setOnAction(actionEvent -> {
            center.setStyle("-fx-border-color: #65446f; -fx-border-width: 5px; -fx-background-color: #17083a;");
            root.setStyle("-fx-background-color: #060809");

            potentialEnergyLabel.setStyle("-fx-text-fill: #e9eff1");
            potentialEnergyLevel.setStyle("-fx-text-fill: #e9eff1");
            kineticEnergyLabel.setStyle("-fx-text-fill: #e9eff1");
            kineticEnergyLevel.setStyle("-fx-text-fill: #e9eff1");
            mechanicalEnergyLabel.setStyle("-fx-text-fill: #e9eff1");
            mechanicalEnergyLevel.setStyle("-fx-text-fill: #e9eff1");

            changeCar.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            car.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            truck.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            changeEngine.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            strongEngine.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            weakEngine.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            changeTires.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            regularTire.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            winterTire.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            changeWeather.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            rainy.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            sunny.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            changeTrack.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            combo.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            uphill.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            downhill.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");

        });


        go.setOnAction(actionEvent -> {
            PhysicsEquations equations = new PhysicsEquations();
            initialVelocity = Integer.parseInt(userSetVelocity.getText());

            Animate animation = new Animate();
            animation.setLayoutX(400);
            animation.setLayoutY(300);
            //center.getChildren().add(animation.comboTrackAnimation(vehicleMass, initialVelocity, chosenTrack));
            center.getChildren().add(animation.animateIncline(initialVelocity, chosenTrack));
            //center.getChildren().add(animation.comboTrackAnimation(vehicleMass, initialVelocity, chosenTrack));
            center.setAlignment(Pos.CENTER);

        });


        return root;
    }

    private void showCodePhysicsEquations() {
        Stage codeWindow = new Stage();
        //Add the code here
        String codeSnippet = "Add code here";

        Text codeArea = new Text(codeSnippet);

        StackPane codeRoot = new StackPane(codeArea);
        Scene codeScene = new Scene(codeRoot, 400, 300);
        codeWindow.setTitle("Code Snippet");
        codeWindow.setScene(codeScene);
        codeWindow.show();
    }

    private void showCodeAnimate() {
        Stage codeWindow = new Stage();
        //Add the code here
        String codeSnippet = "Add code here";

        Text codeArea = new Text(codeSnippet);

        StackPane codeRoot = new StackPane(codeArea);
        Scene codeScene = new Scene(codeRoot, 400, 300);
        codeWindow.setTitle("Code Snippet");
        codeWindow.setScene(codeScene);
        codeWindow.show();
    }

    private void showCodeModel() {
        Stage codeWindow = new Stage();
        //Add the code here
        String codeSnippet = "Add code here";

        Text codeArea = new Text(codeSnippet);

        StackPane codeRoot = new StackPane(codeArea);
        Scene codeScene = new Scene(codeRoot, 400, 300);
        codeWindow.setTitle("Code Snippet");
        codeWindow.setScene(codeScene);
        codeWindow.show();
    }

    private void showCodeMainScreen() {
        Stage codeWindow = new Stage();
        //Add the code here
        String codeSnippet = "Add code here";

        Text codeArea = new Text(codeSnippet);

        StackPane codeRoot = new StackPane(codeArea);
        Scene codeScene = new Scene(codeRoot, 400, 300);
        codeWindow.setTitle("Code Snippet");
        codeWindow.setScene(codeScene);
        codeWindow.show();
    }

}
