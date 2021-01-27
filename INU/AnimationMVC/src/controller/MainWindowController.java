package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainWindowController {
  private Main main;
  private Stage primaryStage;
  
  public void setMain(Main main){
	  this.main=main;
  }
  
  public void setStage(Stage primaryStage){
	  this.primaryStage=primaryStage;
  }
  
  @FXML public void closeMainWindow(){
	 primaryStage.close(); 
  }
  
  @FXML public void imageViewClicked(MouseEvent evt){
	  int num=0;
	  
	  //user code 10
	  int colWidth = 150;
	  int rowHight = 150;
	  int clickedCol = (int) evt.getX() / colWidth;
	  int clickedRow = (int) evt.getY() / rowHight;
	  
	  int colCount = 3;
	  int rowCount = 3;
	  num = 1 + clickedCol + clickedRow * colCount;
	  
	  System.out.println(clickedCol+","+clickedRow);
	  
	  System.out.println("Wybrana cyfra "+num);
	  
	  if (num>=1 && num<=7)
	    animationWindow(num);
  }
  
  public void animationWindow(int animationNo){
	  FXMLLoader loader=new FXMLLoader(
				Main.class.getResource("/view/AnimationWindowView.fxml"));
		try {
			AnchorPane pane=loader.load();
			
			Stage animationWindowStage=new Stage();
			animationWindowStage.setMinWidth(500.0);
			animationWindowStage.setMinHeight(500.0);
			animationWindowStage.setTitle("Animacja");
			animationWindowStage.initModality(Modality.WINDOW_MODAL);
			animationWindowStage.initOwner(primaryStage);
			Scene scene=new Scene(pane);
			
			AnimationWindowController animationWindowController=
					loader.getController();
			animationWindowController.setStage(animationWindowStage);
			animationWindowController.setAnimationNo(animationNo);
			animationWindowStage.setScene(scene);
			animationWindowStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
}
