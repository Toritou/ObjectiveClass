package Launcher;

import Controlador.GestionMedico;
import Controlador.GestionPaciente;
import Modelo.Medico;
import csv.CSVGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        GestionMedico gestionMedico = new GestionMedico();

        Medico medico = new Medico("1234", "Dr. Juan Perez", "Medicina General", "Hospital ABC");
        gestionMedico.agregarPaciente(medico);

        CSVGenerator.generateMedicosCSV("medicos.csv", gestionMedico.getMedicos());

        if (medico != null) {
            GestionPaciente gestionPaciente = new GestionPaciente(medico);

            MenuGeneral menuGeneral = new MenuGeneral(scanner, gestionMedico, gestionPaciente);

            menuGeneral.mostrarMenu();
        } else {
            System.out.println("El médico no fue encontrado.");
        }

        CSVGenerator.generatePacientesCSV("pacientes.csv", gestionMedico.getPacientes());

        scanner.close();
    }

    public static void iniciarPrograma() {
        Scanner scanner = new Scanner(System.in);

        GestionMedico gestionMedico = new GestionMedico();

        Medico medico = new Medico("1234", "Dr. Juan Perez", "Medicina General", "Hospital ABC");
        gestionMedico.agregarPaciente(medico);

        if (medico != null) {
            GestionPaciente gestionPaciente = new GestionPaciente(medico);

            MenuGeneral menuGeneral = new MenuGeneral(scanner, gestionMedico, gestionPaciente);

            menuGeneral.mostrarMenu();
        } else {
            System.out.println("El médico no fue encontrado.");
        }

        scanner.close();
    }
}