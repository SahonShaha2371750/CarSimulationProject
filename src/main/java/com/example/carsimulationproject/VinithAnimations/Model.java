package com.example.carsimulationproject.VinithAnimations;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;

public class Model {

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


// default track
    Path defaulttrack() {

        Rotate rotate = new Rotate(angle,50,50);

        Path trackdefault = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        trackdefault.setStroke(Color.BLACK);
        trackdefault.getTransforms().addAll(rotate);

        return trackdefault;
    }


// decline track
    Path declinettrack() {


        Rotate rotate = new Rotate(angle,50,50);

        Path trackdecline = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        trackdecline.setStroke(Color.BLACK);
        trackdecline.getTransforms().addAll(rotate);

        return trackdecline;
    }

    Path inclinettrack() {

        Rotate rotate = new Rotate(angle,50,50);

        Path trackincline = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        trackincline.setStroke(Color.BLACK);
        trackincline.getTransforms().addAll(rotate);

        return trackincline;
    }

    Path loop() {

        Path trackloop = new Path(



        );

      return trackloop;
    };








}
