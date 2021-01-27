package controller;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationWindowController {
  private Stage animationWindowStage;
  private int animationNo;
  
  @FXML Rectangle rect;
  @FXML Rectangle rect1;
  @FXML Circle circle;
  
  //User code 1 ...
  Animation animation;
  
  public void setStage(Stage stage){
	  this.animationWindowStage=stage;
  }
  
  /*
   * FadeTransition - animowanie przezroczystości
   * FillTransition - kolor i wypełnienie
   * StrokeTransition - kolor krawędzi
   * PathTransition - ruch wzdłuż ścieżki
   * RotateTransition - obroty
   * ScaleTransition - skalowanie
   * TranslateTransition - przesunięcie
   * 
   * ParallelTransition - składanie równoległe anim.
   * SequentialTransition - składanie sekwencyjne anim.
   * 
   * Timeline, KeyFrame, KeyValue - animacja dowolnych właściwości
   * 
   */
  
  public void setAnimationNo(int animationNo){
	  this.animationNo=animationNo;
	  
	  if (animationNo==1){
		  //User code 2 ...
		  RotateTransition transition = new RotateTransition();
		  transition.setNode(rect);
		  transition.setDuration(Duration.seconds(1));
		  transition.setFromAngle(0);
		  transition.setToAngle(360);
		  transition.setCycleCount(Animation.INDEFINITE);
		  transition.setAutoReverse(true);
		  animation = transition;
		  
	  }
	  else if (animationNo==2){
		  //User code 3 ...
		  TranslateTransition transition = new TranslateTransition();
		  transition.setNode(rect);
		  transition.setDuration(Duration.seconds(5));
		  transition.setFromX(0);
		  transition.setFromY(0);
		  transition.setByX(200);
		  transition.setByY(100);
		  transition.setAutoReverse(true);
		  transition.setCycleCount(2);
		  animation = transition;
		  
	  }
	  else if (animationNo==3){
		  //User code 4 ...
		  Path animationPath = new Path();
		  MoveTo moveTo = new MoveTo(0,50);
		  CubicCurveTo sineCurve = new CubicCurveTo(
				  200,-250,200,250,400,0
				  );
		  animationPath.getElements().addAll(moveTo, sineCurve);
		  PathTransition transition = new PathTransition();
		  transition.setNode(rect);
		  transition.setDuration(Duration.seconds(5));
		  transition.setPath(animationPath);
		  transition.setInterpolator(Interpolator.LINEAR);
		  animation = transition;
	  }
	  else if (animationNo==4){
		  //User code 5 ...
		  TranslateTransition t1 = new TranslateTransition();
		  t1.setDuration(Duration.seconds(2));
		  t1.setByX(400);
		  
		  ScaleTransition s1 = new ScaleTransition();
		  s1.setDuration(Duration.seconds(0.3));
		  s1.setFromX(0.95);
		  s1.setFromY(1.05);
		  s1.setToX(1.05);
		  s1.setToX(0.95);
		  
		  ScaleTransition s2 = new ScaleTransition();
		  s2.setDuration(Duration.seconds(0.3));
		  s2.setFromX(1.05);
		  s2.setFromY(0.95);
		  s2.setToX(0.95);
		  s2.setToX(1.05);
		  
		  PauseTransition p1 = new PauseTransition();
		  p1.setDuration(Duration.seconds(1.4));
		  
		  SequentialTransition seq1 = new SequentialTransition(s1,p1,s2);
		  
		  ParallelTransition par1 = new ParallelTransition(rect, t1, seq1);
		  par1.setCycleCount(Animation.INDEFINITE);
		  par1.setAutoReverse(true);
		  
		  animation=par1;
		  
	  }
	  else if (animationNo==5){
		  //User code 6 ...
		  KeyFrame kf0 = new KeyFrame(
				  Duration.millis(0),
				  new KeyValue(rect.arcWidthProperty(), 0),
				  new KeyValue(rect.arcHeightProperty(),0)
				  );
		  KeyFrame kf1 = new KeyFrame(
				  Duration.millis(800),
				  new KeyValue(rect.arcWidthProperty(), 15),
				  new KeyValue(rect.arcHeightProperty(), 15)
				  );
		  KeyFrame kf2 = new KeyFrame(
				  Duration.millis(1000),
				  new KeyValue(rect.arcWidthProperty(), 60),
				  new KeyValue(rect.arcHeightProperty(), 60)
				  );
		  Timeline tl = new Timeline(kf0, kf1, kf2);
		  animation=tl;
		  
	  } 
	  else if (animationNo==6){
		  //User code 7 ...
	  }
	  
	  
  }
  
  @FXML public void paneMouseClicked(MouseEvent evt){
	  	//User code 8 ...
  }
  
  public void animateCircle(double x, double y){
	  //User code 9 ...
  }
  
  @FXML public void closeAnimationWindow(){
	  animationWindowStage.close();
  }
  
  @FXML public void playAnimation(){
	  animation.play();
  }
  
  @FXML public void pauseAnimation(){
	  animation.pause();
  }
  
  @FXML public void stopAnimation(){
	  animation.stop();
  }

}
