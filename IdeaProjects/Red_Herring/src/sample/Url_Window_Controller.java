package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class Url_Window_Controller {
    private Menu_Controller controller;

    @FXML
    private TextField urlField;

    @FXML
    private Button generateButton;

    /** Handle enter key input **/
    @FXML
    private void handleKeyInput(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER) {
            provideGenerateFunctionality();
        }
    }

    @FXML
    protected void handleGenerateButtonAction(ActionEvent event) throws IOException {
        provideGenerateFunctionality();
    }

    private void provideGenerateFunctionality() {
        String url = urlField.getText();
        controller.open_browser_with_url(url);

        Stage stage = (Stage) generateButton.getScene().getWindow();

        stage.close();
    }

    public void setMenuController(Menu_Controller instance) {
        this.controller = instance;
    }
}
