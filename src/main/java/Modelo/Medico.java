package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medico extends Usuario {
    private String nombre;
    private String rut;
    private String especialidad;
    private List<Paciente> pacientes;

    public Medico(String nombre, String rut, String especialidad) {
        super(1234, "1234", "Medico");
        this.nombre = nombre;
        this.rut = rut;
        this.especialidad = especialidad;
        this.pacientes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
}
