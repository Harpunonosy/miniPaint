package mini.paint.shapes;

// Importing necessary classes and interfaces
import javafx.scene.shape.Circle;
import mini.paint.Interfaces.InterfaceForShapes; 
import static mini.paint.Shapes.activeShapeEvent; 
import static mini.paint.Shapes.addColorPickerDialog;

// MyCircle class that extends Circle and implements InterfaceForShapes
public class MyCircle extends Circle implements InterfaceForShapes {
    // Variable to check if the shape is active
    private boolean active = false;

    // Constructor for MyCircle
    public MyCircle(double centerX, double centerY, double radius) {
        // Calling the parent constructor
        super(centerX, centerY, radius);

        // Adding events to the shape
        activeShapeEvent(this);  // Setting the active shape event
        addColorPickerDialog(this); // Adding the color picker dialog

        // Setting the on drag event
        setOnMouseDragged(event -> {
            // If the shape is active, update the center coordinates
            if (active) {
                setCenterX(event.getX());
                setCenterY(event.getY());
            }
        });

        // Setting the on scroll event
        setOnScroll(event -> {
            // If the shape is active, update the radius
            if (active) {
                double scaleFactor = 1 + event.getDeltaY() * 0.001;
                setRadius(getRadius() * scaleFactor);
            }
        });
    }

    // Overriding the setActive method from InterfaceForShapes
    @Override
    public void setActive(boolean active) {
        // Setting the active state
        this.active = active;
        // If the shape is not active, clear the stroke
        if (!active) {
            setStroke(null);
            getStrokeDashArray().clear();
        }
    }
}