module co.edu.uniquindio.ingesis.javafx.ejemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens co.edu.uniquindio.ingesis.javafx.ejemplo to javafx.fxml;
    exports co.edu.uniquindio.ingesis.javafx.ejemplo;
}