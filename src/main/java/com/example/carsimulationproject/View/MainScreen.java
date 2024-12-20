package com.example.carsimulationproject.View;

import com.example.carsimulationproject.Model.Animate;
import com.example.carsimulationproject.Model.CarSkins;
import com.example.carsimulationproject.Model.CodeViewer;
import com.example.carsimulationproject.Model.Trackselections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainScreen {
    public BorderPane root = new BorderPane();
    int vehicleMass = 0;
    public int totalVelocity = 0; // enginetype
    int totalFriction = 0; // tire (increase) + weather (depends on type of track)
    Path chosenTrack;
    Pane chosenAnimation;
    ImageView chosenCar;

    public BorderPane initialize() {
        // Done By Sahon
        Font menuButtonFont = Font.font("Arial", 14);// FontWeight.BOLD,
        MenuBar menuBar = new MenuBar();
        Animate animate = new Animate();
        StackPane center = new StackPane();
        CodeViewer codeViewer = new CodeViewer();

        Menu showCode = new Menu("Show Code");
        MenuItem physicsEquations = new MenuItem("PhysicsEquations");
        physicsEquations.setOnAction(e -> { codeViewer.showCodePhysicsEquations(); });
        MenuItem animateCode = new MenuItem("Animate");
        animateCode.setOnAction(e -> { codeViewer.showCodeAnimate(); });
        MenuItem modelClass = new MenuItem("Tracks");
        modelClass.setOnAction(e -> { codeViewer.showCodeModel(); });
        MenuItem mainScreen = new MenuItem("MainScreen");
        mainScreen.setOnAction(e -> { codeViewer.showCodeMainScreen(); });

        showCode.getItems().addAll(physicsEquations, animateCode, modelClass, mainScreen);

        // Done by Vinith
        Menu showAssets = new Menu("Show Assets and Images");
        MenuItem lamboview = new MenuItem("Lamborghini");
        lamboview.setOnAction(event -> {showLambo();});
        MenuItem cybertruck = new MenuItem("Cybertruck");
        cybertruck.setOnAction(event -> {showCybertruck();});
        MenuItem normaltruck = new MenuItem("Truck");
        normaltruck.setOnAction(event -> {showTruck();});
        showAssets.getItems().addAll(lamboview,cybertruck,normaltruck);

        // Done by Sahon
        Menu changeTheme = new Menu("Change Theme");
        MenuItem enableLightMode = new MenuItem("Enable Light Mode");
        MenuItem enableDarkMode = new MenuItem("Enable Dark Mode");
        changeTheme.getItems().addAll(enableLightMode, enableDarkMode);

        // Done by Obaidah
        Menu userGuide = new Menu("Help/User Guide");
        MenuItem viewUserGuide = new MenuItem("View User Guide");
        viewUserGuide.setOnAction(e -> {
            Stage userGuideWindow = new Stage();
            //Add user guide text here
            String text = "\n   Welcome to the Car Simulation Application!\n" +
                    "\n\n" +
                    "  - Navigation\n" +
                    "\n" +
                    "\tUse the Menu Bar at the top to access features.\n" +
                    "\tClick on buttons for specific actions.\n\n" +
                    "  - Functions\n" +
                    "\n" +
                    "\tUse the menus on the left to modify the simulation.\n" +
                    "\tFor example, click on \"Change Track\" to select a type of track.\n" +
                    "\tMake sure to select at least one item from each menu\n" +
                    "\tbefore running.\n" +
                    "\n" +
                    "  - Exit\n" +
                    "\n" +
                    "\tClick the \"X\" button on the top right corner or " +
                    "\n\tpress Alt + F4 to close the application.\n" +
                    "\n  - Need More Help?\n" +
                    "\n" +
                    "\tSee the full \"User Guide\" document that comes with\n" +
                    "\tthe application.\n" +
                    "\n\tEnjoy driving!\n\n\n" +
                    "\tDeveloped by: Team Vroom\n";

            Text userGuideText = new Text(text);

            StackPane userGuideRoot = new StackPane(userGuideText);
            ScrollPane scrollPane = new ScrollPane(userGuideRoot);

            // Hide the scrollbars but keep scrolling functional
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            Scene scene = new Scene(scrollPane, 400, 300);
            userGuideWindow.setTitle("User Guide");
            userGuideWindow.setScene(scene);
            userGuideWindow.show();
        });
        userGuide.getItems().addAll(viewUserGuide);

        menuBar.getMenus().addAll(showCode, showAssets, changeTheme, userGuide);

        //Done by Sahon
        MenuButton changeCar = new MenuButton("Change Car");
        MenuItem lambomenuitem = new MenuItem("Lamborghini");
        MenuItem cybertruckmenuitem = new MenuItem("Cybertruck");
        MenuItem truckmenuitem = new MenuItem("Truck");
        lambomenuitem.setOnAction(actionEvent -> {
            CarSkins carSkins = new CarSkins();
            vehicleMass = 1520;
            chosenCar = carSkins.lamborghiniSkin();
        });
        cybertruckmenuitem.setOnAction(actionEvent -> {
            CarSkins carSkins = new CarSkins();
            vehicleMass = 2500;
            chosenCar = carSkins.cybertruckSkin();
        });
        truckmenuitem.setOnAction(actionEvent -> {
            CarSkins carSkins = new CarSkins();
            vehicleMass += 4500;
            chosenCar = carSkins.regulartruckSkin();
        });
        changeCar.getItems().addAll(lambomenuitem,cybertruckmenuitem, truckmenuitem);

        MenuButton changeEngine = new MenuButton("Change Engine");
        MenuItem strongEngine = new MenuItem("Engine Ultra S-500");
        MenuItem weakEngine = new MenuItem("Engine F-001");
        weakEngine.setOnAction(actionEvent -> {totalVelocity += 10;});
        strongEngine.setOnAction(actionEvent -> {totalVelocity += 50;});
        changeEngine.getItems().addAll(strongEngine, weakEngine);

        MenuButton changeTires = new MenuButton("Change Tires");
        MenuItem regularTire = new MenuItem("Regular Tires");
        MenuItem winterTire = new MenuItem("Winter Tires");
        changeTires.getItems().addAll(regularTire,winterTire);
        regularTire.setOnAction(actionEvent -> {totalFriction += 5;});
        winterTire.setOnAction(actionEvent -> {totalFriction += 100;});

        MenuButton changeWeather = new MenuButton("Change Weather");
        MenuItem sunny = new MenuItem("Sunny Weather");

        // Done by Vinith
        sunny.setOnAction(actionEvent -> {
            // Load the background image (make sure the file path is correct)
            Image sunnybackgroundimage = new Image("C:\\Users\\user\\IdeaProjects\\CarSimulationProject\\src\\main\\resources\\sunnysky.jpg");

            // Create a BackgroundImage object for the image
            BackgroundImage backgroundImage = new BackgroundImage(
                    sunnybackgroundimage,
                    BackgroundRepeat.NO_REPEAT, // No repeat
                    BackgroundRepeat.NO_REPEAT, // No repeat
                    BackgroundPosition.CENTER,  // Center the image
                    BackgroundSize.DEFAULT      // Default size (cover the entire space)
            );

            // Create a Background object with the BackgroundImage
            Background sunnybackground = new Background(backgroundImage);

            // Set the new background on the StackPane
            center.setBackground(sunnybackground);

            // Optionally, force a layout update to make sure the background shows
            center.layout(); // This ensures that the layout is recalculated
        });



        // Done by Vinith
        MenuItem rainy = new MenuItem("Rainy Weather");

        rainy.setOnAction(actionEvent -> {


        });


        changeWeather.getItems().addAll(sunny, rainy);
        sunny.setOnAction(actionEvent -> {totalFriction += 1;});
        rainy.setOnAction(actionEvent -> {totalFriction += 50;});


        // Done by Sahon
        MenuButton changeTrack = new MenuButton("Change Track");
        MenuItem combo = new MenuItem("Combo Track");
        //MenuItem downhill = new MenuItem("Downhill Track");
        MenuItem uphill = new MenuItem("Uphill Track");
        changeTrack.getItems().addAll(combo,  uphill);

        Trackselections ts = new Trackselections();

        combo.setOnAction(actionEvent -> {
            Animate animation = new Animate();
            chosenTrack = ts.combotrack(center);
            chosenAnimation = animate.comboTrackAnimation(vehicleMass, totalVelocity, chosenTrack, totalFriction, root, chosenCar, center);
        });

        /*downhill.setOnAction(actionEvent -> {
            Animate animation = new Animate();
            chosenTrack = ts.declinetrack(center);
            chosenAnimation = animate.animateDecline(vehicleMass, totalVelocity, chosenTrack, totalFriction, root, chosenCar);
        });*/

        uphill.setOnAction(actionEvent -> {

            Animate animation = new Animate();
            chosenTrack = ts.inclinettrack(center);
            chosenAnimation = animate.animateIncline(vehicleMass, totalVelocity, chosenTrack, totalFriction, root, chosenCar);
        });

        Label inputvelocity = new Label("Initial Velocity: ");
        inputvelocity.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        TextField userSetVelocity = new TextField();

        Button go = new Button("Go!");
        Button reset = new Button("Reset!");


        // STYLING EVERYTHING
        // By Sahon and Obaidah
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
        menuButtonGrid.add(userSetVelocity, 1,3,1,1);
        menuButtonGrid.add(inputvelocity,0,3,1,1);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(menuButtonGrid);

        HBox energyLevels = new HBox();
        energyLevels.setAlignment(Pos.CENTER);

        Pane goAndResetButtons = new Pane();


        go.setLayoutX(30);
        go.setLayoutY(90);
        go.setPrefSize(80,60);

        reset.setLayoutX(160);
        reset.setLayoutY(90);
        reset.setPrefSize(80,60);

        goAndResetButtons.getChildren().addAll(go, reset);


        center.setStyle("-fx-border-color: black; -fx-border-width: 5px;");

        //Setting the default theme
        String originalCenterStyle = center.getStyle();
        String originalRootStyle = root.getStyle();
        String originalMenuBarStyle = menuBar.getStyle();
        //String originalLabelStyle = potentialEnergyLabel.getStyle();
        String originalButtonStyle = changeCar.getStyle();
        String originalOptionStyle = lambomenuitem.getStyle();
        String originalGoAndResetOptionStyle = go.getStyle();
        vBox.getChildren().add(goAndResetButtons);

        root.setCenter(center);
        root.setTop(menuBar);
        root.setBottom(energyLevels);
        root.setLeft(vBox);
        //root.setRight(goAndResetButtons);


        //By Sahon and Obaidah
        // LIGHT MODE COLOR SWITCHES
        enableLightMode.setOnAction(actionEvent -> {

            changeCar.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            lambomenuitem.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
            cybertruckmenuitem.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");
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
            //downhill.setStyle("-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416");

            // Reset styles to their original values
            center.setStyle(originalCenterStyle);
            root.setStyle(originalRootStyle);
            menuBar.setStyle(originalMenuBarStyle);

            changeCar.setStyle(originalButtonStyle);
            lambomenuitem.setStyle(originalOptionStyle);
            cybertruckmenuitem.setStyle(originalOptionStyle);
            truckmenuitem.setStyle(originalOptionStyle);
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
            //downhill.setStyle(originalOptionStyle);
            go.setStyle(originalGoAndResetOptionStyle);
            reset.setStyle(originalGoAndResetOptionStyle);
        });

        //By Sahon and Obaidah
        enableDarkMode.setOnAction(actionEvent -> {


            center.setStyle("-fx-border-color: #65446f; -fx-border-width: 5px; -fx-background-color: #2f333d;");
            root.setStyle("-fx-background-color: #272930");
            menuBar.setStyle("-fx-background-color: #77777c");




            changeCar.setStyle("-fx-background-color: #6734ac; -fx-text-fill: #e9eff1");
            lambomenuitem.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            cybertruckmenuitem.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            truckmenuitem.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
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
            //downhill.setStyle(/*"-fx-background-color: #453659;*/" -fx-text-fill: #131542");
            go.setStyle("-fx-background-color: #2b5624; -fx-text-fill: #e9eff1");
            reset.setStyle("-fx-background-color: #660a0e; -fx-text-fill: #e9eff1");

        });


        // By Sahon
        go.setOnAction(actionEvent -> {
            totalVelocity += Integer.parseInt(userSetVelocity.getText());

            Animate animation = new Animate();
            animation.setLayoutX(400);
            animation.setLayoutY(300);

            center.getChildren().clear();
            center.getChildren().add(chosenAnimation);
            center.setAlignment(Pos.CENTER);
            animate.getPathTransition().play(); // We take the pathTransition from the animate class, that is being set by the comboTrackAnimation method

        });

        //By Sahon
        reset.setOnAction(e-> {
            totalVelocity = 0;
            center.getChildren().clear();
            animate.getPathTransition().stop();
            root.setBottom(null);
        });


        return root;
    }

    // All show methods done by Vinith
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



}
