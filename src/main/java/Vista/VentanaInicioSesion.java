package Vista;

import Controlador.AdministradorSistema;
import Controlador.GestionPaciente;
import Modelo.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicioSesion extends JFrame {
    private final GestionPaciente gestionPaciente;
    private final AdministradorSistema administradorSistema;

    public VentanaInicioSesion() {
        gestionPaciente = new GestionPaciente();
        administradorSistema = new AdministradorSistema();
        initComponents();
    }

    private void initComponents() {
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaIniciarSesion();
            }
        });
        panel.add(btnIniciarSesion);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaRegistrarse();
            }
        });
        panel.add(btnRegistrarse);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(btnSalir);

        add(panel);
    }

    private void mostrarVentanaIniciarSesion() {
        JFrame frameIniciarSesion = new JFrame("Iniciar Sesión");
        frameIniciarSesion.setSize(400, 300);
        frameIniciarSesion.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("RUT:"));
        JTextField txtRut = new JTextField();
        panel.add(txtRut);

        panel.add(new JLabel("Contraseña:"));
        JPasswordField txtContrasena = new JPasswordField();
        panel.add(txtContrasena);

        JButton btnIniciar = new JButton("Iniciar Sesión");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rut = txtRut.getText();
                String contrasena = new String(txtContrasena.getPassword());
                if (administradorSistema.iniciarSesionComoAdmin(rut, contrasena)) {
                    JOptionPane.showMessageDialog(frameIniciarSesion, "Bienvenido Administrador");
                } else {
                    Paciente paciente = gestionPaciente.iniciarSesion(rut, contrasena);
                    if (paciente != null) {
                        JOptionPane.showMessageDialog(frameIniciarSesion, "Bienvenido " + paciente.getNombreCompleto());
                        frameIniciarSesion.dispose();
                        abrirVentanaPaciente(paciente);
                    } else {
                        JOptionPane.showMessageDialog(frameIniciarSesion, "Credenciales incorrectas.");
                    }
                }
            }
        });
        panel.add(btnIniciar);

        frameIniciarSesion.add(panel);
        frameIniciarSesion.setVisible(true);
    }

    private void abrirVentanaPaciente(Paciente paciente) {
        VentanaPaciente ventanaPaciente = new VentanaPaciente(paciente);
        ventanaPaciente.setVisible(true);
    }

    private void mostrarVentanaRegistrarse() {
        JFrame frameRegistrarse = new JFrame("Registrarse");
        frameRegistrarse.setSize(400, 850);
        frameRegistrarse.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre completo:"));
        JTextField txtNombreCompleto = new JTextField();
        panel.add(txtNombreCompleto);

        panel.add(new JLabel("RUT:"));
        JTextField txtRut = new JTextField();
        panel.add(txtRut);

        panel.add(new JLabel("Correo:"));
        JTextField txtCorreo = new JTextField();
        panel.add(txtCorreo);

        panel.add(new JLabel("Edad:"));
        JTextField txtEdad = new JTextField();
        panel.add(txtEdad);

        panel.add(new JLabel("Fecha de nacimiento (dd/MM/yyyy):"));
        JTextField txtFechaNacimiento = new JTextField();
        panel.add(txtFechaNacimiento);

        panel.add(new JLabel("Tipo de sangre:"));
        JTextField txtTipoSangre = new JTextField();
        panel.add(txtTipoSangre);

        panel.add(new JLabel("Peso (kg):"));
        JTextField txtPeso = new JTextField();
        panel.add(txtPeso);

        panel.add(new JLabel("Estado civil:"));
        JTextField txtEstadoCivil = new JTextField();
        panel.add(txtEstadoCivil);

        panel.add(new JLabel("Domicilio:"));
        JTextField txtDomicilio = new JTextField();
        panel.add(txtDomicilio);

        panel.add(new JLabel("Enfermedades (separadas por comas):"));
        JTextField txtEnfermedades = new JTextField();
        panel.add(txtEnfermedades);

        panel.add(new JLabel("Alergias (separadas por comas):"));
        JTextField txtAlergias = new JTextField();
        panel.add(txtAlergias);

        panel.add(new JLabel("Medicamentos (separados por comas):"));
        JTextField txtMedicamentos = new JTextField();
        panel.add(txtMedicamentos);

        panel.add(new JLabel("Cirugías (separadas por comas):"));
        JTextField txtCirugias = new JTextField();
        panel.add(txtCirugias);

        panel.add(new JLabel("Otros detalles:"));
        JTextField txtOtros = new JTextField();
        panel.add(txtOtros);

        panel.add(new JLabel("Contraseña:"));
        JPasswordField txtContrasena = new JPasswordField();
        panel.add(txtContrasena);

        JButton btnRegistrar = new JButton("Registrarse");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCompleto = txtNombreCompleto.getText();
                String rut = txtRut.getText();
                String correo = txtCorreo.getText();
                String edad = txtEdad.getText();
                String fechaNacimiento = txtFechaNacimiento.getText();
                String tipoSangre = txtTipoSangre.getText();
                String peso = txtPeso.getText();
                String estadoCivil = txtEstadoCivil.getText();
                String domicilio = txtDomicilio.getText();
                String enfermedades = txtEnfermedades.getText();
                String alergias = txtAlergias.getText();
                String medicamentos = txtMedicamentos.getText();
                String cirugias = txtCirugias.getText();
                String otros = txtOtros.getText();
                String contrasena = new String(txtContrasena.getPassword());

                Paciente nuevoPaciente = new Paciente(nombreCompleto, rut, correo, edad, fechaNacimiento, tipoSangre, peso, estadoCivil, domicilio, enfermedades, alergias, medicamentos, cirugias, otros, contrasena);
                gestionPaciente.agregarPaciente(nuevoPaciente);

                JOptionPane.showMessageDialog(frameRegistrarse, "Registro completado. Ahora puede iniciar sesión.");
            }
        });
        panel.add(btnRegistrar);

        JButton btnVolver = new JButton( "Volver");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameRegistrarse.dispose();
            }
        });
        panel.add(btnVolver);

        frameRegistrarse.add(panel);
        frameRegistrarse.setVisible(true);
    }
}
