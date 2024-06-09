package Launcher;

import Controlador.GestionPaciente;
import Modelo.Paciente;

import java.util.Scanner;

public class MenuPaciente {
    private final Scanner scanner;
    private final GestionPaciente gestionPaciente;
    private Paciente pacienteActual; // El paciente que ha iniciado sesión

    public MenuPaciente(Scanner scanner, GestionPaciente gestionPaciente) {
        this.scanner = scanner;
        this.gestionPaciente = gestionPaciente;
    }

    public void iniciarSesion() {
        System.out.print("Ingrese su RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        Paciente paciente = gestionPaciente.iniciarSesion(rut, contrasena);
        if (paciente != null) {
            System.out.println("Inicio de sesión exitoso.");
            pacienteActual = paciente;
        } else {
            System.out.println("RUT o contraseña incorrectos.");
        }
    }

    public void mostrarMenu() {
        iniciarSesion(); // Iniciar sesión al mostrar el menú
        int opcion;
        do {
            System.out.println("=== Menú del Paciente ===");
            System.out.println("1. Ver información personal");
            System.out.println("2. Modificar información personal");
            System.out.println("3. Ver ficha médica");
            System.out.println("4. Modificar ficha médica");
            System.out.println("5. Agendar hora");
            System.out.println("6. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt

            switch (opcion) {
                case 1:
                    verInformacionPersonal();
                    break;
                case 2:
                    modificarInformacionPersonal();
                    break;
                case 3:
                    verFichaMedica();
                    break;
                case 4:
                    modificarFichaMedica();
                    break;
                case 5:
                    System.out.println("proximamente");
                    break;
                case 6:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private void verInformacionPersonal() {
        if (pacienteActual != null) {
            System.out.println(pacienteActual.toString());
        } else {
            System.out.println("Debe iniciar sesión primero.");
        }
    }

    private void modificarInformacionPersonal() {
        if (pacienteActual != null) {
            gestionPaciente.modificarInformacionPersonal(pacienteActual);
        } else {
            System.out.println("Debe iniciar sesión primero.");
        }
    }

    private void verFichaMedica() {
        if (pacienteActual != null) {
            System.out.println(pacienteActual.getFichaMedica());
        } else {
            System.out.println("Debe iniciar sesión primero.");
        }
    }

    private void modificarFichaMedica() {
        if (pacienteActual != null) {
            gestionPaciente.modificarFichaMedica(pacienteActual);
        } else {
            System.out.println("Debe iniciar sesión primero.");
        }
    }

    //! agregar metodo para la agendar horas
}
