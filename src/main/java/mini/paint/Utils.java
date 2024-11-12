package mini.paint;

public class Utils { //not sure if comments are needed here but okay
    public static class Point {
        private double x;
        private double y;

        public Point(double x, double y){ //constructor for 2D point
            this.x = x;
            this.y = y;
        }

        public double getx(){ //getter for x
            return x;
        }

        public double gety(){ //getter for y
            return y;
        }

        public double distance(Point point){ //distance between two points
            return Math.sqrt(Math.pow(x - point.getx(), 2) + Math.pow(y - point.gety(), 2));
        }
    }

}
