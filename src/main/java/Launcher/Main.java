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

        // Rutas de archivos CSV
        String loginFilePath = "path/to/login.csv";

        String pacientesFilePath = "path/to/pacientes.csv";

        // Generar datos de login (Rut y Contraseña)
        Map<String, String> logins = new HashMap<>();
        logins.put("12345678-9", "password123"); // Ejemplo de datos ficticios

        CSVGenerator.generateLoginCSV(loginFilePath, logins);


        HashMap<String, Medico> medicos = null;
        GestionMedico gestionMedico = new GestionMedico(medicos);
        Map<String, Paciente> pacientes = Map.of();
        GestionPaciente gestionPaciente = new GestionPaciente(pacientes);

        // Cargar datos desde CSV
        gestionPaciente = CSVGenerator.cargarPacientesDesdeCSV("pacientes.csv");

        MenuGeneral menuGeneral = new MenuGeneral(scanner, gestionMedico, gestionPaciente);
        menuGeneral.mostrarMenu();

        // Guardar datos en CSV
        CSVGenerator.generatePacientesCSV("pacientes.csv", gestionPaciente.getPacientes());

        scanner.close();
    }
}
