package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

public class Menu_Controller implements Initializable {

    /** All functionality is wrapped in a separate function to allow for use in key shortcuts **/
    String file_location;
    String current_browser_url = "https://www.google.com";

    @FXML
    private WebView webView;

    @FXML
    private MenuBar menuBar;

    @FXML
    private VBox tab_vbox;

    @FXML
    public TextArea text_area;
    
    @FXML
    private ToggleButton view_button;
    @FXML
    private ToggleButton browser_button;
    @FXML
    private ToggleButton edit_button;

    /** The ID for the FXML tab objects are set to the path of the file the tab is corresponds to - this avoids
       having to create a whole new custom tab class **/
    @FXML
    public TabPane tab_pane;

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
        edit_button.setSelected(false);
        browser_button.setSelected(false);
        view_button.setSelected(false);
    }

    @FXML
    private void handleUpdateButtonAction(final ActionEvent e) throws MalformedURLException {
        edit_button.setSelected(false);
        browser_button.setSelected(false);
        view_button.setSelected(false);

        File fold=new File(file_location);
        fold.delete();
        File fnew=new File(file_location);
        String source = text_area.getText();

        try {
            FileWriter f2 = new FileWriter(fnew, false);
            f2.write(source);
            f2.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        open_browser_with_url(fnew.toURI().toURL().toString());
    }

    @FXML
    private void handleViewButtonAction(final ActionEvent e) throws MalformedURLException {
        Tab tab = tab_pane.getSelectionModel().getSelectedItem();
        open_browser_with_url(new File (tab.getId()).toURI().toURL().toString());

    }

    @FXML
    private void handleBrowserButtonAction(final ActionEvent e) {
        open_browser_with_url(current_browser_url);
    }

    @FXML
    private void handleEditButtonAction(final ActionEvent e) {
    }

    private void provideAboutFunctionality() {

    }

    private void provideNewFunctionality() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("url_window.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 400, 90);
            Stage stage = new Stage();
            stage.setTitle("Enter url");

            Url_Window_Controller controller = fxmlLoader.getController();
            controller.setMenuController(this);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        open_new_tab("Project " + tab_pane.getTabs().size(), "url");
    }

    private void provideOpenFunctionality() {
        FileChooser file_chooser = new FileChooser();

        File selected_file = file_chooser.showOpenDialog(menuBar.getScene().getWindow());
        String url = null;

        try {
            url = selected_file.toURI().toURL().toString();
            System.out.println(url);

            String project_name = selected_file.getName().split("\\.")[0];
            file_location = selected_file.toString();

            open_new_tab(project_name, file_location);
            open_browser_with_url(url);
            open_text_view_with_location(file_location);

        } catch (MalformedURLException ex) {
            ex.printStackTrace();

        } catch (NullPointerException ex) {
            // No file was selected, no need to throw exception
        }
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
                    view_button.setSelected(true);
                    edit_button.setSelected(false);
                    browser_button.setSelected(false);

                    open_browser_with_url(new File (newTab.getId()).toURI().toURL().toString());
                    open_text_view_with_location(newTab.getId());

                } catch (NullPointerException ex){} catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void open_browser_with_url(String url) {
        webView.getEngine().load(url);
    }

    public void open_text_view_with_location(String location) {
        String content = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(location));
            String str;
            while ((str = in.readLine()) != null) {
                content += str;
                content += "\n";
            }
            in.close();
        } catch (IOException e) {
        }
        text_area.setText(content);
    }

    public void open_new_tab(String project_name, String id) {
        Tab tab = new Tab(project_name);
        tab.setId(id);
        tab_pane.getTabs().add(tab);
        tab_pane.getSelectionModel().select(tab);
    }
}
