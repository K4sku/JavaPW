package mvcClient;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MessageBox {
	 
	private MessageBox() { }
	public static Optional<ButtonType> show(AlertType alertType, String title, String contentText, String headerText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(contentText);
		alert.setHeaderText(headerText);
		return alert.showAndWait();
	}
}