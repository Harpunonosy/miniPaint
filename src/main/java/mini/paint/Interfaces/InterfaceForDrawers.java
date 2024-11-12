package mini.paint.Interfaces; // Package declaration
import javafx.scene.layout.AnchorPane; // Importing AnchorPane from javafx.scene.layout package
import mini.paint.Utils; // Importing Utils from mini.paint package

// InterfaceForDrawers interface declaration
public interface InterfaceForDrawers {
    // Method to add a point to the shape
    void addPoint(Utils.Point point);
    // Method to build the shape and add it to the BOARD
    void Build(AnchorPane BOARD);
    // Method to check if the shape is ready to be drawn
    boolean isReady();
}