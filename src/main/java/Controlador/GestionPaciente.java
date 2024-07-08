package Controlador;

import Modelo.Pacientes;
import csv.CSVManager;
import java.util.*;

public class GestionPaciente {
    private final Map<String, Pacientes> pacientes;

    public GestionPaciente() {
        this.pacientes = new HashMap<>();
        cargarPacientesDesdeCSV();
    }

    private void cargarPacientesDesdeCSV() {
        List<Pacientes> pacientesCargados = CSVManager.leerPacientes();
        for (Pacientes paciente : pacientesCargados) {
            pacientes.put(paciente.getRut(), paciente);
        }
    }

    public Pacientes iniciarSesion(String rut, String contrasena) {
        try {
            Pacientes paciente = pacientes.get(rut);
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

    public void agregarPaciente(Pacientes paciente) {
        pacientes.put(paciente.getRut(), paciente);
        guardarPacientesEnCSV();
    }
}
