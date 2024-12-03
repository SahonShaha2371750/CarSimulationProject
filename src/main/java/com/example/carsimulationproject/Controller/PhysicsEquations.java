package com.example.carsimulationproject.Controller;

import com.example.carsimulationproject.Model.Animate;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.util.ArrayList;

//By Obaidah, Shon, and Vinith
public class PhysicsEquations {

    public double g = 9.8;

    //Dummy values
    double carMass = 1000;
    double carSpeed = 100;
    double height = 20;
    double kineticEnergy = 0;
    double potentialEnergy = 0;
    double mechanicalEnergy = 0;

    //By Obaidah
    public double findFriction(double mu, double Fnormal) {

        double friction = mu * Fnormal;

        return friction;

    }

    //By Obaidah
    public double findAcceleration(double deltaV, double deltaT) {

        double acceleration = deltaV / deltaT;

        return acceleration;


    }

    //By Obaidah
    public double findTime(double distance, double velocity) {

        double time = distance / velocity;

        return time;

    }

    //By Obaidah
    public double findFnormal(double mass) {

        double Fnormal = mass * g;

        return Fnormal;

    }

    //By Obaidah
    public double findFnormalOnRamp(double mass, double angle) {

        double angleInRadians = Math.toRadians(angle);
        double Fnormal = (mass * g) * Math.cos(angleInRadians);

        return Fnormal;

    }

    //By Obaidah
    public double findKineticEnergy(double mass, double speed) {

        double kineticEnergy = 0.5 * mass * (speed * speed);

        return kineticEnergy;

    }

    //By Obaidah
    public double findPotentialEnergy(double mass, double height) {

        double potentialEnergy = mass * g * height;

        return potentialEnergy;

    }

    //By Obaidah
    public double findMechanicalEnergy(double kineticEnergy, double potentialEnergy) {

        double mechanicalEnergy = kineticEnergy + potentialEnergy;

        return mechanicalEnergy;

    }

    //By Obaidah and Vinith
    public double findNetAcceleration(double EngineAcceleration, double angle, double mu, String direction) {

        double angleInRadians = Math.toRadians(angle);
        double netAcceleration = 0;


        if (direction.equalsIgnoreCase("flat")) {
            netAcceleration = EngineAcceleration - (mu * 9.8 * Math.cos(angleInRadians)); // since its flat, not need for angle since gravity is not affecting it

        }

        else if (direction.equalsIgnoreCase("uphill")) {
            netAcceleration = EngineAcceleration - ((g * Math.sin(angleInRadians)) + (mu * 9.8 * Math.cos(angleInRadians)));
        }

        else if (direction.equalsIgnoreCase("downhill")) {
            netAcceleration = EngineAcceleration + ((g * Math.sin(angleInRadians)) - (mu * 9.8 * Math.cos(angleInRadians)));
        }

        return netAcceleration;

    }

    //By Shon
    public ArrayList<Double> findPoints(double startx, double starty, double distance, double angle, String condition) {
        ArrayList<Double> arrayListPositions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            double positionx = startx + i * (distance / 5) * Math.cos(Math.toRadians(angle));
            double positiony;

            if (condition.equalsIgnoreCase("downhill")) {
                positiony = starty + i * (distance / 5) * Math.sin(Math.toRadians(angle));
            } else if (condition.equalsIgnoreCase("uphill")) {
                positiony = starty - i * (distance / 5) * Math.sin(Math.toRadians(angle));
            } else if (condition.equalsIgnoreCase("flat")) {
                positiony = starty; // No change in y for flat slope
            } else {
                throw new IllegalArgumentException("Invalid condition. Use 'downhill', 'uphill', or 'flat'.");
            }

            arrayListPositions.add(positionx); // Add x-coordinate
            arrayListPositions.add(positiony); // Add y-coordinate
        }

        return arrayListPositions;
    }

    //By Shon
    public ArrayList<Double> velocitiesAtPoint(double initialSpeed, double distance,
                                               double engineAcceleration, double angle, double mu, String direction) {

        double netAcceleration = findNetAcceleration(engineAcceleration, angle, mu, direction);

        ArrayList<Double > listvelocities = new ArrayList<Double>();
        for(int  i = 1; i<=5; i++) {
            Double vel = Math.sqrt(Math.pow(initialSpeed, 2) + (2 * netAcceleration * i* distance/5));
            listvelocities.add(vel);
        }

        return listvelocities;
    }

    //By Shon
    public ArrayList<Double> timeAtPoint(double initialSpeed, double distance,
                                         double engineAcceleration, double angle, double mu, String direction) {

        ArrayList<Double> listvelocities = velocitiesAtPoint(initialSpeed, distance, engineAcceleration, angle, mu, direction);
        ArrayList<Double> listTime = new ArrayList<>();
        for (double velocity : listvelocities) {
            double time = distance / velocity;
            listTime.add(time);
        }

        return listTime;

    }

    //By Shon
    public TextFlow createEnergyDisplay(Path trackdecline, double velocity, int friction, double mass, PathTransition pathTransition) {
        Text keText = new Text("KE: 0 J\n");
        Text peText = new Text("PE: 0 J\n");
        Text meText = new Text("ME: 0 J\n");

        TextFlow energyDisplay = new TextFlow(keText, peText, meText);
        energyDisplay.setPadding(new Insets(10));
        energyDisplay.setStyle("-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-width: 1px;");


        //.currentTimeProperty serves to update the time as the animation progresses
        // .addListener does something whenever a property is change, in this case the time
        // obs is the property being observed, oldtime is the time before the update, newTime is the time after
        pathTransition.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            double progress = newTime.toSeconds() / pathTransition.getTotalDuration().toSeconds(); // finds how mch % of the animation is done

            double x = 50 + progress * (750 - 50); // initialX + distance done so far ; 750 - 50 represents the distance of the track
            double y = 100 + progress * (500 - 100);

            double totalVelocity = velocity - friction;
            double height = 600 - y;
            double ke = 0.5 * mass * totalVelocity * totalVelocity;
            double pe = mass * 9.8 * height / 600;
            double me = ke + pe;


            keText.setText(String.format("KE: %.2f J\n", ke));
            peText.setText(String.format("PE: %.2f J\n", pe));
            meText.setText(String.format("ME: %.2f J\n", me));
        });


        pathTransition.setOnFinished(event -> {
            keText.setText("KE: Finished\n");
            peText.setText("PE: Finished\n");
            meText.setText("ME: Finished\n");
        });

        return energyDisplay;
    }

    //By Shon
    public TextFlow createEnergyDisplayUphill(Path trackdecline, double velocity, int friction, double mass, PathTransition pathTransition) {
        Text keText = new Text("KE: 0 J\n");
        Text peText = new Text("PE: 0 J\n");
        Text meText = new Text("ME: 0 J\n");

        TextFlow energyDisplay = new TextFlow(keText, peText, meText);
        energyDisplay.setPadding(new Insets(10));
        energyDisplay.setStyle("-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-width: 1px;");


        //.currentTimeProperty serves to update the time as the animation progresses
        // .addListener does something whenever a property is change, in this case the time
        // obs is the property being observed, oldtime is the time before the update, newTime is the time after
        pathTransition.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            double progress = newTime.toSeconds() / pathTransition.getTotalDuration().toSeconds(); // finds how mch % of the animation is done

            double x = 50 + progress * (750 - 50); // initialX + distance done so far ; 750 - 50 represents the distance of the track
            double y = 500 - progress * (500 - 100);

            double startHeight = 100;
            double endHeight = 500;
            double distance = endHeight - startHeight;

            double height = startHeight + progress * (distance);

            double totalVelocity = velocity - friction;
            double ke = 0.5 * mass * totalVelocity * totalVelocity;
            double pe = mass * 9.8 * height;
            double me = ke + pe;


            keText.setText(String.format("KE: %.2f J\n", ke));
            peText.setText(String.format("PE: %.2f J\n", pe));
            meText.setText(String.format("ME: %.2f J\n", me));
        });


        pathTransition.setOnFinished(event -> {
            keText.setText("KE: Finished\n");
            peText.setText("PE: Finished\n");
            meText.setText("ME: Finished\n");
        });

        return energyDisplay;
    }

    //By Shon
    public TextFlow energyDisplayComboTrack(Path trackdecline, double velocity, int friction, double mass, PathTransition pathTransition) {
        Text keText = new Text("KE: 0 J\n");
        Text peText = new Text("PE: 0 J\n");
        Text meText = new Text("ME: 0 J\n");

        TextFlow energyDisplay = new TextFlow(keText, peText, meText);
        energyDisplay.setPadding(new Insets(10));
        energyDisplay.setStyle("-fx-background-color: lightgrey; -fx-border-color: black; -fx-border-width: 1px;");


        //.currentTimeProperty serves to update the time as the animation progresses
        // .addListener does something whenever a property is change, in this case the time
        // obs is the property being observed, oldtime is the time before the update, newTime is the time after
        pathTransition.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            double progress = newTime.toSeconds() / pathTransition.getTotalDuration().toSeconds(); // finds how mch % of the animation is done

            double x = 50 + progress * (750 - 50); // initialX + distance done so far ; 750 - 50 represents the distance of the track
            double y = 100 + progress * (500 - 100);

            double currentHeight = findHeight(progress);

            double totalVelocity = velocity - friction;
            double height = 600 - currentHeight;
            double ke = 0.5 * mass * totalVelocity * totalVelocity;
            double pe = mass * 9.8 * height;
            double me = ke + pe;


            keText.setText(String.format("KE: %.2f J\n", ke));
            peText.setText(String.format("PE: %.2f J\n", pe));
            meText.setText(String.format("ME: %.2f J\n", me));
        });


        pathTransition.setOnFinished(event -> {
            keText.setText("KE: Finished\n");
            peText.setText("PE: Finished\n");
            meText.setText("ME: Finished\n");
        });

        return energyDisplay;
    }

    //By Shon
    public double findHeight(double progress) {
        double[] heightPerPart = {200, 200, 150, 150, 200, 200};

        double amountOfParts = heightPerPart.length - 1;
        double totalProgress = progress * amountOfParts; // Allows us to see which specifically where the car is in the entire track
        int whichPartCarIsOn = (int) totalProgress; // Rounds up the totalProgress to see which part the car is on
        double progressOnSpecificPart = totalProgress - whichPartCarIsOn; // Sees the place to see the specific place of the car in a specific part

        double y1 = heightPerPart[whichPartCarIsOn];
        double y2 = heightPerPart[whichPartCarIsOn + 1];
        return y1 + (y2 - y1) * progressOnSpecificPart;
    }

    //By Shon
    //Setters
    public void setCarMass(double carMass) {
        this.carMass = carMass;
    }

    public void setCarSpeed(double carSpeed) {
        this.carSpeed = carSpeed;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setKineticEnergy(double kineticEnergy) {
        this.kineticEnergy = kineticEnergy;
    }

    public void setPotentialEnergy(double potentialEnergy) {
        this.potentialEnergy = potentialEnergy;
    }

    public void setMechanicalEnergy(double mechanicalEnergy) {
        this.mechanicalEnergy = mechanicalEnergy;
    }

    //By Obaidah
    //Getters
    public double getKineticEnergy() {
        return kineticEnergy;
    }

    public double getPotentialEnergy() {
        return potentialEnergy;
    }

    public double getMechanicalEnergy() {
        return mechanicalEnergy;
    }


}
