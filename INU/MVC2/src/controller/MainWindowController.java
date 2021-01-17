package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Person;

public class MainWindowController {
	@SuppressWarnings("unused")
	private Main main;
	private Stage primaryStage;
	
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private TableColumn<Person, String> idColumn;
	
    private ObservableList<Person> personList =
    		FXCollections.observableArrayList();
    
    public void setTable() {
    	personList.add(new Person("1", "Anakin", "Skywalker"));
    	personList.add(new Person("2", "Leia", "Organa"));
    	personList.add(new Person("3", "Luke", "Skywalker"));
    	personList.add(new Person("4", "Obi", "Kenobi"));
    	
    	tableView.setItems(personList);
    	
    	tableView.getSelectionModel().selectedItemProperty().addListener(
    			(ov, oldVal, newVal)->{
    				System.out.println(
    						newVal.getFirstName() +" "+
    						newVal.getLastName() +" "+
    						newVal.getId()
    						);
    			}
    			);
    }
    
    
    public void initialize() {
    	firstNameColumn.setCellValueFactory(
    			new PropertyValueFactory<Person, String>("firstName") 
    			);
    	lastNameColumn.setCellValueFactory(
    			new PropertyValueFactory<Person, String>("lastName") 
    			);
    	idColumn.setCellValueFactory(
    			new PropertyValueFactory<Person, String>("id") 
    			);
    }
    
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setPriamryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML public void closeStage() {
		primaryStage.close();
	}
}
