package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class Menu_Controller implements Initializable {

        /** All functionality is wrapped in a separate function to allow for use in key shortcuts **/
        @FXML
        public GridPane gridPane;

        @FXML
        private WebView webView;

        @FXML
        private MenuBar menuBar;

        /** Handle key shortcut inputs **/
        @FXML
        private void handleKeyInput(final InputEvent event)
        {
            if (event instanceof KeyEvent)
            {
                final KeyEvent keyEvent = (KeyEvent) event;
                if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A)
                {
                    provideAboutFunctionality();
                }
                if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.N)
                {
                    provideNewFunctionality();
                }
            }
        }

        /** Logic for About menu option **/
        @FXML
        private void handleAboutAction(final ActionEvent event) {
            provideAboutFunctionality();
        }

        /** Logic for New menu option **/
        @FXML
        private void handleNewAction(final ActionEvent event) {
            provideNewFunctionality();
        }

        /** Logic for Open menu option **/
        @FXML
        private void handleOpenAction(final ActionEvent e) {
            provideOpenFunctionality(e);
        }

        private void provideAboutFunctionality() {
            System.out.println("You clicked on About!");
        }

        private void provideNewFunctionality() {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("url_window.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), 400, 50);
                Stage stage = new Stage();
                stage.setTitle("Enter url");

                Url_Window_Controller controller = fxmlLoader.getController();
                controller.setMenuController(this);

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Failed to create new window");
            }
        }

        private void provideOpenFunctionality(ActionEvent e) {
            FileChooser file_chooser = new FileChooser();

            File selected_file = file_chooser.showOpenDialog(menuBar.getScene().getWindow());
        }

        @Override
        public void initialize(java.net.URL arg0, ResourceBundle arg1) {
            menuBar.setFocusTraversable(true);

        }

        public void open_browser_with_url(String url) {
            webView.getEngine().load(url);
        }
}

