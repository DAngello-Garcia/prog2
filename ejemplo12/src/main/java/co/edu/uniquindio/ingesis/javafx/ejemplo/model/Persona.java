package co.edu.uniquindio.ingesis.javafx.ejemplo.model;

import co.edu.uniquindio.ingesis.javafx.ejemplo.exceptions.ValorRequeridoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Persona {
    private final String numeriIdentificacion;
    private final String nombre;
    private final String apellido;
    private final LocalDate fechaNacimiento;

    private final String clase;

    private Persona(String numeriIdentificacion, String nombre, String apellido, LocalDate fechaNacimiento, String clase) {
        this.numeriIdentificacion = numeriIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.clase = clase;
    }

    public static Persona of(String numeroIdentificacion, String nombre, String apellido, LocalDate fechaNacimiento, String clase) throws ValorRequeridoException {
        if(Objects.requireNonNull(numeroIdentificacion,"El número de identificación es requerido").isEmpty()){
            throw new ValorRequeridoException("número de identificación");
        }
        if(Objects.requireNonNull(nombre,"El nombre es requerido").isEmpty()){
            throw new ValorRequeridoException("nombre");
        }
        if(Objects.requireNonNull(apellido,"El apellido es requerido").isEmpty()){
            throw new ValorRequeridoException("apellido");
        }
        return new Persona(numeroIdentificacion, nombre, apellido, fechaNacimiento, clase);
    }

    public String getNumeriIdentificacion() {
        return numeriIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }


    public String getClase() {
        return clase;
    }
}
