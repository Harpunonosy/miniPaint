package mini.paint;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import mini.paint.Interfaces.InterfaceForShapes;

public class Shapes {
    private static InterfaceForShapes activeShape = null;

    public static void activeShapeEvent(Shape shape) {
        shape.setOnMouseClicked(event -> {
            // If there is an active shape, deactivate it
            if (activeShape != null) {
                activeShape.setActive(false);
            }
            // Set the clicked shape as the active shape
            activeShape = (InterfaceForShapes) shape;
            activeShape.setActive(true);
            // Set the stroke color and style of the active shape
            shape.setStroke(Color.DODGERBLUE);
            shape.setStrokeWidth(5);
            shape.getStrokeDashArray().setAll(10.0, 10.0);

            // Request focus on the active shape
            shape.requestFocus();
        });
    }

    public static void addColorPickerDialog(Shape shape) {
        ColorPicker colorPicker = new ColorPicker();

        // Create a dialog
        Dialog<Color> dialog = new Dialog<>();
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(colorPicker);
        dialog.setDialogPane(dialogPane);

        // Add a "Close" button to the dialog
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        // Set the fill color when a color is selected in the color picker
        colorPicker.setOnAction(event -> {
            shape.setFill(colorPicker.getValue());
            dialog.close();
        });

        // Show the dialog when the shape is right-clicked
        shape.setOnContextMenuRequested(event -> {
            dialog.showAndWait();
            event.consume();
        });
    }
}