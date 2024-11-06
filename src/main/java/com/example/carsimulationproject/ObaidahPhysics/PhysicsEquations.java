package com.example.carsimulationproject.ObaidahPhysics;

public class PhysicsEquations {

    public double findFriction(double mu, double Fnormal) {

        double friction = mu * Fnormal;

        return friction;

    }

    public double findAcceleration(double deltaV, double deltaT) {

        double acceleration = deltaV / deltaT;

        return acceleration;


    }

    public double findTime(double distance, double velocity) {

        double time = distance / velocity;

        return time;

    }

    public double findFnormal(double mass, double g) {

        double Fnormal = mass * g;

        return Fnormal;

    }

    public double findFnormalOnRamp(double mass, double g, double angle) {

        double angleInRadians = Math.toRadians(angle);
        double Fnormal = (mass * g) * Math.cos(angleInRadians);

        return Fnormal;

    }

}
