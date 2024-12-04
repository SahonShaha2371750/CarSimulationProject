package com.example.carsimulationproject.Model;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import com.example.carsimulationproject.View.MainScreen;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Trackselections {

    double distance;
    double angle;
    double initialvelocity; //initial velocity is set by the user
    MainScreen ms = new MainScreen();
    public PhysicsEquations pe = new PhysicsEquations();
    Animate animate = new Animate();

    void setInitialvelocityVelocity ( double velocity) {
        this.initialvelocity = velocity;
    }


      //Default track
    /*public Path defaulttrack() {

        angle = 0;
        distance = 200;
        Path trackdefault = new Path(
                new MoveTo(50,50),
                new LineTo(50+distance,50)
        );

        trackdefault.setStroke(Color.BLACK);

        Double accelerationdefault = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"flat");
        ArrayList<Double> pointsdefault = pe.findPoints(50,50,distance,20, "flat");
        ArrayList<Double> timesAtPointsdefault = pe.timeAtPoint(ms.initialVelocity,distance,ms.engineAcceleration,angle,0.5,"flat");
        animate.timelineanimation(pointsdefault , timesAtPointsdefault );
        return trackdefault;
    }*/


    //By Vinith and Obaidah
    //Decline track
    public  Path declinetrack(StackPane center) {

        double width = center.getWidth();
        double height = center.getHeight();

        System.out.println(width);
        System.out.println(height);

        // Define the path for the inverted triangle
        Path trackdecline = new Path(
                new MoveTo(120, 630),  // Bottom-left corner (90-degree angle)
                new LineTo(1150, 630), // Bottom-right
                new LineTo(120, 150),  // Top-left
                new ClosePath()        // Ensures the path is fully closed
        );

        trackdecline.setFill(Color.LAWNGREEN); // Set the fill color
        trackdecline.setStroke(Color.BLACK);  // Set the stroke color
        trackdecline.setStrokeWidth(2);       // Set the stroke width

        return trackdecline;




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


        Double accelerationdecline = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"downhill");
        ArrayList<Double> pointsdecline = pe.findPoints(50,50,distance,angle, "downhill");
        ArrayList<Double> timesAtPointsdecline = pe.timeAtPoint(ms.initialVelocity,distance,ms.engineAcceleration,angle,0.5,"downhill");

        animate.timelineanimation(pointsdecline, timesAtPointsdecline );

         */
    }

    //By Vinith and Obaidah
    public Path inclinettrack(StackPane center) {

        double width = center.getWidth();
        double height = center.getHeight();

        System.out.println(width);
        System.out.println(height);

        Path trackincline = new Path(

                new MoveTo(190,530),
                new LineTo(1340,150),
                new LineTo(1340,530),
                new LineTo(190,530),


                new ClosePath()
        );


        trackincline.setFill(Color.GREEN);

        trackincline.setStroke(Color.BLACK);
        trackincline.setStrokeWidth(2);

        //Define the points of the track

        //Add all points to the Path

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

        Double accelerationincline = pe.findNetAcceleration(ms.engineAcceleration,angle,0.5,"uphill");
        ArrayList <Double> pointsincline = pe.findPoints(50,50,distance,angle, "uphill");
        ArrayList <Double> timesAtPointsincline = pe.timeAtPoint(ms.initialVelocity,distance,ms.engineAcceleration,angle,0.5,"uphill");


        animate.timelineanimation(pointsincline, timesAtPointsincline);




         */
        return trackincline;
    }






    //the angles are not all the same, this will be fixed next time

    //By Vinith
   public Path combotrack(StackPane center) {


       //Simplified the track

       Path combo = new Path();
       combo.setStroke(Color.BLACK); // Outline color
       combo.setStrokeWidth(2);
       combo.setFill(Color.GREEN);

       double width = center.getWidth();
       double height = center.getHeight();


       // Start point
       combo.getElements().add(new MoveTo(center.getLayoutX()*3/5-5, height*2/3)); // Bottom-left start point
       System.out.println(center.getLayoutX());


       // Top track coordinates
       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(width/5), height*2/3));
       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(2*width/5), height*1/3));
       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(3*width/5), height*1/3));
       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(4*width/5), height*2/3));
       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(5*width/5)-10, height*2/3));

       // Close the shape to the bottom edge
       combo.getElements().add(new LineTo(center.getLayoutX()*3/5+(5*width/5)-10, height)); // Bottom-right
       combo.getElements().add(new LineTo(center.getLayoutX()*3/5-5, height));   // Bottom-left
       combo.getElements().add(new ClosePath());      // Close the shape



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
