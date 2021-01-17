package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Person;

public class MainWindowController {
	private Main main;
	public Person person;
	
	@FXML private Button button;
	@FXML private TextField field;
	@FXML private Label label;
	
	public void setMain(Main main) {
		this.main = main;
		person = new Person(10, "Jan", "Kowalski");
	}
	
	@FXML public void handleButton() {
		System.out.println("Button pressed.");
		label.setText(field.getText());
		person.setLastName(field.getText());
		field.clear();
		
	}
	
}
