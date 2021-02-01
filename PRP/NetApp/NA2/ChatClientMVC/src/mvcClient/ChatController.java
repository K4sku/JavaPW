package mvcClient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChatController {
	private Main main;
	private Stage primaryStage;

    @FXML
    private Label welcomeLabel;

    public void setMain(Main main) {
    	this.main = main;
    }
    
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
