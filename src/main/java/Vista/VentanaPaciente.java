package Vista;

import Controlador.Correo;
import Modelo.Pacientes;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaPaciente extends JFrame {
    private final Pacientes pacienteActual;
    private final Correo correo;


    public VentanaPaciente(Pacientes paciente) {
        this.pacienteActual = paciente;
        initComponents();
        correo = new Correo("re_LRrR6pYX_2RAA3bGD1Hx4gn1QAr5PCQso");
    }

    private void initComponents() {
        // Configuración de la ventana principal
        setTitle("Menú del Paciente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Botón para ver información personal
        JButton btnVerInfoPersonal = new JButton("Ver información personal");
        btnVerInfoPersonal.addActionListener(_ -> verInformacionPersonal());
        panel.add(btnVerInfoPersonal);

        // Botón para agendar hora
        JButton btnAgendarHora = new JButton("Agendar hora");
        btnAgendarHora.addActionListener(_ -> agendarHora());
        panel.add(btnAgendarHora);

        // Botón para ver agenda
        JButton btnVerAgenda = new JButton("Ver agenda");
        btnVerAgenda.addActionListener(_ -> verAgenda());
        panel.add(btnVerAgenda);

        // Botón para eliminar hora
        JButton btnEliminarHora = new JButton("Eliminar hora");
        btnEliminarHora.addActionListener(_ -> eliminarHora());
        panel.add(btnEliminarHora);

        // Botón para salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(_ -> dispose());
        panel.add(btnSalir);

        add(panel);
    }

    private void verInformacionPersonal() {
        if (pacienteActual != null) {
            JOptionPane.showMessageDialog(this,
                    "=== Información Personal ===\n" +
                            "Nombre Completo: " + pacienteActual.getNombreCompleto() + "\n" +
                            "RUT: " + pacienteActual.getRut() + "\n" +
                            "Edad: " + pacienteActual.getEdad() + "\n" +
                            "Fecha de Nacimiento: " + pacienteActual.getFechaNacimiento() + "\n" +
                            "Tipo de Sangre: " + pacienteActual.getTipoSangre() + "\n" +
                            "Peso: " + pacienteActual.getPeso() + "\n" +
                            "Estado Civil: " + pacienteActual.getEstadoCivil() + "\n" +
                            "Domicilio: " + pacienteActual.getDomicilio() + "\n" +
                            "Enfermedades: " + pacienteActual.getEnfermedades() + "\n" +
                            "Alergias: " + pacienteActual.getAlergias() + "\n" +
                            "Medicamentos: " + pacienteActual.getMedicamentos() + "\n" +
                            "Cirugías: " + pacienteActual.getCirugias() + "\n" +
                            "Otros: " + pacienteActual.getOtros() + "\n" +
                            "Ficha Médica: " + pacienteActual.getFichaMedica(),
                    "Información Personal",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Debe iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
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
            frame.getContentPane().add(dateChooser, BorderLayout.CENTER);
            frame.getContentPane().add(btnAceptar, BorderLayout.SOUTH);
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
                            String cita = pacienteActual.getRut() + "," + fechaHora + ","
                                    + pacienteActual.getNombreCompleto() + "," + descripcion;
                            guardarCitaEnAgenda(cita);
                            JOptionPane.showMessageDialog(frame, "Cita agendada correctamente.");

                            // Enviar correo de confirmación
                            String destinatario = pacienteActual.getCorreo();
                            String asunto = "Confirmación de Cita";
                            String contenidoHtml = "<strong>Su cita ha sido agendada para el " + fechaHora + ".</strong>";
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
            JOptionPane.showMessageDialog(this, "Debe iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCitaEnAgenda(String cita) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("agenda.csv", true))) {
            writer.println(cita);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la cita en el archivo de agenda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verAgenda() {
        StringBuilder agenda = new StringBuilder("=== Agenda del Paciente ===\n");
        try (BufferedReader br = new BufferedReader(new FileReader("agenda.csv"))) {
            String line;
            int count = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length >= 4 && datos[0].equals(pacienteActual.getRut())) {
                    Date fechaHora = sdf.parse(datos[1]);
                    String nombrePaciente = datos[2];
                    String detalleHora = datos[3];
                    agenda.append("- Cita número ").append(count).append("\n")
                            .append("  Fecha y Hora: ").append(sdf.format(fechaHora)).append("\n")
                            .append("  Nombre del Paciente: ").append(nombrePaciente).append("\n")
                            .append("  Detalle de la Hora: ").append(detalleHora).append("\n\n");
                    count++;
                }
            }
            if (count == 1) {
                agenda.append("No hay citas agendadas.\n");
            }
        } catch (IOException | java.text.ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al leer la agenda del paciente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, agenda.toString(), "Agenda del Paciente", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarHora() {
        if (pacienteActual != null) {
            String numeroCitaStr = JOptionPane.showInputDialog(this, "Ingrese el número de la cita a eliminar:");
            if (numeroCitaStr != null && !numeroCitaStr.trim().isEmpty()) {
                try {
                    int numeroCita = Integer.parseInt(numeroCitaStr);
                    eliminarCita(pacienteActual, numeroCita);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Número de cita no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCita(Pacientes paciente, int numeroCita) {
        File agendaFile = new File("agenda.csv");
        File tempFile = new File("agenda_temp.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(agendaFile));
             PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            int count = 1;
            boolean citaEliminada = false;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length >= 4 && datos[0].equals(paciente.getRut())) {
                    if (count == numeroCita) {
                        citaEliminada = true;
                    } else {
                        pw.println(line);
                    }
                    count++;
                } else {
                    pw.println(line);
                }
            }

            if (citaEliminada) {
                JOptionPane.showMessageDialog(this, "Cita eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna cita con el número especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la cita: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Renombrar el archivo temporal al original
        if (agendaFile.delete() && tempFile.renameTo(agendaFile)) {
            System.out.println("Archivo de agenda actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el archivo de agenda.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
