package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Red Herring");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    public void createMenu() {
        // create a menu
        Menu m = new Menu("Project");

        // create menuitems
        MenuItem m1 = new MenuItem("New");
        MenuItem m2 = new MenuItem("Open");
        MenuItem m3 = new MenuItem("Exit");

        // create a menubar
        MenuBar mb = new MenuBar();

        // add menu to menubar
        mb.getMenus().add(m);

        // create a VBox
        VBox vb = new VBox(mb);

        // create a scene
        Scene sc = new Scene(vb, 500, 300);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
