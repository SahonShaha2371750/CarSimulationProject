package com.example.carsimulationproject.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarSkins {

 ImageView lamborghiniSkin() {

  Image lamboimage = new Image("file:C:\\Users\\user\\IdeaProjects\\CarSimulationProject\\src\\main\\resources\\lambo.png");

  ImageView lamboView = new ImageView(lamboimage);


  lamboView.setFitWidth(300);
  lamboView.setFitHeight(200);
  lamboView.setPreserveRatio(true);


   return lamboView;
 }

 ImageView cybertruckSkin() {

  Image cyberimage = new Image("file:C:\\Users\\user\\IdeaProjects\\CarSimulationProject\\src\\main\\resources\\cybertruck.png");

  ImageView cyberView = new ImageView(cyberimage);

  cyberView.setFitWidth(300);
  cyberView.setFitHeight(200);
  cyberView.setPreserveRatio(true);

   return cyberView;
 }

 ImageView regulartruckSkin() {

  Image truckimage = new Image("file:C:\\Users\\user\\IdeaProjects\\CarSimulationProject\\src\\main\\resources\\truck.png");

  ImageView truckView = new ImageView(truckimage);


  truckView.setFitWidth(300);
  truckView.setFitHeight(200);
  truckView.setPreserveRatio(true);

  return truckView;
 }



}
