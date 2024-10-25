package com.example.carsimulationproject;

public class PhysicsEquations {

    public void findFriction(double mu, double Fnormal) {

        double friction = mu * Fnormal;

    }

    public void findAcceleration(double deltaV, double deltaT) {

        double acceleration = deltaV / deltaT;

    }

    public void findTime(double distance, double velocity) {

        double time = distance / velocity;

    }

}
