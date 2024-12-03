package com.example.carsimulationproject.Model;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import javafx.animation.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Animate extends Pane {

    Rectangle car = new Rectangle(30, 50, 40, 20);


    public Animate() {

    //setting the vehicule properties like setting the engine

        car.setFill(Color.BLUE);


        this.getChildren().addAll(car);


    }

    public void timelineanimation(ArrayList<Double> arrayListpositions,  ArrayList<Double> listTime) {

        if (arrayListpositions.isEmpty()) {
            throw new IllegalStateException("Points must be generated before animating.");
        }

        KeyValue initvaluex = new KeyValue(car.translateXProperty(), arrayListpositions.get(0));
        KeyValue initvaluey = new KeyValue(car.translateYProperty(), arrayListpositions.get(1));
        KeyFrame keyFrameinit = new KeyFrame(Duration.seconds(listTime.get(0)), initvaluex, initvaluey);

        KeyValue valuex2 = new KeyValue(car.translateXProperty(), arrayListpositions.get(2));
        KeyValue valuey2 = new KeyValue(car.translateYProperty(), arrayListpositions.get(3));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(listTime.get(1)), valuex2, valuey2);

        KeyValue valuex3 = new KeyValue(car.translateXProperty(), arrayListpositions.get(4));
        KeyValue valuey3 = new KeyValue(car.translateYProperty(), arrayListpositions.get(5));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(listTime.get(2)), valuex3, valuey3);

        KeyValue valuex4 = new KeyValue(car.translateXProperty(), arrayListpositions.get(6));
        KeyValue valuey4 = new KeyValue(car.translateYProperty(), arrayListpositions.get(7));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(listTime.get(3)), valuex4, valuey4);

        KeyValue valuex5 = new KeyValue(car.translateXProperty(), arrayListpositions.get(8));
        KeyValue valuey5 = new KeyValue(car.translateYProperty(), arrayListpositions.get(9));
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(listTime.get(4)), valuex5, valuey5);

        Timeline timeline = new Timeline(keyFrameinit, keyFrame2, keyFrame3, keyFrame4, keyFrame5);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }





    public Pane comboTrackAnimation(double mass, double velocity, Path combo, int friction, BorderPane root) {
        Pane pane = new Pane();
        PhysicsEquations equations = new PhysicsEquations();
        pane.setPrefSize(800, 600);

        // Rectangle representing the car
        Rectangle car = new Rectangle(50, 30, Color.RED);

        // Center the path in the pane
        double centerX = (pane.getPrefWidth() - combo.getBoundsInLocal().getWidth()) / 2;
        double centerY = (pane.getPrefHeight() - combo.getBoundsInLocal().getHeight()) / 2;

        combo.setTranslateX(centerX);
        combo.setTranslateY(centerY);

        // Create a sub-path up to the desired point
        Path subPath = new Path();
        subPath.setStroke(Color.BLACK);
        subPath.setStrokeWidth(2);

        // Add path elements up to the stopping point
        subPath.getElements().add(new MoveTo(0, 200)); // Start point
        subPath.getElements().add(new LineTo(200, 200)); // Add first segment
        subPath.getElements().add(new LineTo(300, 150)); // Add second segment
        subPath.getElements().add(new LineTo(400, 150)); // Add third segment
        subPath.getElements().add(new LineTo(500, 200)); // Add fourth segment
        subPath.getElements().add(new LineTo(700, 200)); // Stop here

        // Center the subPath
        subPath.setTranslateX(centerX);
        subPath.setTranslateY(centerY);

        // Add the car to the pane
        pane.getChildren().addAll(combo, car);

//        // Text display for energy (optional)
//        Text energyDisplay = new Text(10, 500, "Energy: ");
//        energyDisplay.setFill(Color.BLACK);
//        pane.getChildren().add(energyDisplay);

        // PathTransition for the car animation
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(subPath);
        pathTransition.setNode(car);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setDuration(Duration.seconds(subPath.getBoundsInLocal().getWidth() / velocity));
        pathTransition.setCycleCount(1);

        // Play the animation
        pathTransition.play();

        TextFlow energyDisplay = equations.energyDisplayComboTrack(combo, velocity, friction, mass, pathTransition);
        root.setBottom(energyDisplay);

        return pane;
    }


    public Pane animateIncline(double velocity, Path incline) {
        Pane pane = new Pane();
        pane.setPrefSize(800, 600);

        Rectangle car = new Rectangle(50, 30, Color.RED);
        double centerX = (pane.getPrefWidth() - incline.getBoundsInLocal().getWidth()) / 2;
        double centerY = (pane.getPrefHeight() - incline.getBoundsInLocal().getHeight()) / 2;

        incline.setTranslateX(centerX);
        incline.setTranslateY(centerY);

        Path trackincline = new Path();
        trackincline.setStroke(Color.BLACK);
        trackincline.setStrokeWidth(2);

        // Define the points of the incline track
        MoveTo start = new MoveTo(0, 500);
        LineTo summit = new LineTo(700, 100); // Summit

        trackincline.setTranslateX(centerX);
        trackincline.setTranslateY(centerY);

        trackincline.getElements().addAll(start, summit);


        // Center the path in the pane
        /*double centerX = (pane.getPrefWidth() - trackincline.getBoundsInLocal().getWidth()) / 2;
        double centerY = (pane.getPrefHeight() - trackincline.getBoundsInLocal().getHeight()) / 2;*/

        /*trackincline.setTranslateX(centerX);
        trackincline.setTranslateY(centerY);*/

        pane.getChildren().addAll(incline, car);

        // PathTransition for the car animation
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(trackincline);
        pathTransition.setNode(car);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setDuration(Duration.seconds(trackincline.getBoundsInLocal().getWidth() / velocity));
        pathTransition.setCycleCount(1);

        // Play the animation
        pathTransition.play();

        return pane;
    }

    public Pane animateDecline(int mass, double velocity, Path decline, int friction, BorderPane root) {
        PhysicsEquations equations = new PhysicsEquations();
        Pane pane = new Pane();
        pane.setPrefSize(800, 600);

        // Create the car rectangle
        Rectangle car = new Rectangle(30, 20, Color.BLUE);

        // Define the track
        Path trackdecline = new Path();
        trackdecline.setStroke(Color.BLACK);
        trackdecline.setStrokeWidth(2);

        MoveTo start = new MoveTo(50, 100);
        LineTo bottom = new LineTo(750, 500);
        trackdecline.getElements().addAll(start, bottom);

        double centerX = (pane.getPrefWidth() - trackdecline.getBoundsInLocal().getWidth()) / 2;
        double centerY = (pane.getPrefHeight() - trackdecline.getBoundsInLocal().getHeight()) / 2;

        trackdecline.setTranslateX(centerX);
        trackdecline.setTranslateY(centerY);

        pane.getChildren().addAll(trackdecline, car);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(trackdecline);
        pathTransition.setNode(car);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setDuration(Duration.seconds(trackdecline.getBoundsInLocal().getWidth() / (velocity - friction)));
        pathTransition.setCycleCount(1);

        pathTransition.play();

        TextFlow energyDisplay = equations.createEnergyDisplay(decline, velocity, friction, mass, pathTransition);
        root.setBottom(energyDisplay);

        return pane;
    }





    //running the animation
    public void playanimation() {
       // timelineanimation().play();
    }

    //stopping the animation
    public void stopanimation() {
       // timelineanimation().stop();
    }

}
