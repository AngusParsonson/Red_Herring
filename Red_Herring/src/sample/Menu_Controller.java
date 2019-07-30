package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

public class Menu_Controller implements Initializable {

    /** All functionality is wrapped in a separate function to allow for use in key shortcuts **/
    @FXML
    private WebView webView;

    @FXML
    private MenuBar menuBar;

    @FXML
    private VBox tab_vbox;

    @FXML
    private ToggleButton deploy_button;
    @FXML
    private ToggleButton view_button;
    @FXML
    private ToggleButton browser_button;
    @FXML
    private ToggleButton edit_button;

    @FXML
    private TabPane tab_pane;

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
            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.O)
            {
                provideOpenFunctionality();
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
        provideOpenFunctionality();
    }

    @FXML
    private void handleDeployButtonAction(final ActionEvent e) {
        if (deploy_button.isSelected()) {
            view_button.setSelected(false);
            edit_button.setSelected(false);
            browser_button.setSelected(false);
        }
        else;
    }

    @FXML
    private void handleViewButtonAction(final ActionEvent e) {
        if (view_button.isSelected()) {
            deploy_button.setSelected(false);
            edit_button.setSelected(false);
            browser_button.setSelected(false);
        }
        else;
    }

    @FXML
    private void handleBrowserButtonAction(final ActionEvent e) {
        if (browser_button.isSelected()) {
            view_button.setSelected(false);
            deploy_button.setSelected(false);
            edit_button.setSelected(false);

            open_browser_with_url("https://www.google.com");
        }
        else;
    }

    @FXML
    private void handleEditButtonAction(final ActionEvent e) {
        if (edit_button.isSelected()) {
            view_button.setSelected(false);
            deploy_button.setSelected(false);
            browser_button.setSelected(false);
        }
        else;
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
        open_new_tab("Project " + tab_pane.getTabs().size());
    }

    private void provideOpenFunctionality() {
        FileChooser file_chooser = new FileChooser();

        File selected_file = file_chooser.showOpenDialog(menuBar.getScene().getWindow());
        String url = null;

        try {
            url = selected_file.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            // No file was selected, no need to throw exception
        }
        String project_name = selected_file.getName().split("\\.")[0];

        open_new_tab(project_name);
        open_browser_with_url(url);
    }

    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
        menuBar.setFocusTraversable(true);
        tab_pane.getTabs().clear();

        tab_pane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                try {
                    oldTab.setContent(null);
                } catch (NullPointerException ex){}
                try {
                    newTab.setContent(tab_vbox);
                } catch (NullPointerException ex){}
            }
        });

    }

    public void open_browser_with_url(String url) {
        webView.getEngine().load(url);
    }

    public void open_text_view_with_url(String url) {

    }

    public void open_new_tab(String project_name) {
        tab_pane.getTabs().add(new Tab(project_name));
    }
}
