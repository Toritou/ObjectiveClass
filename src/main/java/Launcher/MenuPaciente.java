package Launcher;

import com.toedter.calendar.JDateChooser;
import Controlador.GestionPaciente;
import Modelo.Paciente;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Scanner;

public class MenuPaciente {
    private final Scanner scanner;
    private final GestionPaciente gestionPaciente;
    private Paciente pacienteActual;

    public MenuPaciente(Paciente paciente) {
        this.scanner = new Scanner(System.in);
        this.gestionPaciente = new GestionPaciente();
        this.pacienteActual = paciente;
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
        iniciarSesion();
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
            scanner.nextLine();

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
                case 5:
                    agendarHora();
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

    private void agendarHora() {
        if (pacienteActual != null) {
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("dd/MM/yyyy HH:mm");

            JFrame frame = new JFrame("Seleccione la Fecha y Hora");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(dateChooser);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    Date selectedDate = dateChooser.getDate();
                    if (selectedDate != null) {
                        System.out.println("Cita agendada para: " + selectedDate);
                        gestionPaciente.agendarCita(pacienteActual, selectedDate);
                    } else {
                        System.out.println("No se seleccionó ninguna fecha.");
                    }
                }
            });
        } else {
            System.out.println("Debe iniciar sesión primero.");
        }
    }
}
