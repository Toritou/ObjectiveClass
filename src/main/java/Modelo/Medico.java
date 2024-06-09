package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Paciente {
    private String especialidad;
    private List<Paciente> pacientes;

    // Constructor completo para inicializar Medico como Paciente
    public Medico(String rut, String nombre, String especialidad, String hospital) {
        super(rut, nombre, 0, "", "", (int) 0.0, "", "", "", "", "", "", ""); // Valores por defecto para Paciente
        this.especialidad = especialidad;
        this.pacientes = new ArrayList<>();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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

    @Override
    public String toString() {
        return super.toString() + ", Especialidad: " + especialidad + ", Pacientes: " + pacientes.size();
    }
}