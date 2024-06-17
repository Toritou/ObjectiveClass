package Controlador;

import Modelo.Paciente;
import csv.CSVManager;
import java.util.*;

public class GestionPaciente {
    private Map<String, Paciente> pacientes;

    public GestionPaciente() {
        this.pacientes = new HashMap<>();
        cargarPacientesDesdeCSV();
    }

    // Cargar pacientes desde el archivo CSV al iniciar la instancia de GestionPaciente
    private void cargarPacientesDesdeCSV() {
        List<Paciente> pacientesCargados = CSVManager.leerPacientes();
        for (Paciente paciente : pacientesCargados) {
            pacientes.put(paciente.getRut(), paciente);
        }
    }

    // Verificar si existe un paciente dado su rut
    public boolean existePaciente(String rut) {
        return pacientes.containsKey(rut);
    }

    // Iniciar sesión de un paciente dado su rut y contraseña
    public Paciente iniciarSesion(String rut, String contrasena) {
        Paciente paciente = pacientes.get(rut);
        if (paciente != null && paciente.verificarContrasena(contrasena)) {
            return paciente;
        }
        return null;
    }

    // Modificar la información personal de un paciente
    public void modificarInformacionPersonal(Paciente pacienteActual) {
        pacientes.put(pacienteActual.getRut(), pacienteActual);
        guardarPacientesEnCSV();
    }

    // Guardar la lista actualizada de pacientes en el archivo CSV
    private void guardarPacientesEnCSV() {
        CSVManager.escribirPacientes(new ArrayList<>(pacientes.values()));
    }

    // Agregar un nuevo paciente a la colección y actualizar el archivo CSV
    public void agregarPaciente(Paciente paciente) {
        pacientes.put(paciente.getRut(), paciente);
        guardarPacientesEnCSV();
    }

    public void agendarCita(Paciente pacienteActual, Date selectedDate) {
        pacienteActual.agendarCita(selectedDate);
        modificarInformacionPersonal(pacienteActual);
    }

}
