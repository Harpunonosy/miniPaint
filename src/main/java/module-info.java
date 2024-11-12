module mini.paint {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens mini.paint to javafx.fxml;
    exports mini.paint;
}
