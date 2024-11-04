package com.example.carsimulationproject.VinithAnimations;

import com.example.carsimulationproject.ObaidahPhysics.PhysicsEquations;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Animate extends Pane {

    Rectangle car = new Rectangle(30, 50, 40, 20);
    Rectangle truck = new Rectangle(30, 50, 80, 40); //not for now

    double engineforce;

    public Animate() {

    //setting the vehicule properties like setting the engine

        car.setFill(Color.BLUE);

        this.getChildren().addAll(car);

    }

    PathTransition transition() {

     PhysicsEquations physics = new PhysicsEquations();
     Model modelclass = new Model();


     Path chosenpath = new Path();
     chosenpath = modelclass.declinettrack();

     this.getChildren().add(chosenpath);

     double time = physics.findTime(modelclass.distance,modelclass.velocity);

        PathTransition transition = new PathTransition(Duration.seconds(time), chosenpath,car);

        transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.interpolatorProperty().setValue(Interpolator.LINEAR);
        transition.setAutoReverse(false);

        return transition;

    }

    //running the animation
    public void playanimation() {
        transition().play();
    }

    //stopping the animation
    void stopanimation() {
        transition().stop();
    }

}
