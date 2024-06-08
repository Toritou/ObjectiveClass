package Controlador;

import Modelo.Paciente;

import java.util.ArrayList;
import java.util.List;

public class GestionPaciente {
    private List<Paciente> pacientes;

    public GestionPaciente() {
        this.pacientes = new ArrayList<>();
    }

    // Agregar paciente
    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    // Modificar paciente
    public void modificarPaciente(String rut, Paciente pacienteModificado) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getRut().equals(rut)) {
                pacientes.set(i, pacienteModificado);
                return;
            }
        }
    }

    // Eliminar paciente
    public void eliminarPaciente(String rut) {
        pacientes.removeIf(paciente -> paciente.getRut().equals(rut));
    }

    // Listar pacientes
    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes);
    }

    // Buscar paciente por RUT
    public Paciente buscarPacientePorRut(String rut) {
        for (Paciente paciente : pacientes) {
            if (paciente.getRut().equals(rut)) {
                return paciente;
            }
        }
        return null;
    }
}
