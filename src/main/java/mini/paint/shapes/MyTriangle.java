package mini.paint.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import mini.paint.Interfaces.InterfaceForShapes;
import static mini.paint.Shapes.activeShapeEvent;
import static mini.paint.Shapes.addColorPickerDialog;

// Class MyTriangle extends Polygon and implements InterfaceForShapes
public class MyTriangle extends Polygon implements InterfaceForShapes {
    // Variables to store initial positions and translations
    private double initialX, initialY;
    private double initialTranslateX, initialTranslateY;
    // Variables to store centroid coordinates
    private double centroidX, centroidY;
    // Variable to store the active state of the triangle
    private boolean active = false;

    // Constructor for MyTriangle
    public MyTriangle(double... points) {
        super(points); // Calls the constructor of the superclass Polygon
        activeShapeEvent(this); // Adds this triangle to the active shapes
        addColorPickerDialog(this); // Adds a color picker dialog to this triangle
        updateCentroid(); // Updates the centroid of the triangle

        // Event handler for mouse press
        setOnMousePressed(event -> {
            if (active) { // If the triangle is active
                // Store the initial mouse position and translation
                initialX = event.getSceneX();
                initialY = event.getSceneY();
                initialTranslateX = getTranslateX();
                initialTranslateY = getTranslateY();
            }
        });

        // Event handler for mouse drag
        setOnMouseDragged(event -> {
            if (active) { // If the triangle is active
                if (event.isControlDown()) { // If the control key is pressed
                    // Calculate the angle for rotation
                    double angle = Math.atan2(event.getSceneY() - centroidY, event.getSceneX() - centroidX);
                    angle = Math.toDegrees(angle);
                    setRotate(angle); // Rotate the triangle
                } else {
                    // Calculate the change in mouse position
                    double deltaX = event.getSceneX() - initialX;
                    double deltaY = event.getSceneY() - initialY;
                    // Translate the triangle
                    setTranslateX(initialTranslateX + deltaX);
                    setTranslateY(initialTranslateY + deltaY);
                    updateCentroid(); // Update the centroid
                }
            }
        });

        // Event handler for scroll
        setOnScroll(event -> {
            if (active) { // If the triangle is active
                // Calculate the scale factor
                double scaleFactor = 1 + event.getDeltaY() * 0.001;
                double oldCentroidX = centroidX;
                double oldCentroidY = centroidY;
                // Scale the triangle
                for (int i = 0; i < getPoints().size(); i += 2) {
                    getPoints().set(i, getPoints().get(i) - oldCentroidX);
                    getPoints().set(i + 1, getPoints().get(i + 1) - oldCentroidY);
                }
                for (int i = 0; i < getPoints().size(); i += 2) {
                    getPoints().set(i, getPoints().get(i) * scaleFactor);
                    getPoints().set(i + 1, getPoints().get(i + 1) * scaleFactor);
                }
                for (int i = 0; i < getPoints().size(); i += 2) {
                    getPoints().set(i, getPoints().get(i) + oldCentroidX);
                    getPoints().set(i + 1, getPoints().get(i + 1) + oldCentroidY);
                }
                updateCentroid(); // Update the centroid
            }
        });
    }

    // Method to set the active state of the triangle
    @Override
    public void setActive(boolean active) {
        this.active = active;
        if (active) { // If the triangle is active
            // Set the stroke color and width
            setStroke(Color.VIOLET);
            setStrokeWidth(5);
            getStrokeDashArray().setAll(10.0, 10.0); // Set the stroke dash array
        } else {
            setStroke(null); // Remove the stroke
            getStrokeDashArray().clear(); // Clear the stroke dash array
        }
    }

    // Methods to get the x and y coordinates of the triangle's points
    public double getX1() {
        return getPoints().get(0) + getTranslateX();
    }

    public double getY1() {
        return getPoints().get(1) + getTranslateY();
    }

    public double getX2() {
        return getPoints().get(2) + getTranslateX();
    }

    public double getY2() {
        return getPoints().get(3) + getTranslateY();
    }

    public double getX3() {
        return getPoints().get(4) + getTranslateX();
    }

    public double getY3() {
        return getPoints().get(5) + getTranslateY();
    }

    // Method to update the centroid of the triangle
    private void updateCentroid() {
        centroidX = (getPoints().get(0) + getPoints().get(2) + getPoints().get(4)) / 3;
        centroidY = (getPoints().get(1) + getPoints().get(3) + getPoints().get(5)) / 3;
    }
}