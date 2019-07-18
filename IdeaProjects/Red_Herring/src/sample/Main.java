package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Red Herring");
        primaryStage.getIcons().add(new Image("resources/logo.png"));

        primaryStage.setScene(createMenu());
        primaryStage.show();
    }

    public Scene createMenu() {
        // create a menu
        Menu project_menu = new Menu("Project");
        Menu edit_menu = new Menu("Edit");
        Menu help_menu = new Menu("Help");
        Menu view_menu = new Menu("View");

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

    public static void main(String[] args) {
        launch(args);
    }
}
