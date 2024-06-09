package Launcher;

import Controlador.GestionMedico;
import Controlador.GestionPaciente;
import Modelo.Paciente;
import java.util.Scanner;

public class MenuGeneral {
    private final Scanner scanner;
    private final GestionMedico gestionMedico;
    private final GestionPaciente gestionPaciente;

    public MenuGeneral(Scanner scanner, GestionMedico gestionMedico, GestionPaciente gestionPaciente) {
        this.scanner = scanner;
        this.gestionMedico = gestionMedico;
        this.gestionPaciente = gestionPaciente;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("=== Menú General ===");
            System.out.println("1. Soy Médico");
            System.out.println("2. Soy Paciente");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt

            switch (opcion) {
                case 1:
                    MenuMedico menuMedico = new MenuMedico(scanner, gestionMedico);
                    menuMedico.mostrarMenu();
                    break;
                case 2:
                    System.out.println("¿Ya estás registrado como paciente? (Sí/No)");
                    String respuesta = scanner.nextLine().toLowerCase();
                    if (respuesta.equals("si")) {
                        MenuPaciente menuPaciente = new MenuPaciente(scanner, gestionPaciente);
                        menuPaciente.mostrarMenu();
                    } else if (respuesta.equals("no")) {
                        registrarPaciente();
                    } else {
                        System.out.println("Respuesta no válida. Intente nuevamente.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }

    private void registrarPaciente() {
        System.out.println("=== Registro de Paciente ===");
        System.out.print("Ingrese su nombre completo: ");
        String nombre = scanner.nextLine();
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
        System.out.print("Ingrese otros datos relevantes: ");
        String otros = scanner.nextLine();
        System.out.print("Ingrese una contraseña para su ficha médica: ");
        String contrasena = scanner.nextLine();

        // Crea un nuevo objeto Paciente con la información proporcionada
        Paciente nuevoPaciente = new Paciente(nombre, rut, edad, fechaNacimiento, tipoSangre, peso, estadoCivil,
                domicilio, enfermedades, alergias, medicamentos, cirugias, otros, contrasena);

        // Agrega el nuevo paciente a la lista de pacientes utilizando la clase GestionPaciente
        gestionPaciente.agregarPaciente(nuevoPaciente);

        System.out.println("Registro completado con éxito.");
    }

}