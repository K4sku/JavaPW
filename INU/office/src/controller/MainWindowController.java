package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Person;

import java.io.*;
import java.util.Scanner;

public class MainWindowController {
	@SuppressWarnings("unused")
	private Main main;
	private Stage primaryStage;
	private File selectedFile;

	@FXML
	private TableView<Person> tableView;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;
	@FXML
	private TableColumn<Person, String> roomNoColumn;

	@FXML
	private Button loadButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button changeButton;
	@FXML
	private Button reportButton;

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField roomNoField;
	@FXML
	private TextField startHoursField;
	@FXML
	private TextField endHoursField;

	@FXML
	private Label floorLabel;

	@FXML
	private Canvas planCanvas;

	private ObservableList<Person> personList =
			FXCollections.observableArrayList();

	public void initialize() {
		GraphicsContext graphicsContext = planCanvas.getGraphicsContext2D();
		tableView.setItems(personList);

		tableView.getSelectionModel().selectedItemProperty().addListener(
				(ov, oldVal, newVal)->{
					firstNameField.setText(newVal.getFirstName());
					lastNameField.setText(newVal.getLastName());
					roomNoField.setText(newVal.getRoomNo());
					startHoursField.setText(String.valueOf(newVal.getStartHour()));
					endHoursField.setText(String.valueOf(newVal.getEndHour()));

					graphicsContext.drawImage(loadImage(newVal.getRoomNo()), 0,0, 340,350);
				}
		);

		firstNameColumn.setCellValueFactory(
				new PropertyValueFactory<Person, String>("firstName")
		);
		lastNameColumn.setCellValueFactory(
				new PropertyValueFactory<Person, String>("lastName")
		);
		roomNoColumn.setCellValueFactory(
				new PropertyValueFactory<Person, String>("roomNo")
		);

		roomNoField.focusedProperty().addListener(
				(ov, oldVal, newVal)-> {
				if (!newVal) verifyThreeDigits();
				}
		);


	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	void addEmployee(ActionEvent event) {
		personList.add(new Person(
				firstNameField.getText(),
				lastNameField.getText(),
				roomNoField.getText(),
				Integer.parseInt(startHoursField.getText()),
				Integer.parseInt(endHoursField.getText()))
		);
	}

	@FXML
	void changeEmployee(ActionEvent event) {
		var p =  tableView.getSelectionModel().selectedItemProperty().getValue();
		System.out.println(p);
		p.setFirstName(firstNameField.getText());
		p.setLastName(lastNameField.getText());
		p.setRoomNo(roomNoField.getText());
		p.setStartHour(Integer.parseInt(startHoursField.getText()));
		p.setEndHour(Integer.parseInt(endHoursField.getText()));

		tableView.refresh();
	}

	@FXML
	void deleteEmployee(ActionEvent event) {
		personList.remove(tableView.getSelectionModel().selectedItemProperty().getValue());
	}

	@FXML
	void importFile(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			selectedFile = fileChooser.showOpenDialog(primaryStage.getScene().getWindow());
			Scanner scanner = new Scanner(selectedFile);

			while (scanner.hasNextLine()) {
				var firstName = scanner.next();
				var lastName = scanner.next();
				var roomNo = scanner.next();
				var startHour = scanner.nextInt();
				var endHour = scanner.nextInt();

				personList.add(new Person(firstName, lastName, roomNo, startHour, endHour));

			}
		}
		catch (NullPointerException | IllegalArgumentException | FileNotFoundException e) {
			System.out.println("File was not imported");
			e.printStackTrace();
		}
	}

	@FXML
	void saveFile(ActionEvent event) {
		try {
			writeToFile(personList, selectedFile);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	@FXML
	void saveReport(ActionEvent event) {
		var sortedPersonList = FXCollections.observableArrayList(personList);
		sortedPersonList.sort(Person::compareTo);

		try {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);
			var saveReportFile = fileChooser.showSaveDialog(primaryStage.getScene().getWindow());
			writeToFile(sortedPersonList, saveReportFile);
		}
		catch (NullPointerException | IllegalArgumentException | IOException e) {
			System.out.println("File was not imported");
			e.printStackTrace();

		}
	}

	@FXML
	void verifyThreeDigits() {
		try {
			var roomNo = Integer.valueOf(roomNoField.getText());
			roomNoField.setText(String.format("%03d", roomNo));
		} catch (NumberFormatException e) {

		}
	}


	private void writeToFile(ObservableList<Person> personList, File saveReportFile) throws IOException {
		FileWriter writer = new FileWriter(saveReportFile);

		for (int i = 0; i < personList.size(); i++) {
			writer.write(
					personList.get(i).getFirstName() +" " +
							personList.get(i).getLastName() +" " +
							personList.get(i).getRoomNo() +" " +
							personList.get(i).getStartHour() +" " +
							personList.get(i).getEndHour()
			);
			if (i+1 != personList.size()) {	writer.write("\n");	}
		}
		writer.close();
		System.out.println("Successfully wrote to the file.");
	}

	private Image loadImage(String roomNo) {
		try {
			FileInputStream fileInputStream = new FileInputStream("data/"+roomNo+".png");
			floorLabel.setText("Piętro " + roomNo.charAt(0));
			return new Image(fileInputStream);
		} catch (FileNotFoundException e) {
			floorLabel.setText("");
			return new Image("https://i.imgur.com/JtX3Yy1.png");
		}
	}
}
