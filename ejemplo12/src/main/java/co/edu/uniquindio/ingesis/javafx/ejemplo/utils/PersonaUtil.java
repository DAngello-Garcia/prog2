package co.edu.uniquindio.ingesis.javafx.ejemplo.utils;

import co.edu.uniquindio.ingesis.javafx.ejemplo.model.Persona;

import java.time.LocalDate;
import java.util.function.Predicate;

public class PersonaUtil {
    public static Predicate<Persona> buscarPorNumeroIdentificacion(String numeroIdentificacion){
        return persona -> persona.getNumeriIdentificacion().equals(numeroIdentificacion);
    }

    public static Predicate<Persona> buscarPorNombre(String nombre){
        return persona -> persona.getNombre().contains(nombre);
    }

    public static Predicate<Persona> buscarPorApellido(String apellido){
        return persona -> persona.getApellido().contains(apellido);
    }

    public static Predicate<Persona> buscarPorClase(String clase){
        return persona -> persona.getClase().equals(clase);
    }

    public static Predicate<Persona> buscarPorFecha(LocalDate fecha){
        return persona -> persona.getFechaNacimiento().equals(fecha);
    }

    public static Predicate<Persona> buscarPorFechaExacta(LocalDate fecha){
        Predicate<Persona> predicado = persona -> true;
        predicado = predicado.and(buscarPorFecha(fecha));
        return predicado;
    }

    public static Predicate<Persona> buscarPorTodo(String numeroIdentificacion,String nombre,String apellido, LocalDate fecha, String clase) {
        Predicate<Persona> predicado = persona -> true;
        if( numeroIdentificacion != null && !numeroIdentificacion.isEmpty() ){
            predicado = predicado.and(buscarPorNumeroIdentificacion(numeroIdentificacion));
        }
        if( nombre != null && !nombre.isEmpty() ){
            predicado = predicado.and(buscarPorNombre(nombre));
        }
        if( apellido != null && !apellido.isEmpty() ){
            predicado = predicado.and(buscarPorApellido(apellido));
        }
        if( fecha != null){
            predicado = predicado.and(buscarPorFecha(fecha));
        }
        if( clase != null){
            predicado = predicado.and(buscarPorClase(clase));
        }
        return predicado;
    }
}
