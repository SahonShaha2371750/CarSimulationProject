package com.example.carsimulationproject;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Animate extends Pane {

    Rectangle car = new Rectangle(30, 50, 40, 20);
    Rectangle truck = new Rectangle(30, 50, 80, 40); //not for now



    Animate() {

    //setting the vehicule properties like setting the engine

        car.setFill(Color.BLUE);

        this.getChildren().addAll(car);

    }

    double distance = 300; //will be turned into a parameter that user can change
    double angle = 45; //will be turned into a parameter that user can change
    double velocity = 10; //initial velocity is set by the user


    void setDistance(double distance) {
        this.distance = distance;
    }

    void setAngle (double angle) {
        this.angle = angle;
    }

    void setVelocity ( double velocity) {
        this.velocity = velocity;
    }



    Path tracks() {


        Rotate rotate = new Rotate(angle,50,50);

        Path track1 = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        track1.setStroke(Color.BLACK);
        track1.getTransforms().addAll(rotate);

        this.getChildren().add(track1);

        return track1;
    }

    PathTransition transition() {

     PhysicsEquations physics = new PhysicsEquations();

     double time = physics.findTime(distance,velocity);

        PathTransition transition = new PathTransition(Duration.seconds(time), tracks(),car);

        transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.interpolatorProperty().setValue(Interpolator.LINEAR);
        transition.setAutoReverse(false);

        return transition;


    }

    //running the animation
    void playanimation() {
        transition().play();
    }

    //stopping the animation
    void stopanimation() {
        transition().stop();
    }




}
