package koder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private RadioButton radioKod;
    @FXML private RadioButton radioDekod;

    @FXML
    private ToggleGroup operationTG;

    @FXML
    void copyAction(ActionEvent event) {
        text1.setText(text2.getText());
        text2.setText("");
    }

    @FXML
    void executeAction(ActionEvent event) {
        if (operationTG.getSelectedToggle() == radioKod)
            text2.setText(new RLE().encode(text1.getText()));
        else
            text2.setText(new RLE().decode(text1.getText()));

    }

}
