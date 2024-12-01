package com.example.carsimulationproject.Controller;

import com.example.carsimulationproject.Model.Animate;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PhysicsEquations {

   public double g = 9.8;

    //Dummy values
    double carMass = 1000;
    double carSpeed = 100;
    double height = 20;
    double kineticEnergy = 0;
    double potentialEnergy = 0;
    double mechanicalEnergy = 0;

    public double findFriction(double mu, double Fnormal) {

        double friction = mu * Fnormal;

        return friction;

    }

    //only works on a flat road
    public double findAcceleration(double deltaV, double deltaT) {

        double acceleration = deltaV / deltaT;

        return acceleration;


    }

    public double findTime(double distance, double velocity) {

        double time = distance / velocity;

        return time;

    }

    public double findFnormal(double mass) {

        double Fnormal = mass * g;

        return Fnormal;

    }

    public double findFnormalOnRamp(double mass, double angle) {

        double angleInRadians = Math.toRadians(angle);
        double Fnormal = (mass * g) * Math.cos(angleInRadians);

        return Fnormal;

    }

    public double findKineticEnergy(double mass, double speed) {

        double kineticEnergy = 0.5 * mass * (speed * speed);

        return kineticEnergy;

    }

    public double findPotentialEnergy(double mass, double height) {

        double potentialEnergy = mass * g * height;

        return potentialEnergy;

    }

    public double findMechanicalEnergy(double kineticEnergy, double potentialEnergy) {

        double mechanicalEnergy = kineticEnergy + potentialEnergy;

        return mechanicalEnergy;

    }

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

    public void findpoints(double startx, double starty, double distance, double angle) {
        ArrayList<Double> arrayListpositionsx = new ArrayList<>();
        ArrayList<Double> arrayListpositionsy = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            double positionx = startx + i * (distance / 5) * Math.cos(Math.toRadians(angle));
            arrayListpositionsx.add(positionx);
        }

        for (int i = 1; i <= 5; i++) {
            double positiony = starty + i * (distance / 5) * Math.sin(Math.toRadians(angle));
            arrayListpositionsy.add(positiony);
        }

        /*Animate animate = new Animate();
        animate.timelineanimation(arrayListpositionsx, arrayListpositionsy, );*/
    }

    public ArrayList<Double> velocitiesAtPoint(double initialSpeed, int distance,
                             double engineAcceleration, double angle, double mu, String direction) {

        double netAcceleration = findNetAcceleration(engineAcceleration, angle, mu, direction);

        ArrayList<Double > listvelocities = new ArrayList<Double>();
        for(int  i = 1; i<=5; i++) {
            Double vel = Math.sqrt(Math.pow(initialSpeed, 2) + (2 * netAcceleration * i* distance/5));
            listvelocities.add(vel);
        }

        return listvelocities;
    }

    public ArrayList<Double> timeAtPoint(double initialSpeed, int distance,
                                         double engineAcceleration, double angle, double mu, String direction) {

        ArrayList<Double> listvelocities = velocitiesAtPoint(initialSpeed, distance, engineAcceleration, angle, mu, direction);
        ArrayList<Double> listTime = new ArrayList<>();
        for (double velocity : listvelocities) {
            double time = distance / velocity;
            listTime.add(time);
        }

        return listTime;

    }



    public AnimationTimer animationTimer = new AnimationTimer() {
        private long lastUpdate = 0;


        @Override
        public void handle(long now) {
            if (lastUpdate > 0) {
                double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
                updateSimulation(deltaTime);
            }

            lastUpdate = now;
            drawEnergyValues();

        }


    };

    public void updateSimulation(double deltaTime) {
        //Update car's kinetic energy every frame
        kineticEnergy = findKineticEnergy(carMass, carSpeed);

        //Update car's potential energy every frame
        potentialEnergy = findPotentialEnergy(carMass, height);

        //Update car's mechanical energy every frame
        mechanicalEnergy = findMechanicalEnergy(findKineticEnergy(carMass, carSpeed), findPotentialEnergy(carMass, height));

    }

    public Group drawEnergyValues() {
        Text speedText = new Text("Speed: " + carSpeed + " km/h");
        speedText.setX(10);
        speedText.setY(20);

        Text kineticText = new Text("Kinetic Energy: " + kineticEnergy + " J");
        kineticText.setX(10);
        kineticText.setY(40);

        Text potentialText = new Text("Potential Energy: " + potentialEnergy + " J");
        potentialText.setX(10);
        potentialText.setY(60);

        Text mechanicalText = new Text("Mechanical Energy: " + mechanicalEnergy + " J");
        mechanicalText.setX(10);
        mechanicalText.setY(80);

        Group groupLabels = new Group();
        groupLabels.getChildren().addAll(speedText, kineticText, potentialText, mechanicalText);
        return groupLabels;
    }

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
