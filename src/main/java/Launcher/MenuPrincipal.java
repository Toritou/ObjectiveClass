package Launcher;

import Controlador.AdministradorSistema;
import Controlador.GestionPaciente;
import Modelo.Paciente;
import java.util.Scanner;

public class MenuPrincipal {
    private GestionPaciente gestionPaciente;
    private AdministradorSistema administradorSistema;
    private Scanner scanner;

    public MenuPrincipal() {
        gestionPaciente = new GestionPaciente();
        administradorSistema = new AdministradorSistema();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Administrador");
            System.out.println("2. Paciente");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume el salto de línea

            switch (opcion) {
                case 1:
                    iniciarSesionAdministrador();
                    break;
                case 2:
                    mostrarMenuPacientes();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 3);
    }

    private void iniciarSesionAdministrador() {
        System.out.print("Ingrese RUT de administrador: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        if (administradorSistema.iniciarSesionComoAdmin(rut, contrasena)) {
            MenuAdministrador menuAdmin = new MenuAdministrador();
            menuAdmin.mostrarMenu();
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    private void mostrarMenuPacientes() {
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("0. Volver al menú principal");
        System.out.print("Ingrese su opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consume el salto de línea

        switch (opcion) {
            case 1:
                iniciarSesionPaciente();
                break;
            case 2:
                registrarPaciente();
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
                break;
        }
    }

    private void iniciarSesionPaciente() {
        System.out.print("Ingrese RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        Paciente paciente = gestionPaciente.iniciarSesion(rut, contrasena);
        if (paciente != null) {
            MenuPaciente menuPacientes = new MenuPaciente(paciente);
            menuPacientes.mostrarMenu();
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    private void registrarPaciente() {
        System.out.print("Ingrese su nombre completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Ingrese su RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese su edad: ");
        String edad = scanner.nextLine();
        System.out.print("Ingrese su fecha de nacimiento: ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Ingrese su tipo de sangre: ");
        String tipoSangre = scanner.nextLine();
        System.out.print("Ingrese su peso: ");
        String peso = scanner.nextLine();
        System.out.print("Ingrese su estado civil: ");
        String estadoCivil = scanner.nextLine();
        System.out.print("Ingrese su domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Ingrese sus enfermedades: ");
        String enfermedades = scanner.nextLine();
        System.out.print("Ingrese sus alergias: ");
        String alergias = scanner.nextLine();
        System.out.print("Ingrese sus medicamentos: ");
        String medicamentos = scanner.nextLine();
        System.out.print("Ingrese sus cirugías: ");
        String cirugias = scanner.nextLine();
        System.out.print("Ingrese otros detalles: ");
        String otros = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        Paciente nuevoPaciente = new Paciente(nombreCompleto, rut, edad, fechaNacimiento, tipoSangre, peso, estadoCivil, domicilio, enfermedades, alergias, medicamentos, cirugias, otros, contrasena);
        gestionPaciente.agregarPaciente(nuevoPaciente);

        System.out.println("Registro completado. Ahora puede iniciar sesión.");
    }
}
