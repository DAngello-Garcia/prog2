package co.edu.uniquindio.ingesis.javafx.ejemplo;

import co.edu.uniquindio.ingesis.javafx.ejemplo.utils.TextFormatterUtil;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateStringConverter;

import javax.swing.JOptionPane;
import java.time.LocalDate;

public class PersonaController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker txtFecha;

    @FXML
    public void initialize() {
        txtId.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        txtFecha.setValue(LocalDate.now());
    }

    @FXML
    protected void onAceptarButtonClick() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String id = txtId.getText();
        String email = txtEmail.getText();
        LocalDate fecha = txtFecha.getValue();
        JOptionPane.showMessageDialog(null,"Nombre: "+nombre+"\nApellido: "+apellido+"\nNúmero de identificación: "+id+"\nCorreo electrónico: "+email+"\nFecha de nacimiento: "+fecha.toString());
    }
}