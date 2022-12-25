//package project;


import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author erenraich
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("hangman.fxml"));
            Scene scene = new Scene(root); // attach scene graph to scene
            Image icon = new Image("file:../img/hangman.png");
            // Set the icon for the stage
            stage.getIcons().add(icon);
            stage.setTitle("Hangman"); // displayed in window's title bar
            stage.setScene(scene); // attach scene to stage
            stage.show(); // display the stage
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

}
