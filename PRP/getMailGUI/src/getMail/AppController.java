package getMail;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

public class AppController implements AutoCloseable {
    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    private boolean userLoggedIn;

    @FXML
    private Button newEmailBtn;

    @FXML
    private TextField userEmail;

    @FXML
    private Button loginBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private PasswordField userPassword;

    @FXML
    private TreeView<Folder> folderTreeView;

    @FXML
    private TableView<MessageHeaderWrapper> messageSelectionTable;

    @FXML
    private TableColumn<MessageHeaderWrapper, String> senderColumn;

    @FXML
    private TableColumn<MessageHeaderWrapper, String> subjectColumn;

    @FXML
    private TableColumn<MessageHeaderWrapper, String> receivedColumn;

    @FXML
    private WebView messageDisplayWebView;

    private final ObservableList<MessageHeaderWrapper> messagesInSelectedFolder = FXCollections.observableArrayList();
    private final Hashtable<String, Integer> foldersMessagesCountTable = new Hashtable<>();
    private Message[] messages;
    private MessageHeaderWrapper[] messagesHeaders;
    private TreeItem<Folder> inbox = new TreeItem<>();
    private MailConnection connection;
    private Document emailView;
    private String[] selectedEmailContent = {"", ""};

    @FXML
    void initialize() {
        messageSelectionTable.setItems(messagesInSelectedFolder);
        folderTreeView.setRoot(inbox);

        messageSelectionTable.getSelectionModel().selectedItemProperty().addListener(listener2);

        senderColumn.setCellValueFactory(new PropertyValueFactory<MessageHeaderWrapper, String>("fromDisplay"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<MessageHeaderWrapper, String>("subject"));
        receivedColumn.setCellValueFactory(new PropertyValueFactory<MessageHeaderWrapper, String>("receivedDisplayedDate"));

    }

    @FXML
    void loginAction(ActionEvent event) {
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(userEmail.getText())) {
            connection = new MailConnection(userEmail.getText(), userPassword.getText());
            System.out.println("email: " + userEmail.getText() + ", password: " + userPassword.getText());
            System.out.println("Is Connected?: " + connection.isConnected());
            setUserLoggedIn(true);

            inbox = connection.readFolderStructure();
            folderTreeView.getSelectionModel().selectedItemProperty().addListener(listener);

            folderTreeView.setRoot(inbox);
            folderTreeView.getRoot().setExpanded(true);
            folderTreeView.setShowRoot(false);

            userEmail.setDisable(true);
            userPassword.setDisable(true);
            loginBtn.setDisable(true);
            logoutBtn.setDisable(false);
            newEmailBtn.setDisable(false);
        }


    }

    private final ChangeListener<TreeItem<Folder>> listener = (observableValue, treeItem, t1) -> {
//        System.out.println("Selected folder:"+ t1.getValue());
//        System.out.println("path:"+ getFolderPath(t1));
        var selectedFolderPath = getFolderPath(t1);
        if (t1 != treeItem) {
            messagesInSelectedFolder.clear();
            messageSelectionTable.refresh();
            foldersMessagesCountTable.put(selectedFolderPath, connection.getMessagesInFolderCount(selectedFolderPath));
            messages = connection.fetchMessages(selectedFolderPath, foldersMessagesCountTable.get(selectedFolderPath));
            Collections.reverse(Arrays.asList(messages));
            for (Message msg : messages) {
                messagesInSelectedFolder.add(new MessageHeaderWrapper(msg));
            }
        }

    };

    private final ChangeListener<MessageHeaderWrapper> listener2 = (observableValue, oldVal, newVal) -> {
        System.out.println(newVal.getSubject());
        selectedEmailContent = connection.loadMessageContent(newVal.getFolder(), newVal.getMessageNumber());
        emailView = Jsoup.parse(
                selectedEmailContent[1],
                selectedEmailContent[0],
                Parser.xmlParser()
        );
        messageDisplayWebView.getEngine().loadContent(emailView.html());
    };


    private String getFolderPath(TreeItem folderItem) {

        String folderPath = folderItem.getValue().toString();
        while (folderItem.getParent() != null) {
            folderPath = appendFolderPathWithParent(folderItem, folderPath);
            folderItem = folderItem.getParent();
        }
        return folderPath.substring(1);
    }

    private String appendFolderPathWithParent(TreeItem folderItem, String folderPath) {
        return folderItem.getParent().getValue().toString() + "/" + folderPath;
    }

    @FXML
    void logoutAction(ActionEvent event) {
        folderTreeView.getSelectionModel().selectedItemProperty().removeListener(listener);
        messagesInSelectedFolder.clear();
        messageSelectionTable.refresh();
        messageSelectionTable.getSelectionModel().selectedItemProperty().removeListener(listener2);
        messageDisplayWebView.getEngine().loadContent("");

        connection.close();
        setUserLoggedIn(false);

        inbox = new TreeItem();
        folderTreeView.setRoot(inbox);

        userEmail.setDisable(false);
        userPassword.setDisable(false);
        loginBtn.setDisable(false);
        logoutBtn.setDisable(true);
        newEmailBtn.setDisable(true);
    }


    @FXML
    void newEmailAction(ActionEvent event) {
        if (isUserLoggedIn()) {
            ViewLoader<AnchorPane, SendEmailController> sendEmailViewLoader = new ViewLoader<>("SendEmailWindow.fxml");
            sendEmailViewLoader.getController().setUserEmail(userEmail.getText());
            sendEmailViewLoader.getController().setUserPassword(userPassword.getText());

            Scene scene = new Scene(sendEmailViewLoader.getLayout());
            Stage stage = new Stage();
            stage.setTitle("New Email");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void run() {

    }

    @Override
    public void close() throws Exception {

    }


}


