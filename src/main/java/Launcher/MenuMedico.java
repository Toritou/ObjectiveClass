package Launcher;

import Controlador.GestionMedico;
import java.util.Scanner;

public class MenuMedico {
    private final Scanner scanner;
    private final GestionMedico gestionMedico;

    public MenuMedico(Scanner scanner, GestionMedico gestionMedico) {
        this.scanner = scanner;
        this.gestionMedico = gestionMedico;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("=== Menú del Médico ===");
            System.out.println("1. Ver pacientes");
            System.out.println("2. Agregar paciente");
            System.out.println("3. Modificar paciente");
            System.out.println("4. Eliminar paciente");
            System.out.println("5. Ver ficha médica de paciente");
            System.out.println("6. Modificar agenda de horas");
            System.out.println("7. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt

            switch (opcion) {
                case 1:
                    verPacientes();
                    break;
                case 2:
                    agregarPaciente();
                    break;
                case 3:
                    modificarPaciente();
                    break;
                case 4:
                    eliminarPaciente();
                    break;
                case 5:
                    verFichaMedicaPaciente();
                    break;
                case 6:
                    modificarAgendaHoras();
                    break;
                case 7:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 7);
    }

    private void verPacientes() {
        // Implementa la lógica para ver la lista de pacientes del médico
    }

    private void agregarPaciente() {
        // Implementa la lógica para agregar un nuevo paciente a la lista del médico
    }

    private void modificarPaciente() {
        // Implementa la lógica para modificar la información de un paciente
    }

    private void eliminarPaciente() {
        // Implementa la lógica para eliminar un paciente de la lista del médico
    }

    private void verFichaMedicaPaciente() {
        // Implementa la lógica para ver la ficha médica de un paciente específico
    }

    private void modificarAgendaHoras() {
        // Implementa la lógica para modificar la agenda de horas del médico
    }
}
