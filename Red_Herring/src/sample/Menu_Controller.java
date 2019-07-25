package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

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
    private ToggleButton edit_button;

    @FXML
    private TabPane tab_pane;

    private List<Tab> tab_list = new ArrayList<Tab>();
    private int current_tab = 0;

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

    @FXML
    private void handleDeployButtonAction(final ActionEvent e) {
        if (deploy_button.isSelected()) {
            view_button.setSelected(false);
            edit_button.setSelected(false);
            open_new_tab("Project 3");
        }
        else;
    }

    @FXML
    private void handleViewButtonAction(final ActionEvent e) {
        if (view_button.isSelected()) {
            deploy_button.setSelected(false);
            edit_button.setSelected(false);
        }
        else;
    }

    @FXML
    private void handleEditButtonAction(final ActionEvent e) {
        if (edit_button.isSelected()) {
            view_button.setSelected(false);
            deploy_button.setSelected(false);
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
    }

    private void provideOpenFunctionality(ActionEvent e) {
        FileChooser file_chooser = new FileChooser();

        File selected_file = file_chooser.showOpenDialog(menuBar.getScene().getWindow());
        String url = null;
        try {
            url = selected_file.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        open_browser_with_url(url);

    }

    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
        menuBar.setFocusTraversable(true);
    }

    public void open_browser_with_url(String url) {
        webView.getEngine().load(url);
    }

    public void open_new_tab(String project_name) {
        tab_list.add(new Tab(project_name));
        current_tab = tab_list.size() - 1;

        tab_list.get(current_tab).setContent(tab_vbox);

        tab_pane.getTabs().add(tab_list.get(current_tab));
        tab_pane.getSelectionModel().select(tab_list.get(current_tab));
    }
}
