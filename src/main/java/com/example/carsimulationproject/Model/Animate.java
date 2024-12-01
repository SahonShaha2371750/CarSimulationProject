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


    public Animate() {

    //setting the vehicule properties like setting the engine

        car.setFill(Color.BLUE);


        this.getChildren().addAll(car);


    }



    // for acceleration, under construction
    public void timelineanimation(ArrayList<Double> arrayListpositionsx, ArrayList<Double> arrayListpositionsy, ArrayList<Double> listTime) {

        if (arrayListpositionsx.isEmpty() || arrayListpositionsy.isEmpty()) {
            throw new IllegalStateException("Points must be generated before animating.");
        }


        KeyValue initvaluex = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(0));
        KeyValue initvaluey = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(0));
        KeyFrame keyFrameinit = new KeyFrame(Duration.ZERO, initvaluex, initvaluey);

        KeyValue valuex2 = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(1));
        KeyValue valuey2 = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(1));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(listTime.get(1)), valuex2, valuey2);

        KeyValue valuex3 = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(2));
        KeyValue valuey3 = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(2));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(listTime.get(2)), valuex3, valuey3);

        KeyValue valuex4 = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(3));
        KeyValue valuey4 = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(3));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(listTime.get(3)), valuex4, valuey4);

        KeyValue valuex5 = new KeyValue(car.translateXProperty(), arrayListpositionsx.get(4));
        KeyValue valuey5 = new KeyValue(car.translateYProperty(), arrayListpositionsy.get(4));
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(listTime.get(4)), valuex5, valuey5);

        Timeline timeline = new Timeline(keyFrameinit, keyFrame2, keyFrame3, keyFrame4, keyFrame5);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }




    //running the animation
    public void playanimation() {
       // timelineanimation().play();
    }

    //stopping the animation
    public void stopanimation() {
       // timelineanimation().stop();
    }

}
