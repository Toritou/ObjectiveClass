package Launcher;

import Controlador.GestionMedico;
import Modelo.Paciente;

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
            scanner.nextLine();

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
        System.out.println("Lista de pacientes:");
        for (Paciente paciente : gestionMedico.getPacientes().values()) {
            System.out.println(paciente);
        }
    }

    private void agregarPaciente() {
        System.out.print("Ingrese el RUT del nuevo paciente: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese el nombre completo del paciente: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Ingrese la edad del paciente: ");
        String edad = scanner.nextLine();
        // Continuar con los demás datos necesarios

        Paciente nuevoPaciente = new Paciente(nombreCompleto, rut, edad, "", "", "", "", "", "", "", "", "", "", "");
        gestionMedico.agregarPaciente(nuevoPaciente);
        System.out.println("Paciente agregado exitosamente.");
    }

    private void modificarPaciente() {
        System.out.print("Ingrese el RUT del paciente a modificar: ");
        String rut = scanner.nextLine();
        Paciente paciente = gestionMedico.obtenerPacientePorRut(rut);

        if (paciente != null) {
            System.out.println("Ingrese los nuevos datos para el paciente:");
            // Pida los nuevos datos y actualice el paciente
            System.out.print("Nuevo nombre completo: ");
            String nombreCompleto = scanner.nextLine();
            paciente.setNombreCompleto(nombreCompleto);
            // Continuar con los demás campos

            gestionMedico.actualizarPaciente(rut, paciente);
            System.out.println("Paciente actualizado exitosamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private void eliminarPaciente() {
        System.out.print("Ingrese el RUT del paciente a eliminar: ");
        String rut = scanner.nextLine();
        gestionMedico.eliminarPaciente(rut);
    }

    private void verFichaMedicaPaciente() {
        System.out.print("Ingrese el RUT del paciente: ");
        String rut = scanner.nextLine();
        Paciente paciente = gestionMedico.obtenerPacientePorRut(rut);
        if (paciente != null) {
            System.out.println(paciente.getFichaMedica());
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private void modificarAgendaHoras() {
        // Implementar la lógica para modificar la agenda de horas
        System.out.println("Funcionalidad en desarrollo.");
    }
}
