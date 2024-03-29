package co.edu.uniquindio.ingesis.javafx.ejemplo.model;

import co.edu.uniquindio.ingesis.javafx.ejemplo.exceptions.PersonaExisteException;
import co.edu.uniquindio.ingesis.javafx.ejemplo.exceptions.PersonaNoExisteException;
import co.edu.uniquindio.ingesis.javafx.ejemplo.exceptions.ValorRequeridoException;
import co.edu.uniquindio.ingesis.javafx.ejemplo.utils.PersonaUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Bus {
    private final List<Persona> personas;

    public Bus() {
        personas = new ArrayList<>();
    }

    public void adicionarPersona(Persona persona) throws PersonaExisteException {
        if( buscarPersonaByNumeroIdentificacion( persona.getNumeriIdentificacion() ).isPresent() ){
            throw new PersonaExisteException();
        }
        personas.add(persona);
    }

    public void removerPersona(String numeroIdentificacion) throws PersonaNoExisteException, ValorRequeridoException {
        if(Objects.requireNonNull(numeroIdentificacion,"El número de identificación es requerido").isEmpty()){
            throw new ValorRequeridoException("número de identificación");
        }
        Optional<Persona> persona = buscarPersonaByNumeroIdentificacion(numeroIdentificacion);
        if( persona.isEmpty() ){
            throw new PersonaNoExisteException();
        }
        personas.remove(persona.get());
    }

    public Optional<Persona> buscarPersonaByNumeroIdentificacion(String numeriIdentificacion) {
        return personas.stream()
                .filter(PersonaUtil.buscarPorNumeroIdentificacion(numeriIdentificacion))
                .findFirst();
    }

    public List<Persona> buscar(String numeroIdentificacion, String nombre, String apellido, LocalDate fecha, String clase){
        return personas.stream()
                .filter(PersonaUtil.buscarPorTodo(numeroIdentificacion, nombre, apellido, fecha, clase))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Persona> buscarFecha(LocalDate fecha){
        return personas.stream()
                .filter(PersonaUtil.buscarPorFechaExacta(fecha))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Persona> buscarClase(String clase){
        return personas.stream()
                .filter(PersonaUtil.buscarPorClase(clase))
                .collect(Collectors.toUnmodifiableList());
    }
}
