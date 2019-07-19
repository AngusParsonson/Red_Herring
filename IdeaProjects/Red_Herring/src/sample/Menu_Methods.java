package sample;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Optional;

public class Menu_Methods {

    private String requested_url = null;
    private WebView page;

    public Scene create_menu(Stage stage) {
        // create layout to position nodes
        VBox vbox = new VBox();

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
        project_menu.getItems().addAll(new_item, open_item, exit_item);

        edit_menu.getItems().addAll(undo_item, redo_item);

        view_menu.getItems().addAll(firefox_item, chrome_item, edge_item, internet_explorer_item);

        // setting actions for menu items
        open_item.setOnAction(e -> {
            open_file_chooser(stage);
        });

        new_item.setOnAction(e -> {
            open_enter_url_window();

            if (vbox.getChildren().size() == 2) vbox.getChildren().remove(1);
            if ( requested_url != null ) vbox.getChildren().addAll(page);
        });

        exit_item.setOnAction(e -> {
            stage.close();
        });

        // create a menubar
        MenuBar menu_bar = new MenuBar();

        // add menu to menubar
        menu_bar.getMenus().addAll(project_menu, edit_menu, view_menu, help_menu);

        // create a VBox
        vbox.getChildren().add(menu_bar);

        // create a scene
        Scene scene = new Scene(vbox, 1000, 600);

        return scene;
    }

    public File open_file_chooser(Stage stage) {
        FileChooser file_chooser = new FileChooser();

        File selected_file = file_chooser.showOpenDialog(stage);

        return selected_file;
    }

    public void open_enter_url_window() {
        TextInputDialog url_window = new TextInputDialog("");

        url_window.setTitle("Enter url");
        url_window.setHeaderText(null);
        url_window.setContentText("url:");

        Optional<String> result = url_window.showAndWait();

        if (result.isPresent()) {
            requested_url = (result.get());

            Browser browser = new Browser();
            page = browser.browse_url(requested_url);
        }
    }
}
