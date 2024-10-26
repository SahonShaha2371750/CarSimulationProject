package com.example.carsimulationproject;

public class PhysicsEquations {

    public void findFriction(double mu, double Fnormal) {

        double friction = mu * Fnormal;

    }

    double findAcceleration(double deltaV, double deltaT) {

        double acceleration = deltaV / deltaT;
        return acceleration;

    }

    double findTime(double distance, double velocity) {

        double time = distance / velocity;

        return time;

    }

}
