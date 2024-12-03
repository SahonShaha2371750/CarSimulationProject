package com.example.carsimulationproject.View;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import com.example.carsimulationproject.Model.Animate;
import com.example.carsimulationproject.Model.Trackselections;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    int vehicleMass = 0;
    public int totalVelocity = 0; // enginetype
    int totalFriction = 0; // tire (increase) + weather (depends on type of track)
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

        //viewing the vehicules
        Menu showAssets = new Menu("Show Assets and Images");
        MenuItem lamboview = new MenuItem("Lamborghini");
        lamboview.setOnAction(event -> {showLambo();});
        MenuItem cybertruck = new MenuItem("Cybertruck");
        cybertruck.setOnAction(event -> {showCybertruck();});
        MenuItem normaltruck = new MenuItem("Truck");
        normaltruck.setOnAction(event -> {showTruck();});
        showAssets.getItems().addAll(lamboview,cybertruck,normaltruck);

        Menu changeTheme = new Menu("Change Theme");
        MenuItem enableLightMode = new MenuItem("Enable Light Mode");
        MenuItem enableDarkMode = new MenuItem("Enable Dark Mode");
        changeTheme.getItems().addAll(enableLightMode, enableDarkMode);

        Menu userGuide = new Menu("User Guide");
        MenuItem viewUserGuide = new MenuItem("View User Guide");
        viewUserGuide.setOnAction(e -> {
            Stage userGuideWindow = new Stage();
            //Add user guide text here
            String text = "Add text here";

            Text userGuideText = new Text(text);

            StackPane userGuideRoot = new StackPane(userGuideText);
            Scene scene = new Scene(userGuideRoot, 400, 300);
            userGuideWindow.setTitle("User Guide");
            userGuideWindow.setScene(scene);
            userGuideWindow.show();
        });
        userGuide.getItems().addAll(viewUserGuide);

        menuBar.getMenus().addAll(showCode, showAssets, changeTheme, userGuide);

        MenuButton changeCar = new MenuButton("Change Car"); // Mass which affects normal force which affects friction force which will reduce velocity and acceleration || PROBABLY NOT NEEDED
        MenuItem car = new MenuItem("Car");
        MenuItem truck = new MenuItem("Truck");
        car.setOnAction(actionEvent -> vehicleMass += 50);
        truck.setOnAction(actionEvent -> vehicleMass += 100);
        changeCar.getItems().addAll(car, truck);

        MenuButton changeEngine = new MenuButton("Change Engine"); // affects acceleration
        MenuItem strongEngine = new MenuItem("Engine Ultra S-500");
        MenuItem weakEngine = new MenuItem("Engine F-001");
        weakEngine.setOnAction(actionEvent -> {totalVelocity += 10;});
        strongEngine.setOnAction(actionEvent -> {totalVelocity += 50;});
        changeEngine.getItems().addAll(strongEngine, weakEngine);

        MenuButton changeTires = new MenuButton("Change Tires"); // affects the coefficient of friction which lowers friction force
        MenuItem regularTire = new MenuItem("Regular Tires");
        MenuItem winterTire = new MenuItem("Winter Tires");
        changeTires.getItems().addAll(regularTire,winterTire);
        regularTire.setOnAction(actionEvent -> {totalFriction += 1;});
        winterTire.setOnAction(actionEvent -> {totalFriction += 10;});

        MenuButton changeWeather = new MenuButton("Change Weather"); // affects the coefficient of friction which increases friction force
        MenuItem sunny = new MenuItem("Sunny Weather");
        MenuItem rainy = new MenuItem("Rainy Weather");
        changeWeather.getItems().addAll(sunny, rainy);
        sunny.setOnAction(actionEvent -> {totalFriction += 1;});
        rainy.setOnAction(actionEvent -> {totalFriction += 10;});


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

        //Setting the default theme
        String originalCenterStyle = center.getStyle();
        String originalRootStyle = root.getStyle();
        String originalMenuBarStyle = menuBar.getStyle();
        String originalLabelStyle = potentialEnergyLabel.getStyle();
        String originalButtonStyle = changeCar.getStyle();
        String originalOptionStyle = car.getStyle();
        String originalGoAndResetOptionStyle = go.getStyle();


        root.setCenter(center);
        root.setTop(menuBar);
        root.setBottom(energyLevels);
        root.setLeft(vBox);
        root.setRight(goAndResetButtons);



        // LIGHT MODE COLOR SWITCHES
        enableLightMode.setOnAction(actionEvent -> {
            /*center.setStyle("-fx-border-color: #b190bb; -fx-border-width: 5px; -fx-background-color: #7190a8;");
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
            downhill.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");*/

            // Reset styles to their original values
            center.setStyle(originalCenterStyle);
            root.setStyle(originalRootStyle);
            menuBar.setStyle(originalMenuBarStyle);

            potentialEnergyLabel.setStyle(originalLabelStyle);
            potentialEnergyLevel.setStyle(originalLabelStyle);
            kineticEnergyLabel.setStyle(originalLabelStyle);
            kineticEnergyLevel.setStyle(originalLabelStyle);
            mechanicalEnergyLabel.setStyle(originalLabelStyle);
            mechanicalEnergyLevel.setStyle(originalLabelStyle);

            changeCar.setStyle(originalButtonStyle);
            car.setStyle(originalOptionStyle);
            truck.setStyle(originalOptionStyle);
            changeEngine.setStyle(originalButtonStyle);
            strongEngine.setStyle(originalOptionStyle);
            weakEngine.setStyle(originalOptionStyle);
            changeTires.setStyle(originalButtonStyle);
            regularTire.setStyle(originalOptionStyle);
            winterTire.setStyle(originalOptionStyle);
            changeWeather.setStyle(originalButtonStyle);
            rainy.setStyle(originalOptionStyle);
            sunny.setStyle(originalOptionStyle);
            changeTrack.setStyle(originalButtonStyle);
            combo.setStyle(originalOptionStyle);
            uphill.setStyle(originalOptionStyle);
            downhill.setStyle(originalOptionStyle);
            go.setStyle(originalGoAndResetOptionStyle);
            reset.setStyle(originalGoAndResetOptionStyle);
        });


        enableDarkMode.setOnAction(actionEvent -> {
            /*center.setStyle("-fx-border-color: #65446f; -fx-border-width: 5px; -fx-background-color: #17083a;");
            root.setStyle("-fx-background-color: #060809");*/

            center.setStyle("-fx-border-color: #65446f; -fx-border-width: 5px; -fx-background-color: #2f333d;");
            root.setStyle("-fx-background-color: #272930");
            menuBar.setStyle("-fx-background-color: #77777c");

            potentialEnergyLabel.setStyle("-fx-text-fill: #e9eff1");
            potentialEnergyLevel.setFill(Color.WHITESMOKE);
            kineticEnergyLabel.setStyle("-fx-text-fill: #e9eff1");
            kineticEnergyLevel.setFill(Color.WHITESMOKE);
            mechanicalEnergyLabel.setStyle("-fx-text-fill: #e9eff1");
            mechanicalEnergyLevel.setFill(Color.WHITESMOKE);

            /*changeCar.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");
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
            downhill.setStyle("-fx-background-color: #453659; -fx-text-fill: #e9eff1");*/


            changeCar.setStyle("-fx-background-color: #6734ac; -fx-text-fill: #e9eff1");
            car.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            truck.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            changeEngine.setStyle("-fx-background-color: #6734ac; -fx-text-fill: #e9eff1");
            strongEngine.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            weakEngine.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            changeTires.setStyle("-fx-background-color: #6734ac; -fx-text-fill: #e9eff1");
            regularTire.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            winterTire.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            changeWeather.setStyle("-fx-background-color: #6734ac; -fx-text-fill: #e9eff1");
            rainy.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            sunny.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            changeTrack.setStyle("-fx-background-color: #6734ac; -fx-text-fill: #e9eff1");
            combo.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            uphill.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            downhill.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            go.setStyle("-fx-background-color: #2b5624; -fx-text-fill: #e9eff1");
            reset.setStyle("-fx-background-color: #660a0e; -fx-text-fill: #e9eff1");

        });


        go.setOnAction(actionEvent -> {
            PhysicsEquations equations = new PhysicsEquations();
            totalVelocity += Integer.parseInt(userSetVelocity.getText());

            Animate animation = new Animate();
            animation.setLayoutX(400);
            animation.setLayoutY(300);
            center.getChildren().clear();
            center.getChildren().add(animation.comboTrackAnimation(vehicleMass, totalVelocity, chosenTrack, totalFriction, root));
            center.setAlignment(Pos.CENTER);

        });

        reset.setOnAction(e-> {
            totalVelocity = 0;
            center.getChildren().clear();
        });


        return root;
    }

    private void showLambo() {

        Stage imageWindow = new Stage();

        Image lamboimage = new Image("lambo.png");

        ImageView lamboView = new ImageView(lamboimage);


        lamboView.setFitWidth(300);
        lamboView.setFitHeight(200);
        lamboView.setPreserveRatio(true);

        StackPane carRoot = new StackPane(lamboView);
        Scene lamboScene = new Scene(carRoot, 400, 300);
        imageWindow.setTitle("Lamborghini");
        imageWindow.setScene(lamboScene);
        imageWindow.show();

    }

    private void showCybertruck() {

        Stage imageWindow = new Stage();

        Image cyberimage = new Image("cybertruck.png");

        ImageView cyberView = new ImageView(cyberimage);


        cyberView.setFitWidth(300);
        cyberView.setFitHeight(200);
        cyberView.setPreserveRatio(true);

        StackPane carRoot = new StackPane(cyberView);
        Scene cyberScene = new Scene(carRoot, 400, 300);
        imageWindow.setTitle("Cybertruck");
        imageWindow.setScene(cyberScene);
        imageWindow.show();

    }

    private void showTruck() {

        Stage imageWindow = new Stage();

        Image truckimage = new Image("truck.png");

        ImageView truckView = new ImageView(truckimage);


        truckView.setFitWidth(300);
        truckView.setFitHeight(200);
        truckView.setPreserveRatio(true);

        StackPane carRoot = new StackPane(truckView);
        Scene truckScene = new Scene(carRoot, 400, 300);
        imageWindow.setTitle("Truck");
        imageWindow.setScene(truckScene);
        imageWindow.show();

    }

    private void showCodePhysicsEquations() {
        Stage codeWindow = new Stage();
        //Add the code here
        String codeSnippet = "Add code here";

        Text codeArea = new Text(codeSnippet);

        StackPane codeRoot = new StackPane(codeArea);
        Scene codeScene = new Scene(codeRoot, 400, 300);
        codeWindow.setTitle("PhysicsEquations");
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
        codeWindow.setTitle("Animate");
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
        codeWindow.setTitle("Model");
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
        codeWindow.setTitle("MainScreen");
        codeWindow.setScene(codeScene);
        codeWindow.show();
    }

}
