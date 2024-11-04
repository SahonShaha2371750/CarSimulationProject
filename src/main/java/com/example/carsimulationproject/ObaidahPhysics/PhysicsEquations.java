package com.example.carsimulationproject.ObaidahPhysics;

public class PhysicsEquations {

    double friction;
    double caracceleration;

    double time;

    double findFriction(double mu, double Fnormal) {

        friction = mu * Fnormal;

        return friction;

    }


    double findcaracceleration (double carmass, double engineforce) {

        caracceleration = engineforce/ carmass;

        return caracceleration;
    }

    double findAcceleration(double deltaV, double deltaT) {

        double acceleration = deltaV / deltaT;
        return acceleration;

    }


    public double findTime(double distance, double startingvelocity) {

        time = distance / startingvelocity;

        return time;

    }


    double findforcecausedbygravity(double carmass, double angle) {

        double forcecausedbygravity = carmass * 9.8 * Math.sin(angle);
        return forcecausedbygravity;
    }

}
