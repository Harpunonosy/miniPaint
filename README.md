# Mini Paint Application

This is a JavaFX-based mini paint application that allows users to draw shapes like circles, rectangles, and triangles on a canvas. The application provides functionalities to save and open files, undo actions, and clear the canvas.

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher

## How to Build

1. Clone the repository:
    ```sh
    git clone <repository-url>
    cd <repository-directory>
    ```

2. Compile the project using Maven:
    ```sh
    mvn clean compile
    ```

3. Package the project:
    ```sh
    mvn package
    ```

## How to Run

1. Run the application using Maven:
    ```sh
    mvn javafx:run
    ```

## Usage

- **Drawing Shapes**: Click on the respective buttons to select the shape (circle, rectangle, triangle) and click on the canvas to draw.
- **File Operations**: Use the file menu to open and save files.
- **Undo and Clear**: Use the undo button to revert the last action and the clear button to clear the canvas.

## Main Classes and Files

- `App.java`: The main entry point of the application.
- `Controller.java`: The controller class that handles UI interactions.
- `Drawer.java`: The class responsible for drawing shapes.
- `main.fxml`: The FXML file defining the UI layout.

## License

This project is licensed under the MIT License.
