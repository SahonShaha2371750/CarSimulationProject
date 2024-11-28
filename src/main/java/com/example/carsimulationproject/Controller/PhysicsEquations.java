package com.example.carsimulationproject.Controller;

import com.example.carsimulationproject.Model.Animate;

import java.util.ArrayList;

public class PhysicsEquations {

    double g = 9.8;

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
            netAcceleration = EngineAcceleration - (mu * 9.8 * Math.cos(angleInRadians));

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

        Animate animate = new Animate();
        animate.timelineanimation(arrayListpositionsx, arrayListpositionsy);
    }


}
