package com.example.carsimulationproject.Model;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import com.example.carsimulationproject.View.MainScreen;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Trackselections {

    double distance;
    double angle;
    double initialvelocity; //initial velocity is set by the user
    MainScreen ms = new MainScreen();
    PhysicsEquations pe = new PhysicsEquations();
    Animate animate = new Animate();

    void setInitialvelocityVelocity ( double velocity) {
        this.initialvelocity = velocity;
    }


// default track
    public Path defaulttrack() {

        angle = 0;
        distance = 200;
        Path trackdefault = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        trackdefault.setStroke(Color.BLACK);

        Double accelerationfirstsection = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"flat");
        ArrayList<Double> pointsfirstsection = pe.findPoints(50,50,distance,20, "flat");
        ArrayList<Double> timesfirstsection = pe.timeAtPoint(ms.initialVelocity,distance,ms.engineAcceleration,angle,0.5,"flat");
        animate.timelineanimation(pointsfirstsection, timesfirstsection );
        return trackdefault;
    }


// decline track
    public  Path declinetrack() {


        //Simplified the track

        Path trackdecline = new Path();

        trackdecline.setStroke(Color.BLACK);
        trackdecline.setStrokeWidth(2);

        //Define the points of the track
        MoveTo start = new MoveTo(0, 200);
        LineTo line = new LineTo(700, 700);

        //Add all points to the Path
        trackdecline.getElements().addAll(start, line);

        /*

        angle = 20;
        Rotate rotate = new Rotate(angle,50,50);
        distance = 200;
        Path trackdecline = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        trackdecline.setStroke(Color.BLACK);
        trackdecline.getTransforms().addAll(rotate);


        Double accelerationfirstsection = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"downhill");
        ArrayList<Double> pointsfirstsection = pe.findPoints(50,50,distance,angle, "downhill");
        ArrayList<Double> timesfirstsection = pe.timeAtPoint(ms.initialVelocity,distance,ms.engineAcceleration,angle,0.5,"downhill");

        animate.timelineanimation(pointsfirstsection, timesfirstsection );

         */
        return trackdecline;
    }

    public Path inclinettrack() {



        //Simplfied the track

        Path trackincline = new Path();

        trackincline.setStroke(Color.BLACK);
        trackincline.setStrokeWidth(2);

        //Define the points of the track
        MoveTo start = new MoveTo(0, 500);
        LineTo line = new LineTo(700, 100);

        //Add all points to the Path
        trackincline.getElements().addAll(start, line);

        /*
        angle = 20;
        Rotate rotate = new Rotate((-1*angle),50,50);
        distance = 200;

        Path trackincline = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        trackincline.setStroke(Color.BLACK);
        trackincline.getTransforms().addAll(rotate);

        Double accelerationfirstsection = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"uphill");
        ArrayList <Double> pointsfirstsection = pe.findPoints(50,50,distance,angle, "uphill");
        ArrayList <Double> timesfirstsection = pe.timeAtPoint(ms.initialVelocity,distance,ms.engineAcceleration,angle,0.5,"uphill");


        animate.timelineanimation(pointsfirstsection, timesfirstsection );




         */
        return trackincline;
    }


    //the angles are not all the same, this will be fixed next time


   public Path combotrack() {

        /*
        double startpointx = 50;
        double startpointy = 50;


        //flat part (First part)
        distance = 80;
        angle = 0;

        Double accelerationFirstsection = pe.findNetAcceleration(ms.engineAcceleration, angle,0.5,"flat");
        ArrayList<Double> pointsFirstsection = pe.findPoints(startpointx,startpointy,distance,angle,"flat");
        ArrayList<Double> velocitiesFirstsection = pe.velocitiesAtPoint(ms.initialVelocity,distance,ms.engineAcceleration,angle,0.5,"flat");
        ArrayList<Double> timesFirstsection = pe.timeAtPoint(ms.initialVelocity,distance,ms.engineAcceleration,angle,0.5,"flat");
        Double lastvelocitiesFirstsection = velocitiesFirstsection.get(4);

        double endFirstTrackX = pointsFirstsection.get(8);
        double endFirstTrackY = startpointy;
        animate.timelineanimation(pointsFirstsection, timesFirstsection );

        //decline part (Second part)
        angle = 40;
        distance = 120;

        Double accelerationSecondsection = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"downhill");
        ArrayList<Double> pointsSecondsection = pe.findPoints(endFirstTrackX,endFirstTrackY,distance,angle,"downhill");
        ArrayList<Double> velocitiesSecondsection = pe.velocitiesAtPoint(lastvelocitiesFirstsection,distance,ms.engineAcceleration,angle,0.5,"downhill");
        ArrayList<Double> timesSecondsection = pe.timeAtPoint(lastvelocitiesFirstsection,distance,ms.engineAcceleration,angle,0.5,"downhill");
        Double lastvelocitiesSecondsection = velocitiesSecondsection.get(4);

        double endSecondTrackX = pointsSecondsection.get(8); // Second last element is X
        double endSecondTrackY = pointsSecondsection.get(9);
        animate.timelineanimation(pointsSecondsection, timesSecondsection );


        //flat part (Third part)
        angle = 0;
        distance = 60;

        Double accelerationThirdsection = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"flat");
        ArrayList<Double> pointsThirdsection = pe.findPoints(endSecondTrackX,endSecondTrackY,distance,angle,"flat");
        ArrayList<Double> velocitiesThirdsection = pe.velocitiesAtPoint(lastvelocitiesSecondsection,distance, ms.engineAcceleration, angle,0.5,"downhill");
        ArrayList<Double> timesThirdsection = pe.timeAtPoint(lastvelocitiesFirstsection,distance,ms.engineAcceleration,angle,0.5,"downhill");
        Double lastvelocitiesThirdsection = velocitiesThirdsection.get(4);

        double endThirdTrackX = pointsThirdsection.get(8);
        double endThirdTrackY = pointsThirdsection.get(9);
        animate.timelineanimation(pointsThirdsection, timesThirdsection );


        //incline part (Fourth part)
        angle = 45;
        distance = 75;

        Double accelerationFourthsection = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"uphill");
        ArrayList<Double> pointsFourthsection =  pe.findPoints(endThirdTrackX,endThirdTrackY,distance,angle,"uphill");
        ArrayList<Double> velocitiesFourthsection = pe.velocitiesAtPoint(lastvelocitiesThirdsection,distance,ms.engineAcceleration,0,0.5,"uphill");
        ArrayList<Double> timesFourthsection = pe.timeAtPoint(lastvelocitiesThirdsection,distance,ms.engineAcceleration,0,0.5,"uphill");
        Double lastvelocitiesFourthsection = velocitiesFourthsection.get(4);

        double endFourthTrackX = pointsFourthsection.get(8);
        double endFourthTrackY = pointsFourthsection.get(9);
        animate.timelineanimation(pointsFourthsection, timesFourthsection );


        //flat part (Fifth part)
        angle = 0;
        distance = 65;

        Double accelerationFifthsection = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"flat");
        ArrayList<Double> pointsFifthsection = pe.findPoints(endFourthTrackX,endFourthTrackY,distance,angle, "flat");
        ArrayList<Double> velocitiesFifthsection = pe.velocitiesAtPoint(lastvelocitiesFourthsection,distance,ms.engineAcceleration,angle,0.5,"flat");
        ArrayList<Double> timesFifthsection = pe.timeAtPoint(lastvelocitiesFourthsection,distance,ms.engineAcceleration,angle,0.5,"flat");
        Double lastvelocitiesFifthsection = velocitiesFifthsection.get(4);

        double endFifthTrackX = pointsFifthsection.get(8);
        double endFifthTrackY = pointsFifthsection.get(9);
        animate.timelineanimation(pointsFifthsection, timesFifthsection );


        //incline part (Sixth part)
        angle = 25;
        distance = 60;

        Double accelerationSixthsection = pe.findNetAcceleration(20,angle,0.5,"uphill");
        ArrayList<Double> pointsSixthsection = pe.findPoints(endFifthTrackX,endFifthTrackY,distance,angle, "uphill");
        ArrayList<Double> velocitiesSixthsection = pe.velocitiesAtPoint(lastvelocitiesFifthsection,distance,ms.engineAcceleration,angle,0.5,"uphill");
        ArrayList<Double> timesSixthsection = pe.timeAtPoint(lastvelocitiesFifthsection,distance,ms.engineAcceleration,angle,0.5,"uphill");
        Double lastvelocitiesSixthsection = velocitiesSixthsection.get(4);

        double endSixthTrackX = pointsSixthsection.get(8);
        double endSixthTrackY = pointsSixthsection.get(9);
        animate.timelineanimation(pointsSixthsection, timesSixthsection );

        //decline part (Seventh part)
        angle = 35;
        distance = 60;

        Double accelerationSeventhsection = pe.findNetAcceleration(20,angle,0.5,"downhill");
        ArrayList<Double> pointsSeventhsection = pe.findPoints(endSixthTrackX ,endSixthTrackY,distance,angle,"downhill" );
        ArrayList<Double> velocitiesSeventhsection = pe.velocitiesAtPoint(lastvelocitiesSixthsection,distance,ms.engineAcceleration,angle,0.5,"downhill");
        ArrayList<Double> timesSeventhsection = pe.timeAtPoint(lastvelocitiesSixthsection,distance,ms.engineAcceleration,angle,0.5,"downhill");
        Double lastvelocitiesSeventhsection = velocitiesSeventhsection.get(4);

        double endSeventhTrackX = pointsSeventhsection.get(8);
        double endSeventhTrackY = pointsSeventhsection.get(9);
        animate.timelineanimation(pointsSeventhsection, timesSeventhsection );


         */
        //Simplified the track

       Path combo = new Path();
       combo.setStroke(Color.BLACK); // Outline color
       combo.setStrokeWidth(2);
       combo.setFill(Color.GREEN);

       // Start point
       combo.getElements().add(new MoveTo(0, 200)); // Bottom-left start point

       // Top track coordinates
       combo.getElements().add(new LineTo(200, 200)); // (200, 200)
       combo.getElements().add(new LineTo(300, 150)); // (300, 150)
       combo.getElements().add(new LineTo(400, 150)); // (400, 150)
       combo.getElements().add(new LineTo(500, 200)); // (500, 200)
       combo.getElements().add(new LineTo(700, 200)); // (700, 200)

       // Close the shape to the bottom edge
       combo.getElements().add(new LineTo(700, 400)); // Bottom-right
       combo.getElements().add(new LineTo(0, 400));   // Bottom-left
       combo.getElements().add(new ClosePath());      // Close the shape

       /*
        Path combination = new Path(

                new MoveTo(50,50),
                new LineTo(endFirstTrackX,50),
                new LineTo(endSecondTrackX,endSecondTrackY),
                new LineTo(endThirdTrackX,endThirdTrackY),
                new LineTo(endFourthTrackX,endFourthTrackY),
                new LineTo(endFifthTrackX,endFifthTrackY),
                new LineTo(endSixthTrackX, endSixthTrackY),
                new LineTo(endSeventhTrackX, endSeventhTrackY)

        );
        */
        return combo;
    }

}
