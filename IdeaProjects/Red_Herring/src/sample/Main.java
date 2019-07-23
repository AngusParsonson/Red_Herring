package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Red Herring");
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(this.getClass().getResource("/resources/css/menu.css").toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
