package Controlador;

import Modelo.Paciente;
import csv.CSVManager;

import java.text.SimpleDateFormat;
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
        Paciente paciente = pacientes.get(rut);
        if (paciente != null && paciente.verificarContrasena(contrasena)) {
            return paciente;
        }
        return null;
    }

    private void guardarPacientesEnCSV() {
        CSVManager.escribirPacientes(new ArrayList<>(pacientes.values()));
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.put(paciente.getRut(), paciente);
        guardarPacientesEnCSV();
    }

    public boolean eliminarCita(Paciente pacienteActual, String fechaHoraEliminar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date fechaHora;
        try {
            fechaHora = sdf.parse(fechaHoraEliminar);
        } catch (Exception e) {
            System.out.println("Error al parsear la fecha y hora.");
            return false;
        }

        List<Date> citas = pacienteActual.getCitas();
        for (int i = 0; i < citas.size(); i++) {
            if (citas.get(i).equals(fechaHora)) {
                citas.remove(i);
                pacienteActual.setCitas(citas);
                return true;
            }
        }
        return false;
    }
}
