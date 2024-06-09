package Launcher;

import Controlador.GestionMedico;
import Controlador.GestionPaciente;
import Modelo.Medico;
import csv.CSVGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear una instancia de GestionMedico
        GestionMedico gestionMedico = new GestionMedico();

        // Crear un médico de prueba y agregarlo a la lista de pacientes
        Medico medico = new Medico("1234", "Dr. Juan Perez", "Medicina General", "Hospital ABC");
        gestionMedico.agregarPaciente(medico);

        // Generar el CSV de médicos
        CSVGenerator.generateMedicosCSV("medicos.csv", gestionMedico.getMedicos());

        if (medico != null) {
            // Crear una instancia de GestionPaciente
            GestionPaciente gestionPaciente = new GestionPaciente(medico);

            // Crear una instancia de MenuGeneral
            MenuGeneral menuGeneral = new MenuGeneral(scanner, gestionMedico, gestionPaciente);

            // Mostrar el menú general
            menuGeneral.mostrarMenu();
        } else {
            System.out.println("El médico no fue encontrado.");
        }

        // Generar el CSV de pacientes
        CSVGenerator.generatePacientesCSV("pacientes.csv", gestionMedico.getPacientes());

        scanner.close();
    }
}