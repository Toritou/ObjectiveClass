package Launcher;

import Controlador.AdministradorSistema;
import Modelo.Paciente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    Date selectedDate = dateChooser.getDate();
                    if (selectedDate != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String fechaHora = sdf.format(selectedDate);

                        String descripcion = JOptionPane.showInputDialog(frame, "Ingrese la descripción de la cita:");
                        if (descripcion != null && !descripcion.trim().isEmpty()) {
                            administradorSistema.agendarCita(paciente, fechaHora, descripcion);
                            System.out.println("Cita agendada correctamente.");
                        } else {
                            System.out.println("Descripción no válida.");
                        }
                    } else {
                        System.out.println("No se seleccionó ninguna fecha.");
                    }
                }
            });

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
                JDateChooser dateChooser = new JDateChooser();
                dateChooser.setDateFormatString("dd/MM/yyyy HH:mm");

                JFrame frame = new JFrame("Seleccione la Nueva Fecha y Hora");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(dateChooser);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Date selectedDate = dateChooser.getDate();
                        if (selectedDate != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            String fechaHora = sdf.format(selectedDate);

                            String descripcion = JOptionPane.showInputDialog(frame, "Ingrese la nueva descripción de la cita:");
                            if (descripcion != null && !descripcion.trim().isEmpty()) {
                                administradorSistema.modificarCita(indice, paciente, fechaHora, descripcion);
                                System.out.println("Cita modificada correctamente.");
                            } else {
                                System.out.println("Descripción no válida.");
                            }
                        } else {
                            System.out.println("No se seleccionó ninguna fecha.");
                        }
                    }
                });

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
            System.out.println("Cita eliminada correctamente.");
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
            System.out.print("Ingrese la fecha de nacimiento del paciente (dd/MM/yyyy): ");
            String fechaNacimiento = scanner.nextLine();
            System.out.print("Ingrese el tipo de sangre del paciente: ");
            String tipoSangre = scanner.nextLine();
            // Aquí puedes agregar otros campos necesarios para el paciente
            administradorSistema.agregarPaciente(new Paciente(nombre, rut, fechaNacimiento, tipoSangre));
            System.out.println("Paciente agregado correctamente.");
        } else {
            System.out.println("El paciente ya existe.");
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
            // Aquí puedes modificar otros campos necesarios para el paciente
            administradorSistema.modificarPaciente(rut, nombre, tipoSangre);
            System.out.println("Paciente modificado correctamente.");
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
            System.out.println("Paciente eliminado correctamente.");
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
