package com.example.carsimulationproject.Controller;

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

}
