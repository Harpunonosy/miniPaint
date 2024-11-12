package mini.paint;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import mini.paint.Interfaces.InterfaceForDrawers;
import mini.paint.Interfaces.InterfaceForShapes;
import mini.paint.shapes.MyCircle;
import mini.paint.shapes.MyRectangle;
import mini.paint.shapes.MyTriangle;

public class Drawer {
    // List of points used to check if the shape is ready to be drawn
    ArrayList<Utils.Point> points = new ArrayList<Utils.Point>();

    // Method to add a point to the list
    public void addPoint(Utils.Point point) {
        points.add(point);
    }

    // Inner class for drawing circles
    public static class CircleDrawer extends Drawer implements InterfaceForDrawers{

        // Method to check if the circle is ready to be drawn (has enough points)
        public boolean isReady(){
            return points.size() == 2;
        }

        // Method to build and add the circle to the board
        public void Build(AnchorPane BOARD){
            double radius = points.get(0).distance(points.get(1));
            InterfaceForShapes circle = new MyCircle(points.get(0).getx(), points.get(0).gety(), radius);
            BOARD.getChildren().add((Node) circle);
        }
    }

    // Inner class for drawing rectangles
    public static class RectangleDrawer extends Drawer implements InterfaceForDrawers {
    
        // Method to check if the rectangle is ready to be drawn (has enough points)
        public boolean isReady(){
            return points.size() == 2;
        }

        // Method to build and add the rectangle to the board
        public void Build(AnchorPane BOARD){
            double minX = Math.min(points.get(0).getx(), points.get(1).getx());
            double minY = Math.min(points.get(0).gety(), points.get(1).gety());
            double width = Math.abs(points.get(0).getx() - points.get(1).getx());
            double height = Math.abs(points.get(0).gety() - points.get(1).gety());
            InterfaceForShapes rectangle = new MyRectangle(minX, minY, width, height);
            BOARD.getChildren().add((Node) rectangle);
        }
    }

    // Inner class for drawing triangles
    public static class TriangleDrawer extends Drawer implements InterfaceForDrawers{
    
        // Method to check if the triangle is ready to be drawn (has enough points)
        public boolean isReady(){
            return points.size() == 3;
        }

        // Method to build and add the triangle to the board
        public void Build(AnchorPane BOARD){
            InterfaceForShapes triangle = new MyTriangle(points.get(0).getx(), points.get(0).gety(), points.get(1).getx(), points.get(1).gety(), points.get(2).getx(), points.get(2).gety());
            BOARD.getChildren().add((Node) triangle);
        }
    }    
}