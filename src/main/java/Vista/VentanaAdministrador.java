package Vista;

import Controlador.AdministradorSistema;
import Controlador.Correo;
import Modelo.AgendaCitas;
import Modelo.Paciente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VentanaAdministrador extends JFrame {
    private final AdministradorSistema administradorSistema;
    private final Correo correo;
    private JComboBox<String> comboBoxHoras;
    private JComboBox<String> comboBoxPacientes;
    private JComboBox<String> comboBoxVer;

    public VentanaAdministrador(AdministradorSistema administradorSistema, Correo correo) {
        this.administradorSistema = administradorSistema;
        this.correo = correo;
        correo = new Correo("re_LRrR6pYX_2RAA3bGD1Hx4gn1QAr5PCQso");

        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Menú Administrador");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        // Crear JLabels para las secciones del formulario
        // JLabels para las secciones del formulario
        JLabel lblGestionHoras = new JLabel("Gestión de Horas:");
        lblGestionHoras.setBounds(50, 20, 200, 30);
        add(lblGestionHoras);

        JLabel lblGestionPacientes = new JLabel("Gestión de Pacientes:");
        lblGestionPacientes.setBounds(50, 70, 200, 30);
        add(lblGestionPacientes);

        JLabel lblVerAgenda = new JLabel("Ver Agenda o Pacientes:");
        lblVerAgenda.setBounds(50, 120, 200, 30);
        add(lblVerAgenda);

        // Crear JComboBox para Gestión de Horas
        String[] opcionesHoras = {"Seleccione", "Agregar Hora", "Editar Hora", "Eliminar Hora"};
        comboBoxHoras = new JComboBox<>(opcionesHoras);
        comboBoxHoras.setBounds(50, 50, 250, 30);
        add(comboBoxHoras);

        // Crear JComboBox para Gestión de Pacientes
        String[] opcionesPacientes = {"Seleccione", "Agregar Paciente", "Editar Paciente", "Eliminar Paciente"};
        comboBoxPacientes = new JComboBox<>(opcionesPacientes);
        comboBoxPacientes.setBounds(50, 100, 250, 30);
        add(comboBoxPacientes);

        // Crear JComboBox para Ver Agenda o Pacientes
        String[] opcionesVer = {"Seleccione", "Ver Agenda", "Ver Pacientes"};
        comboBoxVer = new JComboBox<>(opcionesVer);
        comboBoxVer.setBounds(50, 150, 250, 30);
        add(comboBoxVer);

        // Agregar ActionListener al JComboBox para Gestión de Horas
        comboBoxHoras.addActionListener(e -> {
            String seleccion = (String) comboBoxHoras.getSelectedItem();
            switch (seleccion) {
                case "Agregar Hora":
                    agendarHora();
                    break;
                case "Editar Hora":
                    modificarHora();
                    break;
                case "Eliminar Hora":
                    eliminarHora();
                    break;
                default:
                    break;
            }
        });

        // Agregar ActionListener al JComboBox para Gestión de Pacientes
        comboBoxPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBoxPacientes.getSelectedItem();
                switch (seleccion) {
                    case "Agregar Paciente":
                        agregarPaciente();
                        break;
                    case "Editar Paciente":
                        modificarPaciente();
                        break;
                    case "Eliminar Paciente":
                        eliminarPaciente();
                        break;
                    default:
                        break;
                }
            }
        });

        // Agregar ActionListener al JComboBox para Ver Agenda o Pacientes
        comboBoxVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBoxVer.getSelectedItem();
                switch (seleccion) {
                    case "Ver Agenda":
                        verAgenda();
                        break;
                    case "Ver Pacientes":
                        verListaPacientes();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    // Métodos para la funcionalidad de los JComboBox

    private void agendarHora() {
        String rut = JOptionPane.showInputDialog(this, "Ingrese el Rut del paciente:");
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

            btnAceptar.addActionListener(e -> {
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
                            String destinatario = paciente.getCorreo();
                            if (isValidEmailAddress(destinatario)) {
                                // Aquí puedes enviar el correo si lo deseas
                                // correo.enviarCorreo(destinatario, "Confirmación de Cita", "<strong>Su cita ha sido agendada para el " + fechaHora + ".</strong>");
                            } else {
                                JOptionPane.showMessageDialog(frame, "Correo electrónico no válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            }

                            administradorSistema.agendarCita(paciente, fechaHora, descripcion);
                            JOptionPane.showMessageDialog(frame, "Cita agendada correctamente.");
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
    }

    private boolean isValidEmailAddress(String email) {
        // Lógica de validación de dirección de correo electrónico básica
        return email != null && email.contains("@");
    }


    private void modificarHora() {
        String indiceStr = JOptionPane.showInputDialog(this, "Ingrese el número de la cita a modificar:");

        // Verificar si el usuario canceló la operación o no ingresó ningún valor
        if (indiceStr == null || indiceStr.trim().isEmpty()) {
            return; // Salir del método si no se ingresó ningún valor
        }

        try {
            int indice = Integer.parseInt(indiceStr);

            if (indice >= 0 && indice < administradorSistema.obtenerAgenda().size()) {
                String rut = JOptionPane.showInputDialog(this, "Ingrese el Rut del paciente:");
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

                    btnAceptar.addActionListener(e -> {
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

                                    // Enviar correo de confirmación
                                    String destinatario = paciente.getCorreo();
                                    String asunto = "Modificación de Cita";
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para el índice.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarHora() {
        String indiceStr = JOptionPane.showInputDialog(this, "Ingrese el número de la cita a eliminar:");

        // Verificar si el usuario canceló la operación o no ingresó ningún valor
        if (indiceStr == null || indiceStr.trim().isEmpty()) {
            return; // Salir del método si no se ingresó ningún valor
        }

        try {
            int indice = Integer.parseInt(indiceStr);

            if (indice >= 0 && indice < administradorSistema.obtenerAgenda().size()) {
                administradorSistema.eliminarCita(indice);
                JOptionPane.showMessageDialog(null, "Cita eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Índice de cita fuera de rango.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void agregarPaciente() {
        String rut = JOptionPane.showInputDialog(this, "Ingrese el Rut del paciente:");
        Paciente paciente = buscarPaciente(rut);

        if (paciente == null) {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del paciente:");
            String fechaNacimiento = JOptionPane.showInputDialog(this, "Ingrese la fecha de nacimiento del paciente (dd/MM/yyyy):");
            String tipoSangre = JOptionPane.showInputDialog(this, "Ingrese el tipo de sangre del paciente:");
            administradorSistema.agregarPaciente(new Paciente(nombre, rut, fechaNacimiento, tipoSangre));
            JOptionPane.showMessageDialog(null, "Paciente agregado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El paciente ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarPaciente() {
        String rut = JOptionPane.showInputDialog(this, "Ingrese el Rut del paciente a modificar:");
        Paciente paciente = buscarPaciente(rut);

        if (paciente != null) {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre del paciente:");
            String tipoSangre = JOptionPane.showInputDialog(this, "Ingrese el nuevo tipo de sangre del paciente:");
            administradorSistema.modificarPaciente(rut, nombre, tipoSangre);
            JOptionPane.showMessageDialog(null, "Paciente modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPaciente() {
        String rut = JOptionPane.showInputDialog(this, "Ingrese el Rut del paciente a eliminar:");
        Paciente paciente = buscarPaciente(rut);

        if (paciente != null) {
            administradorSistema.eliminarPaciente(rut);
            JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verAgenda() {
        StringBuilder agenda = new StringBuilder("=== Agenda ===\n");
        List<AgendaCitas.Cita> citas = administradorSistema.obtenerAgenda(); // Asumiendo que obtenerAgenda() devuelve List<Cita>
        if (citas != null) {
            for (AgendaCitas.Cita cita : citas) {
                agenda.append("Paciente: ").append(cita.getPaciente().getNombreCompleto()).append("\n");
                agenda.append("Fecha y hora: ").append(cita.getFechaHora()).append("\n");
                agenda.append("Descripción: ").append(cita.getDescripcion()).append("\n\n");
            }
            JOptionPane.showMessageDialog(this, agenda.toString(), "Agenda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener la agenda.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verListaPacientes() {
        StringBuilder listaPacientes = new StringBuilder("=== Lista de Pacientes ===\n");
        for (Paciente paciente : administradorSistema.obtenerListaPacientes()) {
            listaPacientes.append(paciente.getNombreCompleto()).append("\n");
        }
        JOptionPane.showMessageDialog(this, listaPacientes.toString(), "Pacientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private Paciente buscarPaciente(String rut) {
        return administradorSistema.obtenerListaPacientes().stream()
                .filter(paciente -> paciente.getRut().equals(rut))
                .findFirst()
                .orElse(null);
    }

}
