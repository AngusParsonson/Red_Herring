package sample;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Menu_Methods {

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
}
