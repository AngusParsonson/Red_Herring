package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Menu_Methods {

    private String requested_url;

    public Scene create_menu(Stage stage) {
        // create a menu
        javafx.scene.control.Menu project_menu = new javafx.scene.control.Menu("Project");
        javafx.scene.control.Menu edit_menu = new javafx.scene.control.Menu("Edit");
        javafx.scene.control.Menu help_menu = new javafx.scene.control.Menu("Help");
        javafx.scene.control.Menu view_menu = new javafx.scene.control.Menu("View");

        // create menuitems
        MenuItem new_item  = new MenuItem("New");
        MenuItem open_item = new MenuItem("Open");
        MenuItem exit_item = new MenuItem("Exit");

        MenuItem undo_item  = new MenuItem("Undo");
        MenuItem redo_item  = new MenuItem("Redo");

        MenuItem firefox_item = new MenuItem("Firefox");
        MenuItem chrome_item  = new MenuItem("Chrome");
        MenuItem edge_item  = new MenuItem("Edge");
        MenuItem internet_explorer_item  = new MenuItem("Internet Explorer");

        // add menu items to menu
        project_menu.getItems().add(new_item);
        project_menu.getItems().add(open_item);
        project_menu.getItems().add(exit_item);

        edit_menu.getItems().add(undo_item);
        edit_menu.getItems().add(redo_item);

        view_menu.getItems().add(firefox_item);
        view_menu.getItems().add(chrome_item);
        view_menu.getItems().add(edge_item);
        view_menu.getItems().add(internet_explorer_item);

        // setting actions for menu items
        open_item.setOnAction(e -> {
            open_file_chooser(stage);
        });

        new_item.setOnAction(e -> {
            open_enter_url_window();
            System.out.println(requested_url);
        });

        exit_item.setOnAction(e -> {
            stage.close();
        });

        // create a menubar
        MenuBar menu_bar = new MenuBar();

        // add menu to menubar
        menu_bar.getMenus().add(project_menu);
        menu_bar.getMenus().add(edit_menu);
        menu_bar.getMenus().add(view_menu);
        menu_bar.getMenus().add(help_menu);

        // create a VBox
        VBox vbox = new VBox(menu_bar);

        // create a scene
        Scene scene = new Scene(vbox, 1000, 800);

        return scene;
    }

    public File open_file_chooser(Stage stage) {
        FileChooser file_chooser = new FileChooser();

        File selected_file = file_chooser.showOpenDialog(stage);

        return selected_file;
    }

    public void open_enter_url_window2() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("sample.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 300, 50);
            Stage stage = new Stage();
            stage.setTitle("Enter url");
            stage.getIcons().add(new Image("resources/logo.png"));

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to create new window");
        }
    }

    public void open_enter_url_window() {
        TextInputDialog url_window = new TextInputDialog("");

        url_window.setTitle("Enter url");
        url_window.setHeaderText(null);
        url_window.setContentText("url:");

        Optional<String> result = url_window.showAndWait();

        if (result.isPresent()) {
            requested_url = (result.get());
        }

    }
}
