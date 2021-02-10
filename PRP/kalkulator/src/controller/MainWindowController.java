package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Calc;


public class MainWindowController {

	@SuppressWarnings("unused")
	private Main main;
	private Stage primaryStage;
	private String result;
	private StringBuilder equationBuldier = new StringBuilder();
	private StringProperty displayedString;
	private Calc calc = new Calc();

	@FXML private TextField display;
	@FXML private GridPane keyboard;
	@FXML private Label answer;
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
	@FXML private Button zero;
	@FXML private Button one;
	@FXML private Button dot;
	@FXML private Button mul;
	@FXML private Button sub;
	@FXML private Button add;
	@FXML private Button solve;
	@FXML private Button div2;

	@FXML
	public void initialize() {

		displayedString = new SimpleStringProperty();
		display.textProperty().bind(displayedString);
	}

	@FXML
	void equationInput(ActionEvent event) {
		equationBuldier.append(((Button)event.getSource()).getText());
		displayedString.set(equationBuldier.toString());

		System.out.println(equationBuldier);
	}

	@FXML
	void ac(ActionEvent event) {
		if(equationBuldier.length()!=0) {
			equationBuldier.delete(0,equationBuldier.length());
			displayedString.set(equationBuldier.toString());

		}
		answer.setText("");
	}

	@FXML
	void delete(ActionEvent event) {
		if(equationBuldier.length()!=0) {
			equationBuldier.deleteCharAt((equationBuldier.length()-1));
			displayedString.set(equationBuldier.toString());
		}
	}

	@FXML
	void inputAnswer(ActionEvent event) {
		if (null != result) {
			equationBuldier.append(result);
			displayedString.set(equationBuldier.toString());
	}

	}

	@FXML
	void solve(ActionEvent event) {
		System.out.println("Solve: " + equationBuldier.toString());
		var input = equationBuldier.toString();
		result = calc.Calc(input);
		equationBuldier.delete(0, equationBuldier.length());
		equationBuldier.append(result);
		displayedString.set(result);

		answer.setText(result);


	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
