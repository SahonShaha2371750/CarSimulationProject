package com.example.carsimulationproject.Model;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

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
    public void timelineanimation(ArrayList<Double> arrayListpositionsx, ArrayList<Double> arrayListpositionsy) {

        if (arrayListpositionsx.isEmpty() || arrayListpositionsy.isEmpty()) {
            throw new IllegalStateException("Points must be generated before animating.");
        }

        KeyValue initvaluex = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(0));
        KeyValue initvaluey = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(0));
        KeyFrame keyFrameinit = new KeyFrame(Duration.ZERO, initvaluex, initvaluey);

        KeyValue valuex2 = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(1));
        KeyValue valuey2 = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(1));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), valuex2, valuey2);

        KeyValue valuex3 = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(2));
        KeyValue valuey3 = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(2));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(4), valuex3, valuey3);

        KeyValue valuex4 = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(3));
        KeyValue valuey4 = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(3));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(6), valuex4, valuey4);

        KeyValue valuex5 = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(4));
        KeyValue valuey5 = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(4));
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(8), valuex5, valuey5);

        Timeline timeline = new Timeline(keyFrameinit, keyFrame2, keyFrame3, keyFrame4, keyFrame5);
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
