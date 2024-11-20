package com.example.carsimulationproject.Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;

public class Model {

    double distance; //will be turned into a parameter that user can change
    double angle; //will be turned into a parameter that user can change
    double velocity= 25; //initial velocity is set by the user


   public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setAngle (double angle) {
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


    //the angles are not all the same, this will be fixed next time
    Path combotrack() {

        double endfirsttrack = 50 + distance;

        double endsecondtrackx = endfirsttrack + 2*distance* Math.cos(angle);
        double endsecondtracky = 50+ + 2*distance* Math.sin(angle);

        double endthirdtrackx = endsecondtrackx + distance;
        double endthirdtracky = endsecondtracky;

        double endfourthtrackx = endthirdtrackx + distance*Math.cos(angle);
        double endfourthtracky = endthirdtracky + distance*Math.sin(angle);

        double endfifthtrackx = endfourthtrackx + distance*Math.cos(angle);
        double fifthtracky = endthirdtracky;

       Path combination = new Path(

               new MoveTo(50,50),
               new LineTo(endfirsttrack,50),
               new LineTo(endsecondtrackx,endsecondtracky),
               new LineTo(endthirdtrackx,endthirdtracky),
               new LineTo(endfourthtrackx,endfourthtracky)

       );
        combination.setStroke(Color.BLACK);
        return combination;
    }

}
