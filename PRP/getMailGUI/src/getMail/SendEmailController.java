package getMail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;


public class SendEmailController implements AutoCloseable {

    private String userEmail;
    private String userPassword;

    @FXML
    private TextField toTextField;

    @FXML
    private TextField subjectTextField;

    @FXML
    private HTMLEditor htmlEditorInput;

    @FXML
    private Button sendBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private Label invalidEmailAddressLabel;

    @FXML
    private Label invalidSubjectLabel;


    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void sendEmail(ActionEvent event) {
        boolean validEmailComposition = true;


        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(toTextField.getText())) {

            validEmailComposition = false;
            invalidEmailAddressLabel.setVisible(true);
            toTextField.textProperty().addListener((observable, oldV, newV) ->
                    invalidEmailAddressLabel.setVisible(false));
        }

        if (subjectTextField.getText().isBlank()) {
            validEmailComposition = false;
            invalidSubjectLabel.setVisible(true);
            subjectTextField.textProperty().addListener((observable, oldV, newV) ->
                    invalidSubjectLabel.setVisible(false));
        }

        if (validEmailComposition) {
            EmailSender sendMail = new EmailSender(userEmail,
                    userPassword,
                    toTextField.getText(),
                    subjectTextField.getText(),
                    htmlEditorInput.getHtmlText()
            );
            Stage stage = (Stage) sendBtn.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void close() throws Exception {

    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
