package com.example.carsimulationproject.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//By Vinith
public class CarSkins {

 //By Vinith
 public ImageView lamborghiniSkin() {

  Image lamboimage = new Image("lambo.png");

  ImageView lamboView = new ImageView(lamboimage);


  lamboView.setFitWidth(100);
  lamboView.setFitHeight(100);
  lamboView.setPreserveRatio(true);


   return lamboView;
 }

 //By Vinith
 public ImageView cybertruckSkin() {

  Image cyberimage = new Image("cybertruck.png");

  ImageView cyberView = new ImageView(cyberimage);

  cyberView.setFitWidth(100);
  cyberView.setFitHeight(100);
  cyberView.setPreserveRatio(true);

   return cyberView;
 }

 //By Vinith
 public ImageView regulartruckSkin() {

  Image truckimage = new Image("truck.png");

  ImageView truckView = new ImageView(truckimage);


  truckView.setFitWidth(100);
  truckView.setFitHeight(100);
  truckView.setPreserveRatio(true);

  return truckView;
 }



}
