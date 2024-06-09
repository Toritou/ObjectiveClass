package Controlador;

import Modelo.Paciente;
import Modelo.Usuario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GestionCSV {
    private final String archivoPacientes;
    private final String archivoUsuarios;

    public GestionCSV(String archivoPacientes, String archivoUsuarios) {
        this.archivoPacientes = archivoPacientes;
        this.archivoUsuarios = archivoUsuarios;
    }

    public GestionCSV(String pacientes) {
        this(pacientes, "usuarios.csv");
    }

    // Métodos para gestionar pacientes
    public void guardarPacientes(List<Paciente> pacientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoPacientes))) {
            for (Paciente paciente : pacientes) {
                writer.write(pacienteParaCSV(paciente));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pacientes: " + e.getMessage());
        }
    }

    public List<Paciente> cargarPacientes() {
        asegurarArchivoExistente(archivoPacientes);
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoPacientes))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                pacientes.add(csvParaPaciente(linea));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    // Métodos para gestionar usuarios
    public void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoUsuarios))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuarioParaCSV(usuario));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> cargarUsuarios() {
        asegurarArchivoExistente(archivoUsuarios);
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                usuarios.add(csvParaUsuario(linea));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void agregarUsuario(Usuario usuario) {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }

    public void modificarUsuario(String rut, String nuevaContrasena) {
        List<Usuario> usuarios = cargarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getRut().equals(rut)) {
                usuario.setContrasena(nuevaContrasena);
                break;
            }
        }
        guardarUsuarios(usuarios);
    }

    public void eliminarUsuario(String rut) {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.removeIf(usuario -> usuario.getRut().equals(rut));
        guardarUsuarios(usuarios);
    }

    public List<Usuario> listarUsuarios() {
        return cargarUsuarios();
    }

    // Métodos auxiliares para convertir entre objetos y formato CSV
    private String pacienteParaCSV(Paciente paciente) {
        return paciente.getNombreCompleto() + "," +
                paciente.getRut() + "," +
                paciente.getEdad() + "," +
                paciente.getFechaNacimiento() + "," +
                paciente.getTipoSangre() + "," +
                paciente.getPeso() + "," +
                paciente.getEstadoCivil() + "," +
                paciente.getDomicilio() + "," +
                paciente.getEnfermedades() + "," +
                paciente.getAlergias() + "," +
                paciente.getMedicamentos() + "," +
                paciente.getCirugias() + "," +
                paciente.getOtros() + "," +
                paciente.getFichaMedica();
    }

    private Paciente csvParaPaciente(String linea) {
        String[] partes = linea.split(",");
        return new Paciente(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], partes[6], partes[7], partes[8], partes[9], partes[10], partes[11], partes[12], partes[13]);
    }

    private String usuarioParaCSV(Usuario usuario) {
        return usuario.getRut() + "," + usuario.getContrasena();
    }

    private Usuario csvParaUsuario(String linea) {
        String[] partes = linea.split(",");
        return new Usuario(partes[0], partes[1]);
    }

    // Método auxiliar para asegurar que el archivo exista antes de cargar usuarios o pacientes
    private void asegurarArchivoExistente(String archivo) {
        Path rutaArchivo = Paths.get(archivo);
        if (!Files.exists(rutaArchivo)) {
            try {
                Files.createFile(rutaArchivo);
                System.out.println("Se creó el archivo: " + archivo);
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + archivo);
                e.printStackTrace();
            }
        }
    }

    public Usuario obtenerUsuarioPorRut(String rut) {
        List<Usuario> usuarios = cargarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getRut().equals(rut)) {
                return usuario;
            }
        }
        return null;
    }
}
