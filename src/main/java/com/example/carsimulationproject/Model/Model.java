package com.example.carsimulationproject.Model;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;

public class Model {

    double distance;
    double angle;
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


        distance = 200;
        Path trackdefault = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        trackdefault.setStroke(Color.BLACK);
        PhysicsEquations pe = new PhysicsEquations();
        pe.findpoints(50,50,distance,0);

        return trackdefault;
    }


// decline track
    public Path declinettrack() {

        angle = 20;
        Rotate rotate = new Rotate(angle,50,50);
        distance = 200;
        Path trackdecline = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        trackdecline.setStroke(Color.BLACK);
        trackdecline.getTransforms().addAll(rotate);

        PhysicsEquations pe = new PhysicsEquations();
        pe.findpoints(50,50,distance,angle);

        return trackdecline;
    }

    public Path inclinettrack() {

        angle = (-20);
        Rotate rotate = new Rotate(angle,50,50);
        distance = 200;

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


        double startpointx = 50;
        double startpointy = 50;

        distance = 80;
        //flat part
        double endfirsttrack = startpointx + distance;


        //decline part
        angle = 40;
        distance = 120;
        double endsecondtrackx = endfirsttrack + ( distance * Math.cos( Math.toRadians(angle)) );
        double endsecondtracky = startpointy + ( distance* Math.sin( Math.toRadians(angle)) );

        //flat part
        distance = 60;
        double endthirdtrackx = endsecondtrackx + distance;
        double endthirdtracky = endsecondtracky;

        //incline incline
        angle = 45;
        distance = 75;
        double endfourthtrackx = endthirdtrackx + (distance * Math.cos( Math.toRadians(angle)));
        double endfourthtracky = endthirdtracky - (distance * Math.sin( Math.toRadians(angle)));

        //flat part
        distance = 65;
        double endfifthtrackx = endfourthtrackx + distance;
        double endfifthtracky = endfourthtracky;

        //decline part
        angle = 25;
        distance = 60;

        double endsixthtrackx = endfifthtrackx + distance * Math.cos( Math.toRadians(angle));
        double endsixthtracky = endfifthtracky - distance * Math.sin( Math.toRadians(angle));

        //end of track

        angle = 35;
        distance = 60;

        double endseventhtrackx = endsixthtrackx + distance * Math.cos( Math.toRadians(angle));
        double endseventhtracky = endsixthtracky + distance * Math.sin( Math.toRadians(angle));



        Path combination = new Path(

                new MoveTo(50,50),
                new LineTo(endfirsttrack,50),
                new LineTo(endsecondtrackx,endsecondtracky),
                new LineTo(endthirdtrackx,endthirdtracky),
                new LineTo(endfourthtrackx,endfourthtracky),
                new LineTo(endfifthtrackx,endfifthtracky),
                new LineTo(endsixthtrackx, endsixthtracky),
                new LineTo(endseventhtrackx, endseventhtracky)

        );
        combination.setStroke(Color.BLACK);
        return combination;
    }

}
