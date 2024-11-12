package mini.paint.shapes;

// Importing necessary classes and interfaces
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import mini.paint.Interfaces.InterfaceForShapes;
import static mini.paint.Shapes.activeShapeEvent;
import static mini.paint.Shapes.addColorPickerDialog;

// MyRectangle class that extends Rectangle and implements InterfaceForShapes
public class MyRectangle extends Rectangle implements InterfaceForShapes {
    // Variables for rotation and initial positions
    private Rotate rotation = new Rotate();
    private double initialSceneX, initialSceneY;
    private double initialTranslateX, initialTranslateY;
    private boolean active = false;

    // Constructor for MyRectangle
    public MyRectangle(double x, double y, double width, double height) {
        // Calling the parent constructor
        super(x, y, width, height);
        // Adding rotation to the transforms of the rectangle
        getTransforms().add(rotation);
        // Adding events to the shape
        activeShapeEvent(this); // Setting the active shape event
        addColorPickerDialog(this); // Adding the color picker dialog

        // Setting the on press event
        setOnMousePressed(event -> {
            // If the shape is active, save the initial positions
            if (active) {
                initialSceneX = event.getSceneX(); // Saving the initial scene X position
                initialSceneY = event.getSceneY(); // Saving the initial scene Y position
                initialTranslateX = getTranslateX(); // Saving the initial translate X position
                initialTranslateY = getTranslateY(); // Saving the initial translate Y position
            }
        });

        // Setting the on drag event
        setOnMouseDragged(event -> {
            // If the shape is active, update the position or rotation
            if (active) {
                if (event.isControlDown()) {
                    // If control is down, update the rotation
                    rotation.setPivotX(getX() + getWidth() / 2); // Setting the pivot X
                    rotation.setPivotY(getY() + getHeight() / 2); // Setting the pivot Y
                    double angle = Math.atan2(event.getSceneY() - rotation.getPivotY(), event.getSceneX() - rotation.getPivotX()); // Calculating the angle
                    rotation.setAngle(Math.toDegrees(angle)); // Setting the angle
                } else {
                    // Otherwise, update the position
                    double deltaX = event.getSceneX() - initialSceneX;
                    double deltaY = event.getSceneY() - initialSceneY;
                    setTranslateX(initialTranslateX + deltaX); // Setting the translate X
                    setTranslateY(initialTranslateY + deltaY); // Setting the translate Y
                }
            }
        });

        // Setting the on scroll event
        setOnScroll(event -> {
            // If the shape is active, update the size
            if (active) {
                double scaleFactor = 1 + event.getDeltaY() * 0.001;
                double oldWidth = getWidth();
                double oldHeight = getHeight();
                double newWidth = oldWidth * scaleFactor;
                double newHeight = oldHeight * scaleFactor;
                setWidth(newWidth);
                setHeight(newHeight);
                setX(getX() + (oldWidth - newWidth) / 2);
                setY(getY() + (oldHeight - newHeight) / 2);
            }
        });
    }

    // Overriding the setActive method from InterfaceForShapes
    @Override
    public void setActive(boolean active) {
        // Setting the active state
        this.active = active;
        if (active) {
            // If the shape is active, update the rotation pivot
            double currentRotation = rotation.getAngle();
            rotation.setAngle(0);
            double pivotX = getX() + getWidth() / 2;
            double pivotY = getY() + getHeight() / 2;
            rotation.setPivotX(pivotX);
            rotation.setPivotY(pivotY);
            rotation.setAngle(currentRotation);
        } else {
            // If the shape is not active, clear the stroke
            setStroke(null);
            getStrokeDashArray().clear();
        }
    }

    // Method to get the X position including translation
    public double getX1() {
        return getX() + getTranslateX();
    }

    // Method to get the Y position including translation
    public double getY1() {
        return getY() + getTranslateY();
    }

    // Method to get the current rotation angle
    public double getCurrentRotation() {
        return this.rotation.getAngle();
    }
}