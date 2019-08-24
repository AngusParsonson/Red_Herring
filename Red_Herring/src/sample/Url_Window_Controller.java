package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class Url_Window_Controller {
    private Menu_Controller controller;

    @FXML
    private TextField urlField;

    @FXML
    private TextField projectField;

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
        String project_name = projectField.getText();

        try {
            File sites_dir = new File("C:\\Users\\Angus Parsonson\\Documents\\Computer Science\\Academy\\Red_Herring\\Red_Herring\\Sites");

            Process p = Runtime.getRuntime().exec(new String[]{"C:\\Python27\\python.exe", "C:\\Users\\Angus Parsonson\\Documents\\Computer Science\\Academy\\Red_Herring\\Red_Herring\\backend\\siteGrab.py", url, project_name}, null, sites_dir);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;

            while ((line = in.readLine()) != null) {
                //System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        File generated_file = new File("Sites\\" + project_name + "\\" + project_name + ".html");

        try {
            url = generated_file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        controller.open_browser_with_url(url);

        TabPane tab_pane = controller.tab_pane;
        tab_pane.getTabs().get(tab_pane.getTabs().size() - 1).setId(generated_file.toString());
        tab_pane.getTabs().get(tab_pane.getTabs().size() - 1).setText(project_name);
        controller.open_text_view_with_location(generated_file.toString());

        Stage stage = (Stage) generateButton.getScene().getWindow();

        stage.close();
    }

    public void setMenuController(Menu_Controller instance) {
        this.controller = instance;
    }
}