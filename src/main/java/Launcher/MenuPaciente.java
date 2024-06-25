package Launcher;

import Controlador.GestionPaciente;
import Modelo.Paciente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
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
            System.out.println("3. Ver agenda");
            System.out.println("4. Eliminar hora");
            System.out.println("0. Salir");
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
                    verAgenda();
                    break;
                case 4:
                    eliminarHora();
                    break;
                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);
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
            dateChooser.setMinSelectableDate(new Date());

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
                        int confirmacion = JOptionPane.showConfirmDialog(null,
                                "¿Está seguro que desea seleccionar esta fecha?",
                                "Confirmación de Selección",
                                JOptionPane.YES_NO_OPTION);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            String fechaHora = sdf.format(selectedDate);

                            String descripcion = JOptionPane.showInputDialog(frame, "Ingrese la descripción de la cita:");
                            if (descripcion != null && !descripcion.trim().isEmpty()) {
                                String cita = pacienteActual.getRut() + "," + fechaHora + ","
                                        + pacienteActual.getNombreCompleto() + "," + descripcion;
                                guardarCitaEnAgenda(cita);
                                JOptionPane.showMessageDialog(frame, "Cita agendada correctamente.");
                            } else {
                                JOptionPane.showMessageDialog(frame, "Descripción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            frame.dispose();
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

    private void guardarCitaEnAgenda(String cita) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("agenda.csv", true))) {
            writer.println(cita);
        } catch (IOException e) {
            System.err.println("Error al guardar la cita en el archivo de agenda: " + e.getMessage());
        }
    }

    private void verAgenda() {
        try (BufferedReader br = new BufferedReader(new FileReader("agenda.csv"))) {
            String line;
            System.out.println("=== Agenda del Paciente ===");
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length >= 4 && datos[0].equals(pacienteActual.getRut())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    Date fechaHora = sdf.parse(datos[1]);
                    String nombrePaciente = datos[2];
                    String detalleHora = datos[3];
                    System.out.println("- Rut: " + datos[0]);
                    System.out.println("  Fecha y Hora: " + sdf.format(fechaHora));
                    System.out.println("  Nombre del Paciente: " + nombrePaciente);
                    System.out.println("  Detalle de la Hora: " + detalleHora);
                    System.out.println();
                }
            }
        } catch (IOException | java.text.ParseException e) {
            System.err.println("Error al leer la agenda del paciente: " + e.getMessage());
        }
    }

    private void eliminarHora() {
        if (pacienteActual != null) {
            System.out.println("=== Eliminar Hora ===");
            System.out.print("Ingrese la fecha y hora a eliminar (dd/MM/yyyy HH:mm): ");
            String fechaHoraEliminar = scanner.nextLine();

            boolean citaEliminada = gestionPaciente.eliminarCita(pacienteActual, fechaHoraEliminar);
            if (citaEliminada) {
                System.out.println("Cita eliminada correctamente.");
            } else {
                System.out.println("No se encontró ninguna cita con esa fecha y hora.");
            }
        } else {
            System.out.println("Debe iniciar sesión primero.");
        }
    }
}
