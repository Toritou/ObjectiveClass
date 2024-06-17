package Launcher;

import Controlador.GestionPaciente;
import Modelo.Paciente;
import java.util.Scanner;

public class MenuPaciente {

    private GestionPaciente gestionPaciente;
    private Scanner scanner;
    private Paciente pacienteActual;

    public MenuPaciente(Paciente paciente) {
        gestionPaciente = new GestionPaciente();
        scanner = new Scanner(System.in);
        this.pacienteActual = paciente;
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("=== Menú Pacientes ===");
            System.out.println("1. Ver información personal");
            System.out.println("2. Modificar información personal");
            System.out.println("3. Ver ficha médica");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume el salto de línea

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
                    System.out.println("Saliendo del Menú Pacientes...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 4);
    }

    private void verInformacionPersonal() {
        System.out.println("=== Información Personal ===");
        System.out.println("Nombre: " + pacienteActual.getNombreCompleto());
        System.out.println("RUT: " + pacienteActual.getRut());
        System.out.println("Edad: " + pacienteActual.getEdad());
        System.out.println("Fecha de Nacimiento: " + pacienteActual.getFechaNacimiento());
        System.out.println("Tipo de Sangre: " + pacienteActual.getTipoSangre());
        System.out.println("Peso: " + pacienteActual.getPeso());
        System.out.println("Estado Civil: " + pacienteActual.getEstadoCivil());
        System.out.println("Domicilio: " + pacienteActual.getDomicilio());
        System.out.println("Enfermedades: " + pacienteActual.getEnfermedades());
        System.out.println("Alergias: " + pacienteActual.getAlergias());
        System.out.println("Medicamentos: " + pacienteActual.getMedicamentos());
        System.out.println("Cirugías: " + pacienteActual.getCirugias());
        System.out.println("Otros: " + pacienteActual.getOtros());
    }

    private void modificarInformacionPersonal() {
        System.out.println("=== Modificar Información Personal ===");
        System.out.print("Ingrese su nuevo domicilio: ");
        String nuevoDomicilio = scanner.nextLine();
        pacienteActual.setDomicilio(nuevoDomicilio);

        // Implementar otros cambios según sea necesario

        // Actualizar en el sistema de gestión de pacientes
        gestionPaciente.modificarInformacionPersonal(pacienteActual);

        System.out.println("Información personal modificada correctamente.");
    }

    private void verFichaMedica() {
        System.out.println("=== Ficha Médica ===");
        System.out.println("Ficha Médica: " + pacienteActual.getFichaMedica());
    }
}
