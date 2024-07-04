package Vista;

import Controlador.AdministradorSistema;
import Controlador.Correo;
import Modelo.Paciente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuAdministrador {
    private final Scanner scanner;
    private final AdministradorSistema administradorSistema;
    private final Correo correo;

    public MenuAdministrador() {
        scanner = new Scanner(System.in);
        administradorSistema = new AdministradorSistema();
        correo = new Correo("re_LRrR6pYX_2RAA3bGD1Hx4gn1QAr5PCQso");

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
            dateChooser.setMinSelectableDate(new Date());

            JButton btnAceptar = new JButton("Aceptar");

            JFrame frame = new JFrame("Seleccione la Fecha y Hora");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(dateChooser, "Center");
            frame.getContentPane().add(btnAceptar, "South");
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            btnAceptar.addActionListener(_ -> {
                Date selectedDate = dateChooser.getDate();
                if (selectedDate != null) {
                    int confirmacion = JOptionPane.showConfirmDialog(null,
                            "¿Está seguro que desea seleccionar esta fecha?",
                            "Confirmación de Selección",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String fechaHora = sdf.format(selectedDate);

                        String descripcion = JOptionPane.showInputDialog(frame, "Ingrese la descripción de la cita:");
                        if (descripcion != null && !descripcion.trim().isEmpty()) {
                            administradorSistema.agendarCita(paciente, fechaHora, descripcion);
                            JOptionPane.showMessageDialog(frame, "Cita agendada correctamente.");

                            // Enviar correo de confirmación
                            String destinatario = paciente.getCorreo();
                            String asunto = "Confirmación de Cita";
                            String contenidoHtml = "<strong>Su cita ha sido agendada para el " + fechaHora + ".</strong>";
                            correo.enviarCorreo(destinatario, asunto, contenidoHtml);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Descripción no valida.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se selecciono ninguna fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarHora() {
        System.out.print("Ingrese el número de la cita a modificar:  ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice >= 0 && indice < administradorSistema.obtenerAgenda().size()) {
            System.out.print("Ingrese el Rut del paciente: ");
            String rut = scanner.nextLine();
            Paciente paciente = buscarPaciente(rut);

            if (paciente != null) {
                JDateChooser dateChooser = new JDateChooser();
                dateChooser.setDateFormatString("dd/MM/yyyy HH:mm");
                dateChooser.setMinSelectableDate(new Date());

                JButton btnAceptar = new JButton("Aceptar");

                JFrame frame = new JFrame("Seleccione la Nueva Fecha y Hora");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(dateChooser, "Center");
                frame.getContentPane().add(btnAceptar, "South");
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                btnAceptar.addActionListener(_ -> {
                    Date selectedDate = dateChooser.getDate();
                    if (selectedDate != null) {
                        int confirmacion = JOptionPane.showConfirmDialog(null,
                                "¿Está seguro que desea seleccionar esta fecha?",
                                "Confirmación de Selección",
                                JOptionPane.YES_NO_OPTION);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            String fechaHora = sdf.format(selectedDate);

                            String descripcion = JOptionPane.showInputDialog(frame, "Ingrese la nueva descripción de la cita:");
                            if (descripcion != null && !descripcion.trim().isEmpty()) {
                                administradorSistema.modificarCita(indice, paciente, fechaHora, descripcion);
                                JOptionPane.showMessageDialog(frame, "Cita modificada correctamente.");

                                //? Enviar correo de confirmación

                                String destinatario = paciente.getCorreo();
                                String asunto = "Modificacion de Cita";
                                String contenidoHtml = "<strong>Su cita ha sido modificada para el " + fechaHora + ".</strong>";
                                correo.enviarCorreo(destinatario, asunto, contenidoHtml);

                            } else {
                                JOptionPane.showMessageDialog(frame, "Descripción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            frame.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

            } else {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Índice de cita fuera de rango.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarHora() {
        System.out.print("Ingrese el número de la cita a eliminar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice >= 0 && indice < administradorSistema.obtenerAgenda().size()) {
            administradorSistema.eliminarCita(indice);
            JOptionPane.showMessageDialog(null, "Cita eliminada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Índice de cita fuera de rango.", "Error", JOptionPane.ERROR_MESSAGE);
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
            administradorSistema.agregarPaciente(new Paciente(nombre, rut, fechaNacimiento, tipoSangre));
            JOptionPane.showMessageDialog(null, "Paciente agregado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El paciente ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
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
            administradorSistema.modificarPaciente(rut, nombre, tipoSangre);
            JOptionPane.showMessageDialog(null, "Paciente modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPaciente() {
        System.out.print("Ingrese el Rut del paciente a eliminar: ");
        String rut = scanner.nextLine();
        Paciente paciente = buscarPaciente(rut);

        if (paciente != null) {
            administradorSistema.eliminarPaciente(rut);
            JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
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
