package com.example.carsimulationproject.Model;

import com.example.carsimulationproject.Controller.PhysicsEquations;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
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

    public void timelineanimation(ArrayList<Double> arrayListpositions,  ArrayList<Double> listTime) {

        if (arrayListpositions.isEmpty()) {
            throw new IllegalStateException("Points must be generated before animating.");
        }

        KeyValue initvaluex = new KeyValue(car.translateXProperty(), arrayListpositions.get(0));
        KeyValue initvaluey = new KeyValue(car.translateYProperty(), arrayListpositions.get(1));
        KeyFrame keyFrameinit = new KeyFrame(Duration.seconds(0), initvaluex, initvaluey);

        KeyValue valuex2 = new KeyValue(car.translateXProperty(), arrayListpositions.get(2));
        KeyValue valuey2 = new KeyValue(car.translateYProperty(), arrayListpositions.get(3));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(1), valuex2, valuey2);

        KeyValue valuex3 = new KeyValue(car.translateXProperty(), arrayListpositions.get(4));
        KeyValue valuey3 = new KeyValue(car.translateYProperty(), arrayListpositions.get(5));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(2), valuex3, valuey3);

        KeyValue valuex4 = new KeyValue(car.translateXProperty(), arrayListpositions.get(6));
        KeyValue valuey4 = new KeyValue(car.translateYProperty(), arrayListpositions.get(7));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(3), valuex4, valuey4);

        KeyValue valuex5 = new KeyValue(car.translateXProperty(), arrayListpositions.get(8));
        KeyValue valuey5 = new KeyValue(car.translateYProperty(), arrayListpositions.get(9));
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(4), valuex5, valuey5);

        Timeline timeline = new Timeline(keyFrameinit, keyFrame2, keyFrame3, keyFrame4, keyFrame5);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }


    public Pane inclineCarAnimation(int velocityX, int angleRadians, int mass, double g) {
        Pane pane = new Pane();
        pane.setPrefSize(600, 400);

        Rectangle car = new Rectangle(50, 30, Color.RED);
        car.setX(0);
        car.setY(100);
        pane.getChildren().add(car);

        Text energyDisplay = new Text(10, 20, "Energy: ");
        energyDisplay.setFill(Color.BLACK);
        pane.getChildren().add(energyDisplay);

        double distanceX = pane.getPrefWidth();
        double durationInSeconds = distanceX / velocityX;
        double distanceY = distanceX * Math.tan(angleRadians);

        double[] keyTimes = {0, 0.25, 0.5, 0.75}; // Four keyframes as fractions of the total duration
        Timeline timeline = new Timeline();

        for (double keyTime : keyTimes) {
            // Compute position at this keyframe
            double xPos = keyTime * distanceX;
            double yPos = keyTime * distanceY;

            // Calculate height
            double height = 100 - yPos; // Assuming starting Y position is 100

            // Energies
            double potentialEnergy = mass * g * height / 100; // Normalize height to meters
            double velocityAtPoint = velocityX; // Assuming constant speed down the incline
            double kineticEnergy = 0.5 * mass * Math.pow(velocityAtPoint / 100, 2); // Normalize velocity to m/s
            double mechanicalEnergy = potentialEnergy + kineticEnergy;

            // Add keyframe
            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(keyTime * durationInSeconds),
                    e -> {
                        // Update car position
                        car.setX(xPos);
                        car.setY(yPos);

                        // Update energy display
                        energyDisplay.setText(String.format(
                                "PE: %.2f J, KE: %.2f J, ME: %.2f J",
                                potentialEnergy, kineticEnergy, mechanicalEnergy
                        ));
                    },
                    new KeyValue(car.xProperty(), xPos, Interpolator.EASE_BOTH), // Smooth X transition
                    new KeyValue(car.yProperty(), yPos, Interpolator.EASE_BOTH)  // Smooth Y transition
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        // Add final keyframe to reset
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(durationInSeconds),
                e -> {
                    car.setX(0);
                    car.setY(100);
                    energyDisplay.setText("Energy: Reset");
                }
        ));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return pane;
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
