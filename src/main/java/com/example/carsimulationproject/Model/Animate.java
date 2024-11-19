package com.example.carsimulationproject.Model;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Animate extends Pane {

    Rectangle car = new Rectangle(30, 50, 40, 20);
    Rectangle truck = new Rectangle(30, 50, 80, 40); //not for now

    public double engineforce;

    public Animate() {

    //setting the vehicule properties like setting the engine

        car.setFill(Color.BLUE);

        this.getChildren().addAll(car);

    }

    public PathTransition transition() {

     PhysicsEquations physics = new PhysicsEquations();
     Model modelclass = new Model();


     Path chosenpath = new Path();
     chosenpath = modelclass.declinettrack();

     this.getChildren().add(chosenpath);

     double time = physics.findTime(modelclass.distance, modelclass.velocity);

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
    public void stopanimation() {
        transition().stop();
    }

}
