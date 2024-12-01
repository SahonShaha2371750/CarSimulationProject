package com.example.carsimulationproject.View;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import com.example.carsimulationproject.Model.Animate;
import com.example.carsimulationproject.Model.Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreen {
    public BorderPane root = new BorderPane();
    int engineAcceleration; // enginetype
    int frictionCoefficient; // tire (increase) + weather (depends on type of track)
    int initialVelocity; // Set by user

    public BorderPane initialize() {
        // root.getStylesheets().add(getClass().getResource("/light-theme.css").toExternalForm());
        Font menuButtonFont = Font.font("Arial", 14);// FontWeight.BOLD,
        MenuBar menuBar = new MenuBar();

        Menu showCode = new Menu("Show Code");
        MenuItem physicsEquations = new MenuItem("PhysicsEquations");
        physicsEquations.setOnAction(e -> { showCodePhysicsEquations(); });
        MenuItem animate = new MenuItem("Animate");
        animate.setOnAction(e -> { showCodeAnimate(); });
        MenuItem modelClass = new MenuItem("Model");
        modelClass.setOnAction(e -> { showCodeModel(); });
        MenuItem mainScreen = new MenuItem("MainScreen");
        mainScreen.setOnAction(e -> { showCodeMainScreen(); });

        showCode.getItems().addAll(physicsEquations, animate, modelClass, mainScreen);

        Menu showAssets = new Menu("Show Assets and Images");
        //Menu changeBackground = new Menu("Change Background");
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

        //Labels here
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
            straight.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
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
            straight.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            uphill.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
            downhill.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");

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
