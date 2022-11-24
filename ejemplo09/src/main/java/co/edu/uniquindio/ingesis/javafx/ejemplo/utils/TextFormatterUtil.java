package co.edu.uniquindio.ingesis.javafx.ejemplo.utils;

import javafx.scene.control.TextFormatter.Change;

public class TextFormatterUtil {

    public static Change integerFormat(Change change){
        if (change.getText().matches("[0-9]*")) {
            return change;
        }
        return null;
    }
}
