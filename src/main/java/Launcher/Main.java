package Launcher;

import Controlador.GestionMedico;
import Controlador.GestionPaciente;
import Modelo.Medico;
import Modelo.Paciente;
import csv.CSVGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Medico> medicos = null;
        GestionMedico gestionMedico = new GestionMedico(medicos);
        Map<String, Paciente> pacientes = Map.of();
        GestionPaciente gestionPaciente = new GestionPaciente(pacientes);

        // Cargar datos desde CSV
        gestionMedico = CSVGenerator.cargarMedicosDesdeCSV("medicos.csv");
        gestionPaciente = CSVGenerator.cargarPacientesDesdeCSV("pacientes.csv");

        // Crear y agregar un médico y un paciente si no existen
        if (gestionMedico.obtenerMedicoPorRut("1234") == null) {
            Medico medico = new Medico("1234", "Dr. Juan Perez", "Medicina General", "Hospital ABC");
            gestionMedico.agregarMedico(medico);
        }

        if (gestionPaciente.obtenerPacientePorRut("12345678-9") == null) {
            Paciente paciente = new Paciente("12345678-9", "Juan Pérez", "30", "01/01/1994", "O+", "70kg", "Soltero", "Av. Siempre Viva 742", "Ninguna", "Ninguna", "Ninguno", "Ninguna", "Ninguno", "ficha123");
            gestionPaciente.agregarPaciente(paciente);
        }

        MenuGeneral menuGeneral = new MenuGeneral(scanner, gestionMedico, gestionPaciente);
        menuGeneral.mostrarMenu();

        // Guardar datos en CSV
        CSVGenerator.generateMedicosCSV("medicos.csv", gestionMedico.getMedicos());
        CSVGenerator.generatePacientesCSV("pacientes.csv", gestionPaciente.getPacientes());

        scanner.close();
    }
}
