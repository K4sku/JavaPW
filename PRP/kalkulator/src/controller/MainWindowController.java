package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MainWindowController {

	@SuppressWarnings("unused")
	private Main main;
	private Stage primaryStage;
	private String input;
	private StringBuilder equationBuldier = new StringBuilder();

	@FXML private TextField display;
	@FXML private GridPane keyboard;
	@FXML private Button two;
	@FXML private Button three;
	@FXML private Button four;
	@FXML private Button five;
	@FXML private Button six;
	@FXML private Button seven;
	@FXML private Button eight;
	@FXML private Button nine;
	@FXML private Button open;
	@FXML private Button close;
	@FXML private Button ac;
	@FXML private Button del;
	@FXML private Button sgn;
	@FXML private Button percent;
	@FXML private Button zero;
	@FXML private Button one;
	@FXML private Button dot;
	@FXML private Button mul;
	@FXML private Button sub;
	@FXML private Button add;
	@FXML private Button solve;
	@FXML private Button div2;

	@FXML
	void equationInput(ActionEvent event) {
		equationBuldier.append(((Button)event.getSource()).getText());
		System.out.println(equationBuldier);

	}

	@FXML
	void ac(ActionEvent event) {
		if(equationBuldier.length()!=0) {
			equationBuldier.delete(0,equationBuldier.length());
			System.out.println(equationBuldier);
		}
		display.setText("");
	}

	@FXML
	void delete(ActionEvent event) {
		if(equationBuldier.length()!=0) {
			equationBuldier.deleteCharAt((equationBuldier.length()-1));
			System.out.println(equationBuldier);
		}
	}

	@FXML
	void percent(ActionEvent event) {

	}

	@FXML
	void solve(ActionEvent event) {

	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML public void closeStage() {
		primaryStage.close();
	}

}
