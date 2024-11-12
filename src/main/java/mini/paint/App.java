package mini.paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    // Scene for the application
    private static Scene scene;

    // This method is called at the start of the application
    @Override
    public void start(Stage stage) throws IOException {
        // Load the main FXML file and create a Scene with it
        scene = new Scene(loadFXML("main"), 1200, 900);
        // Set the scene for the stage
        stage.setScene(scene);
        // Display the stage
        stage.show();
    }

    // Method to set the root of the scene
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // Method to load an FXML file
    private static Parent loadFXML(String fxml) throws IOException {
        // Create a FXMLLoader with the specified FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        // Load the FXML file
        return fxmlLoader.load();
    }

    // Main method to launch the application
    public static void main(String[] args) {
        System.out.println("Launching the application...");
        launch();
    }

}