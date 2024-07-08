package Vista;

import Controlador.AdministradorSistema;
import Controlador.GestionPaciente;
import Modelo.Pacientes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    private final GestionPaciente gestionPaciente;
    private final AdministradorSistema administradorSistema;
    private final Scanner scanner;

    public MenuPrincipal() {
        gestionPaciente = new GestionPaciente();
        administradorSistema = new AdministradorSistema();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;

        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        iniciarSesion();
                        break;
                    case 2:
                        registrar();
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine();
            }

        } while (opcion != 0);
    }

    private void iniciarSesion() {
        System.out.print("Ingrese RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();

        //! Verificar si es administrador
        if (administradorSistema.iniciarSesionComoAdmin(rut, contrasena)) {
            MenuAdministrador menuAdmin = new MenuAdministrador();
            menuAdmin.mostrarMenu();
        } else {
            //! Si no es administrador, intentar iniciar sesiOn como paciente
            Pacientes paciente = gestionPaciente.iniciarSesion(rut, contrasena);
            if (paciente != null) {
                MenuPaciente menuPacientes = new MenuPaciente(paciente);
                menuPacientes.mostrarMenu();
            } else {
                System.out.println("Credenciales incorrectas.");
            }
        }
    }

    private void registrar() {
        System.out.print("Ingrese su nombre completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Ingrese su RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese su edad: ");
        String edad = scanner.nextLine();
        System.out.print("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Ingrese su tipo de sangre: ");
        String tipoSangre = scanner.nextLine();
        System.out.print("Ingrese su peso (kg): ");
        String peso = scanner.nextLine();
        System.out.print("Ingrese su estado civil: ");
        String estadoCivil = scanner.nextLine();
        System.out.print("Ingrese su domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Ingrese sus enfermedades (separadas por comas): ");
        String enfermedades = scanner.nextLine();
        System.out.print("Ingrese sus alergias (separadas por comas): ");
        String alergias = scanner.nextLine();
        System.out.print("Ingrese sus medicamentos (separados por comas): ");
        String medicamentos = scanner.nextLine();
        System.out.print("Ingrese sus cirugías (separadas por comas): ");
        String cirugias = scanner.nextLine();
        System.out.print("Ingrese otros detalles: ");
        String otros = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        Pacientes nuevoPaciente = new Pacientes(nombreCompleto, rut, correo, edad, fechaNacimiento, tipoSangre, peso, estadoCivil, domicilio, enfermedades, alergias, medicamentos, cirugias, otros, contrasena);
        gestionPaciente.agregarPaciente(nuevoPaciente);

        System.out.println("Registro completado. Ahora puede iniciar sesión.");
    }
}
