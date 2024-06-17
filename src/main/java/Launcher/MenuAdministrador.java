package Launcher;

import Controlador.AdministradorSistema;
import Modelo.Paciente;
import java.util.Scanner;

public class MenuAdministrador {
    private Scanner scanner;
    private AdministradorSistema administradorSistema;

    public MenuAdministrador() {
        scanner = new Scanner(System.in);
        administradorSistema = new AdministradorSistema();
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("=== Menú Administrador ===");
            System.out.println("1. Agendar Hora");
            System.out.println("2. Modificar Hora");
            System.out.println("3. Eliminar Hora");
            System.out.println("4. Agregar Paciente");
            System.out.println("5. Modificar Paciente");
            System.out.println("6. Eliminar Paciente");
            System.out.println("7. Ver Agenda");
            System.out.println("8. Ver lista de Pacientes");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume el salto de línea

            switch (opcion) {
                case 1:
                    agendarHora();
                    break;
                case 2:
                    modificarHora();
                    break;
                case 3:
                    eliminarHora();
                    break;
                case 4:
                    agregarPaciente();
                    break;
                case 5:
                    modificarPaciente();
                    break;
                case 6:
                    eliminarPaciente();
                    break;
                case 7:
                    verAgenda();
                    break;
                case 8:
                    verListaPacientes();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 0);
    }

    private void agendarHora() {
        System.out.print("Ingrese el Rut del paciente: ");
        String rut = scanner.nextLine();
        Paciente paciente = buscarPaciente(rut);

        if (paciente != null) {
            System.out.print("Ingrese la descripción de la cita: ");
            String descripcion = scanner.nextLine();
            administradorSistema.agendarCita(paciente, descripcion);
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private void modificarHora() {
        System.out.print("Ingrese el número de la cita a modificar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consume el salto de línea

        if (indice >= 0 && indice < administradorSistema.obtenerAgenda().size()) {
            System.out.print("Ingrese el nuevo Rut del paciente: ");
            String rut = scanner.nextLine();
            Paciente paciente = buscarPaciente(rut);

            if (paciente != null) {
                System.out.print("Ingrese la nueva descripción de la cita: ");
                String descripcion = scanner.nextLine();
                administradorSistema.modificarCita(indice, paciente, descripcion);
            } else {
                System.out.println("Paciente no encontrado.");
            }
        } else {
            System.out.println("Índice de cita fuera de rango.");
        }
    }

    private void eliminarHora() {
        System.out.print("Ingrese el número de la cita a eliminar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consume el salto de línea

        if (indice >= 0 && indice < administradorSistema.obtenerAgenda().size()) {
            administradorSistema.eliminarCita(indice);
        } else {
            System.out.println("Índice de cita fuera de rango.");
        }
    }

    private void agregarPaciente() {
        System.out.print("Ingrese el Rut del paciente: ");
        String rut = scanner.nextLine();
        Paciente paciente = buscarPaciente(rut);

        if (paciente == null) {
            System.out.print("Ingrese el nombre del paciente: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la fecha de nacimiento del paciente (dd/mm/yyyy): ");
            String fechaNacimiento = scanner.nextLine();
            System.out.print("Ingrese el tipo de sangre del paciente: ");
            String tipoSangre = scanner.nextLine();
        }
    }

    private void modificarPaciente() {
        System.out.print("Ingrese el Rut del paciente a modificar: ");
        String rut = scanner.nextLine();
        Paciente paciente = buscarPaciente(rut);

        if (paciente != null) {
            System.out.print("Ingrese el nuevo nombre del paciente: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el nuevo tipo de sangre del paciente: ");
            String tipoSangre = scanner.nextLine();

        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private void eliminarPaciente() {
        System.out.print("Ingrese el Rut del paciente a eliminar: ");
        String rut = scanner.nextLine();
        Paciente paciente = buscarPaciente(rut);

        if (paciente != null) {
            administradorSistema.eliminarPaciente(rut);
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private void verAgenda() {
        administradorSistema.verAgenda();
    }

    private void verListaPacientes() {
        System.out.println("=== Lista de Pacientes ===");
        administradorSistema.obtenerListaPacientes().forEach(paciente -> System.out.println(paciente.getNombreCompleto()));
    }

    private Paciente buscarPaciente(String rut) {
        for (Paciente paciente : administradorSistema.obtenerListaPacientes()) {
            if (paciente.getRut().equals(rut)) {
                return paciente;
            }
        }
        return null;
    }


}
