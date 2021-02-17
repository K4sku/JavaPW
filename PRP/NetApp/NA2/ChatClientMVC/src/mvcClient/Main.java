package mvcClient;
	
import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	public static final String appName = "Sockets-JavaFX-MVC";
	
	@Override
	public void start(Stage primaryStage) {
		ViewLoader<AnchorPane, ChatController> viewLoader = new ViewLoader<>("Chat.fxml");
		viewLoader.getController().setUserName(getUserName());
		viewLoader.getController().setHost("localhost");
		viewLoader.getController().setPort(11002);
		viewLoader.getController().run();
		Scene scene = new Scene(viewLoader.getLayout());
		primaryStage.setScene(scene);
		primaryStage.setTitle(appName);
		primaryStage.setOnHiding( e -> primaryStage_Hiding(e, viewLoader.getController()));
		primaryStage.show();
		
	}
	
	

		
	
	private String getUserName() {
		TextInputDialog textInputDialog = new TextInputDialog("Anonymous");
		textInputDialog.setTitle(appName);
		textInputDialog.setHeaderText("Podaj swój pseudonim:");
		textInputDialog.setContentText("Pseudonim:");
		Optional<String> result = textInputDialog.showAndWait();
		return result.orElse("Anonymous");
	}
	
	private void primaryStage_Hiding(WindowEvent e, ChatController controller) {
		try {
			controller.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	
	public static void main(String[] args) {
		launch(args);
	}
}
