package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Usuario {
    private String nombre;
    private String rut;
    private String especialidad;
    private List<Paciente> pacientes;

    public Medico(String nombre, String rut, String especialidad) {
        super("123456789", "12345");
        this.nombre = nombre;
        this.rut = rut;
        this.especialidad = especialidad;
        this.pacientes = new ArrayList<>();
    }


    public String getRut() {
        return rut;
    }


    public List<Paciente> getPacientes() {
        return pacientes;
    }


    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public Paciente obtenerPacientePorRut(String rut) {
        for (Paciente paciente : pacientes) {
            if (paciente.getRut().equals(rut)) {
                return paciente;
            }
        }
        return null;
    }
    public void modificarFichaPaciente(String rut, String nuevaInformacion) {
        for (Paciente paciente : pacientes) {
            if (paciente.getRut().equals(rut)) {
                paciente.setFichaMedica(nuevaInformacion);
                return;
            }
        }
        System.out.println("Paciente no encontrado.");
    }
}
