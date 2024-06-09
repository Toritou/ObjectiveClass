package Launcher;

import Controlador.GestionMedico;
import Controlador.GestionPaciente;
import Modelo.Medico;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear una instancia de GestionMedico
        GestionMedico gestionMedico = new GestionMedico("pacientes.csv");

        // Obtener el médico por el RUT
        Medico medico = gestionMedico.obtenerMedicoPorRut("1234");

        if (medico != null) {
            // Crear una instancia de GestionPaciente
            GestionPaciente gestionPaciente = new GestionPaciente(medico);

            // Crear una instancia de MenuGeneral
            MenuGeneral menuGeneral = new MenuGeneral(scanner, gestionMedico, gestionPaciente);

            // Mostrar el menú general
            menuGeneral.mostrarMenu();

            scanner.close();
        }
    }
}
