package Launcher;

import Controlador.GestionPaciente;
import Modelo.Paciente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            System.out.println("2. Agendar hora");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verInformacionPersonal();
                    break;
                case 2:
                    agendarHora();
                    break;
                case 3:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }

    private void verInformacionPersonal() {
        if (pacienteActual != null) {
            System.out.println("=== Información Personal ===");
            System.out.println("Nombre Completo: " + pacienteActual.getNombreCompleto());
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
            System.out.println("Ficha Médica: " + pacienteActual.getFichaMedica());
        } else {
            System.out.println("Debe iniciar sesión primero.");
        }
    }

    private void agendarHora() {
        if (pacienteActual != null) {
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("dd/MM/yyyy HH:mm");

            JButton btnAceptar = new JButton("Aceptar");

            JFrame frame = new JFrame("Seleccione la Fecha y Hora");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(dateChooser, "Center");
            frame.getContentPane().add(btnAceptar, "South");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            btnAceptar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Date selectedDate = dateChooser.getDate();
                    if (selectedDate != null) {
                        // Mostrar mensaje de confirmación
                        int confirmacion = JOptionPane.showConfirmDialog(null,
                                "¿Está seguro que desea seleccionar esta fecha?",
                                "Confirmación de Selección",
                                JOptionPane.YES_NO_OPTION);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            System.out.println("Cita agendada para: " + selectedDate);
                            gestionPaciente.agendarCita(pacienteActual, selectedDate);
                            frame.dispose(); // Cerrar la ventana después de agendar la cita
                        } else {
                            // No hacer nada si se cancela la selección
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } else {
            System.out.println("Debe iniciar sesión primero.");
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso:
        Paciente paciente = new Paciente("Juan Perez", "12345678-9", "25", "01/01/1990",
                "O+", "70 kg", "Soltero", "Calle 123", "Ninguna", "Ninguna", "", "", "", "");

        MenuPaciente menu = new MenuPaciente(paciente);
        menu.mostrarMenu();
    }
}
