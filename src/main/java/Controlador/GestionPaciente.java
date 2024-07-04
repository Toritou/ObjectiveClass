package Controlador;

import Modelo.Paciente;
import csv.CSVManager;
import java.util.*;

public class GestionPaciente {
    private final Map<String, Paciente> pacientes;

    public GestionPaciente() {
        this.pacientes = new HashMap<>();
        cargarPacientesDesdeCSV();
    }

    private void cargarPacientesDesdeCSV() {
        List<Paciente> pacientesCargados = CSVManager.leerPacientes();
        for (Paciente paciente : pacientesCargados) {
            pacientes.put(paciente.getRut(), paciente);
        }
    }

    public Paciente iniciarSesion(String rut, String contrasena) {
        try {
            Paciente paciente = pacientes.get(rut);
            if (paciente == null) {
                throw new IllegalArgumentException("Paciente no encontrado con el RUT: " + rut);
            }
            if (!paciente.verificarContrasena(contrasena)) {
                throw new IllegalArgumentException("La contraseña es incorrecta para el RUT: " + rut);
            }
            return paciente;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    private void guardarPacientesEnCSV() {
        CSVManager.escribirPacientes(new ArrayList<>(pacientes.values()));
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.put(paciente.getRut(), paciente);
        guardarPacientesEnCSV();
    }
}
