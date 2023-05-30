package co.edu.uniquindio.ingesis.javafx.ejemplo.controllers;

import co.edu.uniquindio.ingesis.javafx.ejemplo.model.Persona;
import co.edu.uniquindio.ingesis.javafx.ejemplo.utils.TextFormatterUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.List;

import static co.edu.uniquindio.ingesis.javafx.ejemplo.controllers.AppController.INSTANCE;

public class PersonaController {
    @FXML
    private TableView<Persona> tblPersonas;
    @FXML
    private TableColumn<Persona, String> colNumeroIdentificacion;
    @FXML
    private TableColumn<Persona, String> colNombre;
    @FXML
    private TableColumn<Persona, String> colApellido;
    @FXML
    private TableColumn<Persona, LocalDate> fechaNacimiento;
    @FXML
    private TableColumn<Persona, String> clase;
    @FXML
    private TextField tfNumeroIdentificacion;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellido;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private ComboBox comboClase;

    /*
    * Ejercicio: El ejemplo muestra el registro de pasajeros que abordaran un bus de transporte intermunicipal. A cada pasajero que abordará el bus se le solicita número de identificación, nombre y apellido.

Modifique el proyecto para que las personas deban registrar:

Su fecha de nacimiento con un calendario.
La clase en la que viajaran (ejecutiva y turista) con una lista de selección.
Adicione a la tabla las columnas para ver los nuevos campos. Realice las modificaciones necesarias para que el sistema permita realizar busquedas por la fecha de nacimiento y la clase en la que se viaja.*/


    @FXML
    public void initialize() {
        ObservableList<String> clases = FXCollections.observableArrayList("Ejecutiva", "Turista");
        llenarTabla(INSTANCE.getBus().buscar(null, null, null, null, null));
        colNumeroIdentificacion.setCellValueFactory(new PropertyValueFactory<>("numeriIdentificacion"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        fechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        clase.setCellValueFactory(new PropertyValueFactory<>("clase"));
        comboClase.setItems(clases);
        dateFecha.setValue(null);
        tblPersonas.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> llenarCampos(newValue));
        tfNumeroIdentificacion.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        tfNombre.setTextFormatter(new TextFormatter<>(TextFormatterUtil::upperCaseFormat));
        tfApellido.setTextFormatter(new TextFormatter<>(TextFormatterUtil::upperCaseFormat));
    }

    public void onGuardarClick() {
        try {
            Persona persona = Persona.of(tfNumeroIdentificacion.getText(), tfNombre.getText(), tfApellido.getText(), dateFecha.getValue(), comboClase.getValue().toString());
            INSTANCE.getBus().adicionarPersona(persona);
            llenarTabla(INSTANCE.getBus().buscar(null, null, null, null, null));
            limpiarCampos();
            mostrarInformacion("La persona fue aceptada en el bus");
        } catch (Exception e) {
            mostrarMensaje(e.getMessage());
        }
    }

    public void onCancelarClick() {
        limpiarCampos();
        tblPersonas.getSelectionModel().clearSelection();
    }

    public void onRemoverClick() {
        try {
            INSTANCE.getBus().removerPersona(tfNumeroIdentificacion.getText());
            llenarTabla(INSTANCE.getBus().buscar(null, null, null, null, null));
            limpiarCampos();
            mostrarInformacion("La persona fue retirada del bus");
        } catch (Exception e) {
            mostrarMensaje(e.getMessage());
        }
    }

    public void onBuscarClick() {
        if(dateFecha.getValue() != null) {
            llenarTabla(INSTANCE.getBus().buscarFecha(dateFecha.getValue()));
        }
        if(comboClase.getValue() != null) {
            llenarTabla(INSTANCE.getBus().buscarClase(comboClase.getValue().toString()));
        }

    }

    private void llenarCampos(Persona persona) {
        if (persona != null) {
            tfNumeroIdentificacion.setText(persona.getNumeriIdentificacion());
            tfNombre.setText(persona.getNombre());
            tfApellido.setText(persona.getApellido());
            dateFecha.setValue(persona.getFechaNacimiento());
            comboClase.setValue(persona.getClase());
        }
    }

    private void limpiarCampos() {
        tfNumeroIdentificacion.setText("");
        tfNombre.setText("");
        tfApellido.setText("");
        dateFecha.setValue(null);
        comboClase.setValue(null);
    }

    private void llenarTabla(List<Persona> personas) {
        tblPersonas.setItems(FXCollections.observableArrayList(personas));
        tblPersonas.refresh();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}