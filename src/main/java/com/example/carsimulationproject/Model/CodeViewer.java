package com.example.carsimulationproject.Model;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CodeViewer {

    // Done by Obaidah and Sahon
    public void showCodePhysicsEquations() {
        Stage codeWindow = new Stage();
        //Add the code here
        String codeSnippet = "public TextFlow createEnergyDisplayUphill(Path trackdecline, double velocity, int friction, double mass, PathTransition pathTransition) {\n" +
                "        Text keText = new Text(\"Kinetic Energy: 0 kJ\\n\");\n" +
                "        Text peText = new Text(\"Potential Energy: 0 kJ\\n\");\n" +
                "        Text meText = new Text(\"Mechanical Energy: 0 kJ\\n\");\n" +
                "\n" +
                "        keText.setFont(new Font(\"Monaco\", 18));\n" +
                "        peText.setFont(new Font(\"Monaco\", 18));\n" +
                "        meText.setFont(new Font(\"Monaco\", 18));\n" +
                "\n" +
                "        TextFlow energyDisplay = new TextFlow(keText, peText, meText);\n" +
                "        energyDisplay.setPadding(new Insets(10));\n" +
                "        energyDisplay.setStyle(\"-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-width: 1px;\");\n" +
                "\n" +
                "\n" +
                "        //.currentTimeProperty serves to update the time as the animation progresses\n" +
                "        // .addListener does something whenever a property is change, in this case the time\n" +
                "        // obs is the property being observed, oldtime is the time before the update, newTime is the time after\n" +
                "        pathTransition.currentTimeProperty().addListener((obs, oldTime, newTime) -> {\n" +
                "            double progress = newTime.toSeconds() / pathTransition.getTotalDuration().toSeconds(); // finds how mch % of the animation is done\n" +
                "\n" +
                "            double x = 50 + progress * (750 - 50); // initialX + distance done so far ; 750 - 50 represents the distance of the track\n" +
                "            double y = 500 - progress * (500 - 100);\n" +
                "\n" +
                "            double startHeight = 100;\n" +
                "            double endHeight = 500;\n" +
                "            double distance = endHeight - startHeight;\n" +
                "\n" +
                "            double height = startHeight + progress * (distance);\n" +
                "\n" +
                "            double totalVelocity = velocity - friction;\n" +
                "            double ke = (0.5 * mass * totalVelocity * totalVelocity)/1000;\n" +
                "            double pe = (mass * 9.8 * height)/1000;\n" +
                "            double me = ke + pe;\n" +
                "\n" +
                "\n" +
                "            keText.setText(String.format(\"Kinetic Energy: %.2f kJ\\n\", ke));\n" +
                "            peText.setText(String.format(\"Potential Energy: %.2f kJ\\n\", pe));\n" +
                "            meText.setText(String.format(\"Mechanical Energy: %.2f kJ\\n\", me));\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "        pathTransition.setOnFinished(event -> {\n" +
                "            keText.setText(\"Kinetic Energy: Finished\\n\");\n" +
                "            peText.setText(\"Potential Energy: Finished\\n\");\n" +
                "            meText.setText(\"Mechanical Energy: Finished\\n\");\n" +
                "        });\n" +
                "\n" +
                "        return energyDisplay;\n" +
                "    }\n" +
                "\n" +
                "    //By Sahon\n" +
                "    public TextFlow energyDisplayComboTrack(Path trackdecline, double velocity, int friction, double mass, PathTransition pathTransition) {\n" +
                "        Text keText = new Text(\"Kinetic Energy: 0 kJ\\n\");\n" +
                "        Text peText = new Text(\"Potential Energy: 0 kJ\\n\");\n" +
                "        Text meText = new Text(\"Mechanical Energy: 0 kJ\\n\");\n" +
                "\n" +
                "        keText.setFont(new Font(\"Monaco\", 18));\n" +
                "        peText.setFont(new Font(\"Monaco\", 18));\n" +
                "        meText.setFont(new Font(\"Monaco\", 18));\n" +
                "\n" +
                "        TextFlow energyDisplay = new TextFlow(keText, peText, meText);\n" +
                "        energyDisplay.setPadding(new Insets(10));\n" +
                "        energyDisplay.setStyle(\"-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 1px;\");\n" +
                "\n" +
                "        //.currentTimeProperty serves to update the time as the animation progresses\n" +
                "        // .addListener does something whenever a property is change, in this case the time\n" +
                "        // obs is the property being observed, oldtime is the time before the update, newTime is the time after\n" +
                "        pathTransition.currentTimeProperty().addListener((obs, oldTime, newTime) -> {\n" +
                "            double progress = newTime.toSeconds() / pathTransition.getTotalDuration().toSeconds(); // finds how mch % of the animation is done\n" +
                "\n" +
                "            double x = 50 + progress * (750 - 50); // initialX + distance done so far ; 750 - 50 represents the distance of the track\n" +
                "            double y = 100 + progress * (500 - 100);\n" +
                "\n" +
                "            double currentHeight = findHeight(progress);\n" +
                "\n" +
                "            double totalVelocity = velocity - friction;\n" +
                "            double height = 600 - currentHeight;\n" +
                "            double ke = (0.5 * mass * totalVelocity * totalVelocity)/1000;\n" +
                "            double pe = (mass * 9.8 * height)/1000;\n" +
                "            double me = ke + pe;\n" +
                "\n" +
                "\n" +
                "            keText.setText(String.format(\"Kinetic Energy: %.2f kJ\\n\", ke));\n" +
                "            peText.setText(String.format(\"Potential Energy: %.2f kJ\\n\", pe));\n" +
                "            meText.setText(String.format(\"Mechanical Energy: %.2f kJ\\n\", me));\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "        pathTransition.setOnFinished(event -> {\n" +
                "            keText.setText(\"Kinetic Energy: Finished\\n\");\n" +
                "            peText.setText(\"Potential Energy: Finished\\n\");\n" +
                "            meText.setText(\"Mechanical Energy: Finished\\n\");\n" +
                "        });\n" +
                "\n" +
                "        return energyDisplay;\n" +
                "    }\n" +
                "\n" +
                "    //By Sahon\n" +
                "    public double findHeight(double progress) {\n" +
                "        double[] heightPerPart = {200, 200, 150, 150, 200, 200};\n" +
                "\n" +
                "        double amountOfParts = heightPerPart.length - 1;\n" +
                "        double totalProgress = progress * amountOfParts; // Allows us to see which specifically where the car is in the entire track\n" +
                "        int whichPartCarIsOn = (int) totalProgress; // Rounds up the totalProgress to see which part the car is on\n" +
                "        double progressOnSpecificPart = totalProgress - whichPartCarIsOn; // Sees the place to see the specific place of the car in a specific part\n" +
                "\n" +
                "        double y1 = heightPerPart[whichPartCarIsOn];\n" +
                "        double y2 = heightPerPart[whichPartCarIsOn + 1];\n" +
                "        return y1 + (y2 - y1) * progressOnSpecificPart;\n" +
                "    }";

        Text codeArea = new Text(codeSnippet);

        ScrollPane scrollPane = new ScrollPane(codeArea);

        // Hide the scrollbars but keep scrolling functional
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Scene codeScene = new Scene(scrollPane, 750, 750);
        codeWindow.setTitle("PhysicsEquations");
        codeWindow.setScene(codeScene);
        codeWindow.show();
    }

    //By Obaidah
    public void showCodeAnimate() {
        Stage codeWindow = new Stage();
        //Add the code here
        String codeSnippet = " public Pane comboTrackAnimation(double mass, double velocity, Path combo, int friction, BorderPane root, ImageView carType, StackPane center) {\n" +
                "        Pane pane = new Pane();\n" +
                "        PhysicsEquations equations = new PhysicsEquations();\n" +
                "        pane.setPrefSize(800, 600);\n" +
                "\n" +
                "        // Center the path in the pane\n" +
                "        double centerX = (pane.getPrefWidth() - combo.getBoundsInLocal().getWidth()) / 2;\n" +
                "        double centerY = (pane.getPrefHeight() - combo.getBoundsInLocal().getHeight()) / 2;\n" +
                "\n" +
                "        combo.setTranslateX(centerX);\n" +
                "        combo.setTranslateY(centerY);\n" +
                "\n" +
                "        // Creating a new path that stops at a certain point instead of the car doing the full animation\n" +
                "        Path subPath = new Path();\n" +
                "        subPath.setStroke(Color.BLACK);\n" +
                "        subPath.setStrokeWidth(2);\n" +
                "        double width = center.getWidth();\n" +
                "        double height = center.getHeight();\n" +
                "\n" +
                "        subPath.getElements().add(new MoveTo(center.getLayoutX()*3/5-5, height*2/3)); // Start point\n" +
                "        subPath.getElements().add(new LineTo(center.getLayoutX()*3/5+(width/5), height*2/3)); // Add first segment\n" +
                "\n" +
                "        subPath.getElements().add(new LineTo(center.getLayoutX()*3/5+(2*width/5), height/3)); // Add second segment\n" +
                "        subPath.getElements().add(new LineTo(center.getLayoutX()*3/5+(3*width/5), height/3)); // Add third segment\n" +
                "        subPath.getElements().add(new LineTo(center.getLayoutX()*3/5+(4*width/5), height*2/3)); // Add fourth segment\n" +
                "        subPath.getElements().add(new LineTo(center.getLayoutX()*3/5+(5*width/5)-10, height*2/3)); // Stop here\n" +
                "\n" +
                "        // Centering\n" +
                "        subPath.setTranslateX(centerX);\n" +
                "        subPath.setTranslateY(centerY);\n" +
                "\n" +
                "        pane.getChildren().addAll(combo, carType);\n" +
                "\n" +
                "\n" +
                "        // PathTransition for the car animation\n" +
                "        pathTransition = new PathTransition();\n" +
                "        pathTransition.setPath(subPath);\n" +
                "        pathTransition.setNode(carType);\n" +
                "        pathTransition.setInterpolator(Interpolator.LINEAR);\n" +
                "        pathTransition.setDuration(Duration.seconds(subPath.getBoundsInLocal().getWidth() / velocity));\n" +
                "        pathTransition.setCycleCount(1);\n" +
                "\n" +
                "        TextFlow energyDisplay = equations.energyDisplayComboTrack(combo, velocity, friction, mass, pathTransition);\n" +
                "        root.setBottom(energyDisplay);\n" +
                "\n" +
                "        return pane;\n" +
                "    }\n" +
                "\n" +
                "    //By Sahon\n" +
                "    public Pane animateIncline(double mass, double velocity, Path incline, int friction, BorderPane root, ImageView carType) {\n" +
                "        Pane pane = new Pane();\n" +
                "        PhysicsEquations equations = new PhysicsEquations();\n" +
                "        pane.setPrefSize(800, 600);\n" +
                "\n" +
                "        double centerX = (pane.getPrefWidth() - incline.getBoundsInLocal().getWidth()) / 2;\n" +
                "        double centerY = (pane.getPrefHeight() - incline.getBoundsInLocal().getHeight()) / 2;\n" +
                "\n" +
                "        incline.setTranslateX(centerX);\n" +
                "        incline.setTranslateY(centerY);\n" +
                "\n" +
                "        // Creating the new path for the car to follow\n" +
                "        Path trackincline = new Path();\n" +
                "        trackincline.setStroke(Color.BLACK);\n" +
                "        trackincline.setStrokeWidth(2);\n" +
                "        MoveTo start = new MoveTo(190, 530);\n" +
                "        LineTo summit = new LineTo(1340, 150); // Highest point\n" +
                "\n" +
                "        trackincline.setTranslateX(centerX);\n" +
                "        trackincline.setTranslateY(centerY);\n" +
                "\n" +
                "        trackincline.getElements().addAll(start, summit);\n" +
                "\n" +
                "        pane.getChildren().addAll(incline, carType);\n" +
                "\n" +
                "        // PathTransition for the car animation\n" +
                "        pathTransition = new PathTransition();\n" +
                "        pathTransition.setPath(trackincline);\n" +
                "        pathTransition.setNode(carType);\n" +
                "        pathTransition.setInterpolator(Interpolator.LINEAR);\n" +
                "        pathTransition.setDuration(Duration.seconds(trackincline.getBoundsInLocal().getWidth() / velocity));\n" +
                "        pathTransition.setCycleCount(1);\n" +
                "\n" +
                "\n" +
                "        TextFlow energyDisplay = equations.createEnergyDisplayUphill(incline, velocity, friction, mass, pathTransition);\n" +
                "        root.setBottom(energyDisplay);\n" +
                "\n" +
                "        return pane;\n" +
                "    }";

        Text codeArea = new Text(codeSnippet);

        ScrollPane scrollPane = new ScrollPane(codeArea);

        // Hide the scrollbars but keep scrolling functional
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Scene codeScene = new Scene(scrollPane, 750, 750);
        codeWindow.setTitle("Animate");
        codeWindow.setScene(codeScene);
        codeWindow.show();
    }

    //By Obaidah
    public void showCodeModel() {
        Stage codeWindow = new Stage();
        //Add the code here
        String codeSnippet = "public Path inclinettrack(StackPane center) {\n" +
                "\n" +
                "        double width = center.getWidth();\n" +
                "        double height = center.getHeight();\n" +
                "\n" +
                "        System.out.println(width);\n" +
                "        System.out.println(height);\n" +
                "\n" +
                "        Path trackincline = new Path(\n" +
                "\n" +
                "                new MoveTo(190,530),\n" +
                "                new LineTo(1340,150),\n" +
                "                new LineTo(1340,530),\n" +
                "                new LineTo(190,530),\n" +
                "\n" +
                "\n" +
                "                new ClosePath()\n" +
                "        );\n" +
                "\n" +
                "\n" +
                "        trackincline.setFill(Color.GREEN);\n" +
                "\n" +
                "        trackincline.setStroke(Color.BLACK);\n" +
                "        trackincline.setStrokeWidth(2);\n" +
                "\n" +
                "        // Old Version done by Vinith\n" +
                "        //Define the points of the track\n" +
                "        //Add all points to the Path\n" +
                "        /*\n" +
                "        angle = 20;\n" +
                "        Rotate rotate = new Rotate((-1*angle),50,50);\n" +
                "        distance = 200;\n" +
                "\n" +
                "        Path trackincline = new Path(\n" +
                "                new MoveTo(50,50),\n" +
                "                new LineTo(50+distance,50)\n" +
                "        );\n" +
                "\n" +
                "        trackincline.setStroke(Color.BLACK);\n" +
                "        trackincline.getTransforms().addAll(rotate);\n" +
                "\n" +
                "        Double accelerationincline = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,\"uphill\");\n" +
                "        ArrayList <Double> pointsincline = pe.findPoints(50,50,distance,angle, \"uphill\");\n" +
                "        ArrayList <Double> timesAtPointsincline = pe.timeAtPoint(ms.initialVelocity,distance,ms.engineAcceleration,angle,0.5,\"uphill\");\n" +
                "\n" +
                "\n" +
                "        animate.timelineanimation(pointsincline, timesAtPointsincline);\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "         */\n" +
                "        return trackincline;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    //By Vinith and Sahon\n" +
                "   public Path combotrack(StackPane center) {\n" +
                "\n" +
                "\n" +
                "       //Simplified the track\n" +
                "\n" +
                "       Path combo = new Path();\n" +
                "       combo.setStroke(Color.BLACK); // Outline color\n" +
                "       combo.setStrokeWidth(2);\n" +
                "       combo.setFill(Color.GREEN);\n" +
                "\n" +
                "       double width = center.getWidth();\n" +
                "       double height = center.getHeight();\n" +
                "\n" +
                "\n" +
                "       // Start point\n" +
                "       combo.getElements().add(new MoveTo(center.getLayoutX()*3/5+15, height*2/3)); // Bottom-left start point\n" +
                "       System.out.println(center.getLayoutX());\n" +
                "\n" +
                "\n" +
                "       // Top track coordinates\n" +
                "       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(width/5), height*2/3));\n" +
                "       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(2*width/5), height*1/3));\n" +
                "       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(3*width/5), height*1/3));\n" +
                "       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(4*width/5), height*2/3));\n" +
                "       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(5*width/5)-10, height*2/3));\n" +
                "\n" +
                "       // Close the shape to the bottom edge\n" +
                "       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(5*width/5)-10, height)); // Bottom-right\n" +
                "       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+15, height));   // Bottom-left\n" +
                "       combo.getElements().add(new ClosePath());" +
                "return combo;\n" +
                "    }";

        Text codeArea = new Text(codeSnippet);

        ScrollPane scrollPane = new ScrollPane(codeArea);

        // Hide the scrollbars but keep scrolling functional
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Scene codeScene = new Scene(scrollPane, 750, 750);
        codeWindow.setTitle("Model");
        codeWindow.setScene(codeScene);
        codeWindow.show();
    }

    //By Obaidah
    public void showCodeMainScreen() {
        Stage codeWindow = new Stage();
        //Add the code here
        String codeSnippet = "public class MainScreen {\n" +
                "    public BorderPane root = new BorderPane();\n" +
                "    int vehicleMass = 0;\n" +
                "    public int totalVelocity = 0; // enginetype\n" +
                "    int totalFriction = 0; // tire (increase) + weather (depends on type of track)\n" +
                "    Path chosenTrack;\n" +
                "    Pane chosenAnimation;\n" +
                "    ImageView chosenCar;\n" +
                "\n" +
                "    public BorderPane initialize() {\n" +
                "        // Done By Sahon\n" +
                "        Font menuButtonFont = Font.font(\"Arial\", 14);// FontWeight.BOLD,\n" +
                "        MenuBar menuBar = new MenuBar();\n" +
                "        Animate animate = new Animate();\n" +
                "        StackPane center = new StackPane();\n" +
                "        CodeViewer codeViewer = new CodeViewer();\n" +
                "\n" +
                "        Menu showCode = new Menu(\"Show Code\");\n" +
                "        MenuItem physicsEquations = new MenuItem(\"PhysicsEquations\");\n" +
                "        physicsEquations.setOnAction(e -> { codeViewer.showCodePhysicsEquations(); });\n" +
                "        MenuItem animateCode = new MenuItem(\"Animate\");\n" +
                "        animateCode.setOnAction(e -> { codeViewer.showCodeAnimate(); });\n" +
                "        MenuItem modelClass = new MenuItem(\"Tracks\");\n" +
                "        modelClass.setOnAction(e -> { codeViewer.showCodeModel(); });\n" +
                "        MenuItem mainScreen = new MenuItem(\"MainScreen\");\n" +
                "        mainScreen.setOnAction(e -> { codeViewer.showCodeMainScreen(); });\n" +
                "\n" +
                "        showCode.getItems().addAll(physicsEquations, animateCode, modelClass, mainScreen);\n" +
                "\n" +
                "        // Done by Vinith\n" +
                "        Menu showAssets = new Menu(\"Show Assets and Images\");\n" +
                "        MenuItem lamboview = new MenuItem(\"Lamborghini\");\n" +
                "        lamboview.setOnAction(event -> {showLambo();});\n" +
                "        MenuItem cybertruck = new MenuItem(\"Cybertruck\");\n" +
                "        cybertruck.setOnAction(event -> {showCybertruck();});\n" +
                "        MenuItem normaltruck = new MenuItem(\"Truck\");\n" +
                "        normaltruck.setOnAction(event -> {showTruck();});\n" +
                "        showAssets.getItems().addAll(lamboview,cybertruck,normaltruck);\n" +
                "\n" +
                "        // Done by Sahon\n" +
                "        Menu changeTheme = new Menu(\"Change Theme\");\n" +
                "        MenuItem enableLightMode = new MenuItem(\"Enable Light Mode\");\n" +
                "        MenuItem enableDarkMode = new MenuItem(\"Enable Dark Mode\");\n" +
                "        changeTheme.getItems().addAll(enableLightMode, enableDarkMode);\n" +
                "\n" +
                "        // Done by Obaidah\n" +
                "        Menu userGuide = new Menu(\"Help/User Guide\");\n" +
                "        MenuItem viewUserGuide = new MenuItem(\"View User Guide\");\n" +
                "        viewUserGuide.setOnAction(e -> {\n" +
                "            Stage userGuideWindow = new Stage();\n" +
                "            //Add user guide text here\n" +
                "            String text = \"\\n   Welcome to the Car Simulation Application!\\n\" +\n" +
                "                    \"\\n\\n\" +\n" +
                "                    \"  - Navigation\\n\" +\n" +
                "                    \"\\n\" +\n" +
                "                    \"\\tUse the Menu Bar at the top to access features.\\n\" +\n" +
                "                    \"\\tClick on buttons for specific actions.\\n\\n\" +\n" +
                "                    \"  - Functions\\n\" +\n" +
                "                    \"\\n\" +\n" +
                "                    \"\\tUse the menus on the left to modify the simulation.\\n\" +\n" +
                "                    \"\\tFor example, click on \\\"Change Track\\\" to select a type of track.\\n\" +\n" +
                "                    \"\\tMake sure to select at least one item from each menu\\n\" +\n" +
                "                    \"\\tbefore running.\\n\" +\n" +
                "                    \"\\n\" +\n" +
                "                    \"  - Exit\\n\" +\n" +
                "                    \"\\n\" +\n" +
                "                    \"\\tClick the \\\"X\\\" button on the top right corner or \" +\n" +
                "                    \"\\n\\tpress Alt + F4 to close the application.\\n\" +\n" +
                "                    \"\\n  - Need More Help?\\n\" +\n" +
                "                    \"\\n\" +\n" +
                "                    \"\\tSee the full \\\"User Guide\\\" document that comes with\\n\" +\n" +
                "                    \"\\tthe application.\\n\" +\n" +
                "                    \"\\n\\tEnjoy driving!\\n\\n\\n\" +\n" +
                "                    \"\\tDeveloped by: Team Vroom\\n\";\n" +
                "\n" +
                "            Text userGuideText = new Text(text);\n" +
                "\n" +
                "            StackPane userGuideRoot = new StackPane(userGuideText);\n" +
                "            ScrollPane scrollPane = new ScrollPane(userGuideRoot);\n" +
                "\n" +
                "            // Hide the scrollbars but keep scrolling functional\n" +
                "            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);\n" +
                "            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);\n" +
                "            Scene scene = new Scene(scrollPane, 400, 300);\n" +
                "            userGuideWindow.setTitle(\"User Guide\");\n" +
                "            userGuideWindow.setScene(scene);\n" +
                "            userGuideWindow.show();\n" +
                "        });\n" +
                "        userGuide.getItems().addAll(viewUserGuide);\n" +
                "\n" +
                "        menuBar.getMenus().addAll(showCode, showAssets, changeTheme, userGuide);\n" +
                "\n" +
                "        //Done by Sahon\n" +
                "        MenuButton changeCar = new MenuButton(\"Change Car\");\n" +
                "        MenuItem lambomenuitem = new MenuItem(\"Lamborghini\");\n" +
                "        MenuItem cybertruckmenuitem = new MenuItem(\"Cybertruck\");\n" +
                "        MenuItem truckmenuitem = new MenuItem(\"Truck\");\n" +
                "        lambomenuitem.setOnAction(actionEvent -> {\n" +
                "            CarSkins carSkins = new CarSkins();\n" +
                "            vehicleMass = 1520;\n" +
                "            chosenCar = carSkins.lamborghiniSkin();\n" +
                "        });\n" +
                "        cybertruckmenuitem.setOnAction(actionEvent -> {\n" +
                "            CarSkins carSkins = new CarSkins();\n" +
                "            vehicleMass = 2500;\n" +
                "            chosenCar = carSkins.cybertruckSkin();\n" +
                "        });\n" +
                "        truckmenuitem.setOnAction(actionEvent -> {\n" +
                "            CarSkins carSkins = new CarSkins();\n" +
                "            vehicleMass += 4500;\n" +
                "            chosenCar = carSkins.regulartruckSkin();\n" +
                "        });\n" +
                "        changeCar.getItems().addAll(lambomenuitem,cybertruckmenuitem, truckmenuitem);\n" +
                "\n" +
                "        MenuButton changeEngine = new MenuButton(\"Change Engine\");\n" +
                "        MenuItem strongEngine = new MenuItem(\"Engine Ultra S-500\");\n" +
                "        MenuItem weakEngine = new MenuItem(\"Engine F-001\");\n" +
                "        weakEngine.setOnAction(actionEvent -> {totalVelocity += 10;});\n" +
                "        strongEngine.setOnAction(actionEvent -> {totalVelocity += 50;});\n" +
                "        changeEngine.getItems().addAll(strongEngine, weakEngine);\n" +
                "\n" +
                "        MenuButton changeTires = new MenuButton(\"Change Tires\");\n" +
                "        MenuItem regularTire = new MenuItem(\"Regular Tires\");\n" +
                "        MenuItem winterTire = new MenuItem(\"Winter Tires\");\n" +
                "        changeTires.getItems().addAll(regularTire,winterTire);\n" +
                "        regularTire.setOnAction(actionEvent -> {totalFriction += 5;});\n" +
                "        winterTire.setOnAction(actionEvent -> {totalFriction += 100;});\n" +
                "\n" +
                "        MenuButton changeWeather = new MenuButton(\"Change Weather\");\n" +
                "        MenuItem sunny = new MenuItem(\"Sunny Weather\");\n" +
                "\n" +
                "        // Done by Vinith\n" +
                "        sunny.setOnAction(actionEvent -> {\n" +
                "            // Load the background image (make sure the file path is correct)\n" +
                "            Image sunnybackgroundimage = new Image(\"C:\\\\Users\\\\user\\\\IdeaProjects\\\\CarSimulationProject\\\\src\\\\main\\\\resources\\\\sunnysky.jpg\");\n" +
                "\n" +
                "            // Create a BackgroundImage object for the image\n" +
                "            BackgroundImage backgroundImage = new BackgroundImage(\n" +
                "                    sunnybackgroundimage,\n" +
                "                    BackgroundRepeat.NO_REPEAT, // No repeat\n" +
                "                    BackgroundRepeat.NO_REPEAT, // No repeat\n" +
                "                    BackgroundPosition.CENTER,  // Center the image\n" +
                "                    BackgroundSize.DEFAULT      // Default size (cover the entire space)\n" +
                "            );\n" +
                "\n" +
                "            // Create a Background object with the BackgroundImage\n" +
                "            Background sunnybackground = new Background(backgroundImage);\n" +
                "\n" +
                "            // Set the new background on the StackPane\n" +
                "            center.setBackground(sunnybackground);\n" +
                "\n" +
                "            // Optionally, force a layout update to make sure the background shows\n" +
                "            center.layout(); // This ensures that the layout is recalculated\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "\n" +
                "        // Done by Vinith\n" +
                "        MenuItem rainy = new MenuItem(\"Rainy Weather\");\n" +
                "\n" +
                "        rainy.setOnAction(actionEvent -> {\n" +
                "\n" +
                "\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "        changeWeather.getItems().addAll(sunny, rainy);\n" +
                "        sunny.setOnAction(actionEvent -> {totalFriction += 1;});\n" +
                "        rainy.setOnAction(actionEvent -> {totalFriction += 50;});\n" +
                "\n" +
                "\n" +
                "        // Done by Sahon\n" +
                "        MenuButton changeTrack = new MenuButton(\"Change Track\");\n" +
                "        MenuItem combo = new MenuItem(\"Combo Track\");\n" +
                "        //MenuItem downhill = new MenuItem(\"Downhill Track\");\n" +
                "        MenuItem uphill = new MenuItem(\"Uphill Track\");\n" +
                "        changeTrack.getItems().addAll(combo,  uphill);\n" +
                "\n" +
                "        Trackselections ts = new Trackselections();\n" +
                "\n" +
                "        combo.setOnAction(actionEvent -> {\n" +
                "            Animate animation = new Animate();\n" +
                "            chosenTrack = ts.combotrack(center);\n" +
                "            chosenAnimation = animate.comboTrackAnimation(vehicleMass, totalVelocity, chosenTrack, totalFriction, root, chosenCar, center);\n" +
                "        });\n" +
                "\n" +
                "        /*downhill.setOnAction(actionEvent -> {\n" +
                "            Animate animation = new Animate();\n" +
                "            chosenTrack = ts.declinetrack(center);\n" +
                "            chosenAnimation = animate.animateDecline(vehicleMass, totalVelocity, chosenTrack, totalFriction, root, chosenCar);\n" +
                "        });*/\n" +
                "\n" +
                "        uphill.setOnAction(actionEvent -> {\n" +
                "\n" +
                "            Animate animation = new Animate();\n" +
                "            chosenTrack = ts.inclinettrack(center);\n" +
                "            chosenAnimation = animate.animateIncline(vehicleMass, totalVelocity, chosenTrack, totalFriction, root, chosenCar);\n" +
                "        });\n" +
                "\n" +
                "        Label inputvelocity = new Label(\"Initial Velocity: \");\n" +
                "        inputvelocity.setStyle(\"-fx-font-size: 20px; -fx-font-weight: bold;\");\n" +
                "        TextField userSetVelocity = new TextField();\n" +
                "\n" +
                "        Button go = new Button(\"Go!\");\n" +
                "        Button reset = new Button(\"Reset!\");\n" +
                "\n" +
                "\n" +
                "        // STYLING EVERYTHING\n" +
                "        // By Sahon and Obaidah\n" +
                "        changeCar.setFont(menuButtonFont);\n" +
                "        changeEngine.setFont(menuButtonFont);\n" +
                "        changeTires.setFont(menuButtonFont);\n" +
                "        changeWeather.setFont(menuButtonFont);\n" +
                "        changeTrack.setFont(menuButtonFont);\n" +
                "\n" +
                "        GridPane menuButtonGrid = new GridPane();\n" +
                "        menuButtonGrid.setAlignment(Pos.CENTER);\n" +
                "        menuButtonGrid.setHgap(20);\n" +
                "        menuButtonGrid.setVgap(20);\n" +
                "\n" +
                "        menuButtonGrid.add(changeCar, 0, 0);\n" +
                "        menuButtonGrid.add(changeEngine, 1, 0);\n" +
                "        menuButtonGrid.add(changeTires, 0, 1);\n" +
                "        menuButtonGrid.add(changeWeather, 1, 1);\n" +
                "        menuButtonGrid.add(changeTrack, 0, 2, 2, 1);\n" +
                "        menuButtonGrid.add(userSetVelocity, 1,3,1,1);\n" +
                "        menuButtonGrid.add(inputvelocity,0,3,1,1);\n" +
                "\n" +
                "        VBox vBox = new VBox();\n" +
                "        vBox.setAlignment(Pos.CENTER);\n" +
                "        vBox.getChildren().addAll(menuButtonGrid);\n" +
                "\n" +
                "        HBox energyLevels = new HBox();\n" +
                "        energyLevels.setAlignment(Pos.CENTER);\n" +
                "\n" +
                "        Pane goAndResetButtons = new Pane();\n" +
                "\n" +
                "\n" +
                "        go.setLayoutX(30);\n" +
                "        go.setLayoutY(90);\n" +
                "        go.setPrefSize(80,60);\n" +
                "\n" +
                "        reset.setLayoutX(160);\n" +
                "        reset.setLayoutY(90);\n" +
                "        reset.setPrefSize(80,60);\n" +
                "\n" +
                "        goAndResetButtons.getChildren().addAll(go, reset);\n" +
                "\n" +
                "\n" +
                "        center.setStyle(\"-fx-border-color: black; -fx-border-width: 5px;\");\n" +
                "\n" +
                "        //Setting the default theme\n" +
                "        String originalCenterStyle = center.getStyle();\n" +
                "        String originalRootStyle = root.getStyle();\n" +
                "        String originalMenuBarStyle = menuBar.getStyle();\n" +
                "        //String originalLabelStyle = potentialEnergyLabel.getStyle();\n" +
                "        String originalButtonStyle = changeCar.getStyle();\n" +
                "        String originalOptionStyle = lambomenuitem.getStyle();\n" +
                "        String originalGoAndResetOptionStyle = go.getStyle();\n" +
                "        vBox.getChildren().add(goAndResetButtons);\n" +
                "\n" +
                "        root.setCenter(center);\n" +
                "        root.setTop(menuBar);\n" +
                "        root.setBottom(energyLevels);\n" +
                "        root.setLeft(vBox);\n" +
                "        //root.setRight(goAndResetButtons);\n" +
                "\n" +
                "\n" +
                "        //By Sahon and Obaidah\n" +
                "        // LIGHT MODE COLOR SWITCHES\n" +
                "        enableLightMode.setOnAction(actionEvent -> {\n" +
                "            \n" +
                "            changeCar.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            lambomenuitem.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            cybertruckmenuitem.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            changeEngine.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            strongEngine.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            weakEngine.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            changeTires.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            regularTire.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            winterTire.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            changeWeather.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            rainy.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            sunny.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            changeTrack.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            combo.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            uphill.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "            //downhill.setStyle(\"-fx-background-color: #b5a6c9; -fx-text-fill: #0e1416\");\n" +
                "\n" +
                "            // Reset styles to their original values\n" +
                "            center.setStyle(originalCenterStyle);\n" +
                "            root.setStyle(originalRootStyle);\n" +
                "            menuBar.setStyle(originalMenuBarStyle);\n" +
                "\n" +
                "            changeCar.setStyle(originalButtonStyle);\n" +
                "            lambomenuitem.setStyle(originalOptionStyle);\n" +
                "            cybertruckmenuitem.setStyle(originalOptionStyle);\n" +
                "            truckmenuitem.setStyle(originalOptionStyle);\n" +
                "            changeEngine.setStyle(originalButtonStyle);\n" +
                "            strongEngine.setStyle(originalOptionStyle);\n" +
                "            weakEngine.setStyle(originalOptionStyle);\n" +
                "            changeTires.setStyle(originalButtonStyle);\n" +
                "            regularTire.setStyle(originalOptionStyle);\n" +
                "            winterTire.setStyle(originalOptionStyle);\n" +
                "            changeWeather.setStyle(originalButtonStyle);\n" +
                "            rainy.setStyle(originalOptionStyle);\n" +
                "            sunny.setStyle(originalOptionStyle);\n" +
                "            changeTrack.setStyle(originalButtonStyle);\n" +
                "            combo.setStyle(originalOptionStyle);\n" +
                "            uphill.setStyle(originalOptionStyle);\n" +
                "            //downhill.setStyle(originalOptionStyle);\n" +
                "            go.setStyle(originalGoAndResetOptionStyle);\n" +
                "            reset.setStyle(originalGoAndResetOptionStyle);\n" +
                "        });\n" +
                "\n" +
                "        //By Sahon and Obaidah\n" +
                "        enableDarkMode.setOnAction(actionEvent -> {\n" +
                "\n" +
                "\n" +
                "            center.setStyle(\"-fx-border-color: #65446f; -fx-border-width: 5px; -fx-background-color: #2f333d;\");\n" +
                "            root.setStyle(\"-fx-background-color: #272930\");\n" +
                "            menuBar.setStyle(\"-fx-background-color: #77777c\");\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "            changeCar.setStyle(\"-fx-background-color: #6734ac; -fx-text-fill: #e9eff1\");\n" +
                "            lambomenuitem.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            cybertruckmenuitem.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            truckmenuitem.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            changeEngine.setStyle(\"-fx-background-color: #6734ac; -fx-text-fill: #e9eff1\");\n" +
                "            strongEngine.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            weakEngine.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            changeTires.setStyle(\"-fx-background-color: #6734ac; -fx-text-fill: #e9eff1\");\n" +
                "            regularTire.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            winterTire.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            changeWeather.setStyle(\"-fx-background-color: #6734ac; -fx-text-fill: #e9eff1\");\n" +
                "            rainy.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            sunny.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            changeTrack.setStyle(\"-fx-background-color: #6734ac; -fx-text-fill: #e9eff1\");\n" +
                "            combo.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            uphill.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            //downhill.setStyle(/*\"-fx-background-color: #453659;*/\" -fx-text-fill: #131542\");\n" +
                "            go.setStyle(\"-fx-background-color: #2b5624; -fx-text-fill: #e9eff1\");\n" +
                "            reset.setStyle(\"-fx-background-color: #660a0e; -fx-text-fill: #e9eff1\");\n" +
                "\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "        // By Sahon\n" +
                "        go.setOnAction(actionEvent -> {\n" +
                "            totalVelocity += Integer.parseInt(userSetVelocity.getText());\n" +
                "\n" +
                "            Animate animation = new Animate();\n" +
                "            animation.setLayoutX(400);\n" +
                "            animation.setLayoutY(300);\n" +
                "\n" +
                "            center.getChildren().clear();\n" +
                "            center.getChildren().add(chosenAnimation);\n" +
                "            center.setAlignment(Pos.CENTER);\n" +
                "            animate.getPathTransition().play(); // We take the pathTransition from the animate class, that is being set by the comboTrackAnimation method\n" +
                "\n" +
                "        });\n" +
                "\n" +
                "        //By Sahon\n" +
                "        reset.setOnAction(e-> {\n" +
                "            totalVelocity = 0;\n" +
                "            center.getChildren().clear();\n" +
                "            animate.getPathTransition().stop();\n" +
                "            root.setBottom(null);\n" +
                "        });\n" +
                "\n" +
                "\n" +
                "        return root;\n" +
                "    }\n" +
                "\n" +
                "    // All show methods done by Vinith\n" +
                "    private void showLambo() {\n" +
                "\n" +
                "        Stage imageWindow = new Stage();\n" +
                "\n" +
                "        Image lamboimage = new Image(\"lambo.png\");\n" +
                "\n" +
                "        ImageView lamboView = new ImageView(lamboimage);\n" +
                "\n" +
                "\n" +
                "        lamboView.setFitWidth(300);\n" +
                "        lamboView.setFitHeight(200);\n" +
                "        lamboView.setPreserveRatio(true);\n" +
                "\n" +
                "        StackPane carRoot = new StackPane(lamboView);\n" +
                "        Scene lamboScene = new Scene(carRoot, 400, 300);\n" +
                "        imageWindow.setTitle(\"Lamborghini\");\n" +
                "        imageWindow.setScene(lamboScene);\n" +
                "        imageWindow.show();\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    private void showCybertruck() {\n" +
                "\n" +
                "        Stage imageWindow = new Stage();\n" +
                "\n" +
                "        Image cyberimage = new Image(\"cybertruck.png\");\n" +
                "\n" +
                "        ImageView cyberView = new ImageView(cyberimage);\n" +
                "\n" +
                "\n" +
                "        cyberView.setFitWidth(300);\n" +
                "        cyberView.setFitHeight(200);\n" +
                "        cyberView.setPreserveRatio(true);\n" +
                "\n" +
                "        StackPane carRoot = new StackPane(cyberView);\n" +
                "        Scene cyberScene = new Scene(carRoot, 400, 300);\n" +
                "        imageWindow.setTitle(\"Cybertruck\");\n" +
                "        imageWindow.setScene(cyberScene);\n" +
                "        imageWindow.show();\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    private void showTruck() {\n" +
                "\n" +
                "        Stage imageWindow = new Stage();\n" +
                "\n" +
                "        Image truckimage = new Image(\"truck.png\");\n" +
                "\n" +
                "        ImageView truckView = new ImageView(truckimage);\n" +
                "\n" +
                "\n" +
                "        truckView.setFitWidth(300);\n" +
                "        truckView.setFitHeight(200);\n" +
                "        truckView.setPreserveRatio(true);\n" +
                "\n" +
                "        StackPane carRoot = new StackPane(truckView);\n" +
                "        Scene truckScene = new Scene(carRoot, 400, 300);\n" +
                "        imageWindow.setTitle(\"Truck\");\n" +
                "        imageWindow.setScene(truckScene);\n" +
                "        imageWindow.show();\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "\n" +
                "}";

        Text codeArea = new Text(codeSnippet);

        ScrollPane scrollPane = new ScrollPane(codeArea);

        // Hide the scrollbars but keep scrolling functional
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Scene codeScene = new Scene(scrollPane, 750, 750);
        codeWindow.setTitle("MainScreen");
        codeWindow.setScene(codeScene);
        codeWindow.show();
    }
}
