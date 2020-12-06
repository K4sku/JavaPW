package application;
	
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			

			
			Label label1 = new Label("label 1");
			Label label2 = new Label("label 2");
			VBox left_vbox = new VBox(10);
			left_vbox.getChildren().addAll(label1, label2);
			root.setLeft(left_vbox);
			
			Label label = new Label("HelloFX");
			label.setLayoutY(-20);
			TextField text1 = new TextField();
			text1.setLayoutY(0);
			var text2 = new TextField();
			text2.setLayoutY(25);
			
			var center_group = new Group();
			center_group.getChildren().addAll(text1, text2, label);
			root.setCenter(center_group);
			
			Image center_image = new Image("https://media.tenor.com/images/aee32a79b20021734a17eef456ea0d74/tenor.gif");
			ImageView center_image_view = new ImageView(center_image);
			root.setBottom(center_image_view);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
