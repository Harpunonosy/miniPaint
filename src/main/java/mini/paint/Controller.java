package mini.paint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import mini.paint.Interfaces.InterfaceForDrawers;
import mini.paint.shapes.MyCircle;
import mini.paint.shapes.MyRectangle;
import mini.paint.shapes.MyTriangle;
import javafx.scene.paint.Paint;

public class Controller {

    @FXML
    private AnchorPane MainPane; // The main window of the application

    @FXML
    private AnchorPane BOARD; // The main drawing board where shapes are drawn

    @FXML
    private Button BTN_C;  // Button for drawing circles

    @FXML
    private Button BTN_R;  // Button for drawing rectangles

    @FXML
    private Button BTN_T;  // Button for drawing triangles

    @FXML
    private Button BTN_INFO;  // Button for displaying information

    @FXML
    private Button BTN_CLEAR;  // Button for clearing the drawing board

    @FXML
    private Button BTN_UNDO;  // Button for undoing the last action

    @FXML
    private MenuButton MENU_FILE; // Menu button for file operations

    @FXML
    private MenuItem FILE_OPEN;  // Menu item for opening a file

    @FXML
    private MenuItem FILE_SAVE;  // Menu item for saving the current state to a file

    @FXML                       
    private ImageView IMG_C;    // Image for the circle button

    @FXML
    private ImageView IMG_R;    // Image for the rectangle button

    @FXML
    private ImageView IMG_T;   // Image for the triangle button

    @FXML
    void ON_CLICK_C(ActionEvent event) { // Event handler for the circle button
        drawer = new Drawer.CircleDrawer();
        BOARD_CLICKABLE();        
    }

    @FXML
    void ON_CLICK_R(ActionEvent event) { // Event handler for the rectangle button
        drawer = new Drawer.RectangleDrawer();
        BOARD_CLICKABLE();
    }

    @FXML
    void ON_CLICK_T(ActionEvent event) { // Event handler for the triangle button
        drawer = new Drawer.TriangleDrawer();
        BOARD_CLICKABLE();
    }

    @FXML
    void ON_CLICK_INFO(ActionEvent event) { // Event handler for the info button
    // Create a new alert
    Alert alert = new Alert(AlertType.INFORMATION);
    // Set the title of the alert
    alert.setTitle("MINI PAINT");
    // Set the header of the alert
    alert.setHeaderText("Autor: Miłosz Białek");
    // Set the content of the alert
    alert.setContentText("Program służy do rysowania za pomocą zmieniających kolory kształtów, aby dodać jakiś kształt wystarczy kliknąć wybrany guzik i kliknąć 2 lub 3 razy na ekran. \n OBRÓT: CTRL + MOUSE \n ZMIANA KOLORU: PPM \n ZMIANA ROZMIARU: SCROLL \n ZAPIS: FILE -> SAVE \n WCZYTAJ: FILE -> OPEN \n USUŃ: CLEAR \n COFNIJ: UNDO");
    // Show the alert
    alert.showAndWait();
    }

    @FXML
    void CLEAR(ActionEvent event) { // Event handler for the clear button
        BOARD.getChildren().clear();
    }

    @FXML
    void UNDO(ActionEvent event) { // Event handler for the undo button
        if (BOARD.getChildren().size() > 0) {
            BOARD.getChildren().remove(BOARD.getChildren().size() - 1);
        }
    }

    @FXML
    public void initialize() { // Initialization method for setting up the drawing board
        // Create a Rectangle that has the same size as the BOARD
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(BOARD.widthProperty());
        clip.heightProperty().bind(BOARD.heightProperty());

        // Set the clip of the BOARD
        BOARD.setClip(clip);
    }

    @FXML
    void SAVE(ActionEvent event) {
        // Create a new FileChooser
        FileChooser fileChooser = new FileChooser();
        // Set the title of the FileChooser dialog
        fileChooser.setTitle("Save Board State");
        // Add an extension filter to the FileChooser
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Custom Files", "*.jakDuzoZlosciSprawiloMiToZeShapesNieSaSeriazable"));
        // Show the save dialog and get the selected file
        File file = fileChooser.showSaveDialog(BOARD.getScene().getWindow());

        // If a file was selected
        if (file != null) {
            try {
                // Get the path of the selected file
                String filePath = file.getPath();
                // If the file does not end with the custom extension, append it
                if (!filePath.toLowerCase().endsWith(".jakDuzoZlosciSprawiloMiToZeShapesNieSaSeriazable")) {
                    file = new File(filePath + ".jakDuzoZlosciSprawiloMiToZeShapesNieSaSeriazable");
                }

                // Create a PrintWriter for the file
                PrintWriter writer = new PrintWriter(file, "UTF-8");

                // Iterate over each node in the board
                for (Node node : BOARD.getChildren()) {
                    // If the node is a rectangle
                    if (node instanceof MyRectangle) {
                        MyRectangle rect = (MyRectangle) node;
                        // Write the rectangle's properties to the file
                        writer.println("MyRectangle");
                        writer.println(rect.getX1());
                        writer.println(rect.getY1());
                        writer.println(rect.getWidth());
                        writer.println(rect.getHeight());
                        writer.println(rect.getFill());
                        writer.println(rect.getCurrentRotation());
                    
                    // If the node is a circle
                    } else if (node instanceof MyCircle) {
                        MyCircle circle = (MyCircle) node;
                        // Write the circle's properties to the file
                        writer.println("MyCircle");
                        writer.println(circle.getCenterX());
                        writer.println(circle.getCenterY());
                        writer.println(circle.getRadius());
                        writer.println(circle.getFill());
                        
                    // If the node is a triangle
                    } else if (node instanceof MyTriangle) {
                        MyTriangle triangle = (MyTriangle) node;
                        // Write the triangle's properties to the file
                        writer.println("MyTriangle");
                        writer.println(triangle.getX1());
                        writer.println(triangle.getY1());
                        writer.println(triangle.getX2());
                        writer.println(triangle.getY2());
                        writer.println(triangle.getX3());
                        writer.println(triangle.getY3());
                        writer.println(triangle.getRotate());
                        writer.println(triangle.getFill());
                        
                    }
                }

                // Close the writer
                writer.close();
            } catch (IOException i) {
                // Print the stack trace for any IOExceptions
                i.printStackTrace();
            }
        }
    }

    @FXML
    void OPEN(ActionEvent event) {
        // Create a new FileChooser
        FileChooser fileChooser = new FileChooser();
        // Set the title of the FileChooser dialog
        fileChooser.setTitle("Open Board State");
        // Add an extension filter to the FileChooser
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Custom Files", "*.jakDuzoZlosciSprawiloMiToZeShapesNieSaSeriazable"));
        // Show the open dialog and get the selected file
        File file = fileChooser.showOpenDialog(BOARD.getScene().getWindow());

        // If a file was selected
        if (file != null) {
            try {
                // Create a BufferedReader for the file
                BufferedReader reader = new BufferedReader(new FileReader(file));

                // Clear the board
                BOARD.getChildren().clear();

                // Read each line of the file
                String line;
                while ((line = reader.readLine()) != null) {
                    // If the line indicates a rectangle
                    if (line.equals("MyRectangle")) {
                        // Read the rectangle's properties from the file
                        double x = Double.parseDouble(reader.readLine());
                        double y = Double.parseDouble(reader.readLine());
                        double width = Double.parseDouble(reader.readLine());
                        double height = Double.parseDouble(reader.readLine());
                        String color = reader.readLine(); // Read color before rotate
                        double rotate = Double.parseDouble(reader.readLine());

                        // Create a new rectangle with the read properties
                        MyRectangle rect = new MyRectangle(x, y, width, height);
                        rect.setRotate(rotate);
                        rect.setFill(Paint.valueOf(color)); // Convert color string to Paint object
                        // Add the rectangle to the board
                        BOARD.getChildren().add(rect);
                    // If the line indicates a circle
                    } else if (line.equals("MyCircle")) {
                        // Read the circle's properties from the file
                        double centerX = Double.parseDouble(reader.readLine());
                        double centerY = Double.parseDouble(reader.readLine());
                        double radius = Double.parseDouble(reader.readLine());
                        String color = reader.readLine();

                        // Create a new circle with the read properties
                        MyCircle circle = new MyCircle(centerX, centerY, radius);
                        circle.setFill(Paint.valueOf(color)); // Convert color string to Paint object
                        // Add the circle to the board
                        BOARD.getChildren().add(circle);
                    // If the line indicates a triangle
                    } else if (line.equals("MyTriangle")) {
                        // Read the triangle's properties from the file
                        double x1 = Double.parseDouble(reader.readLine());
                        double y1 = Double.parseDouble(reader.readLine());
                        double x2 = Double.parseDouble(reader.readLine());
                        double y2 = Double.parseDouble(reader.readLine());
                        double x3 = Double.parseDouble(reader.readLine());
                        double y3 = Double.parseDouble(reader.readLine());
                        double rotate = Double.parseDouble(reader.readLine());
                        String color = reader.readLine(); // Read color before rotate
                        

                        // Create a new triangle with the read properties
                        MyTriangle triangle = new MyTriangle(x1, y1, x2, y2, x3, y3);
                        triangle.setRotate(rotate);
                        triangle.setFill(Paint.valueOf(color)); // Convert color string to Paint object
                        // Add the triangle to the board
                        BOARD.getChildren().add(triangle);
                    }
                }

                // Close the reader
                reader.close();
            } catch (IOException i) {
                // Print the stack trace for any IOExceptions
                i.printStackTrace();
            }
        }
    }

    // The object that will be used to draw shapes
    private InterfaceForDrawers drawer;

    // The event handler for mouse events
    private EventHandler<MouseEvent> eventHandler;

    // Method to make the board respond to mouse clicks
    public void BOARD_CLICKABLE() {
        try {
            // If an event handler already exists, remove it
            if (eventHandler != null) {
                BOARD.removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            }

            // Create a new event handler
            eventHandler = e -> {
                // Create a new point at the location of the mouse click
                Utils.Point point = new Utils.Point((double)e.getX(), (double)e.getY());
                // Add the point to the drawer
                drawer.addPoint(point);
                // If the drawer has enough points, draw the shape
                if (drawer.isReady()) {
                    drawer.Build(BOARD);
                }
            };

            // Add the event handler to the board
            BOARD.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        } catch (Exception e) {
            // Print the stack trace for any exceptions
            e.printStackTrace();
        }
    }

}
