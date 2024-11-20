package com.example.carsimulationproject.Model;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import javafx.animation.*;
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

    // for acceleration, under construction
    void timelineanimation() {

        KeyValue initvaluex = new KeyValue(car.translateXProperty(),50);
        KeyValue initvaluey = new KeyValue(car.translateYProperty(),50);
        KeyFrame keyFrameinit = new KeyFrame(Duration.ZERO, initvaluex,initvaluey);

        KeyValue valuex2 = new KeyValue(car.translateXProperty(),150);
        KeyValue valuey2 = new KeyValue(car.translateYProperty(),150);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), valuex2, valuey2);

        KeyValue valuex3 = new KeyValue(car.translateXProperty(),250);
        KeyValue valuey3 = new KeyValue(car.translateYProperty(),250);
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(2), valuex3,valuex3);

        Timeline timeline = new Timeline(keyFrameinit,keyFrame2,keyFrame3);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();



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
