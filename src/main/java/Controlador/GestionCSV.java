package Controlador;

import Modelo.Paciente;

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
    private String archivoPacientes;

    public GestionCSV(String pacientes, String archivoPacientes) {
        this.archivoPacientes = archivoPacientes;
    }

    // Método para guardar la información de los pacientes en un archivo CSV
    public void guardarPacientes(List<Paciente> pacientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoPacientes))) {
            for (Paciente paciente : pacientes) {
                writer.write(pacienteParaCSV(paciente));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar la información de los pacientes desde un archivo CSV
    public List<Paciente> cargarPacientes() {
        asegurarArchivoExistente();
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

    // Método auxiliar para convertir un paciente a formato CSV
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
                paciente.getOtros();
    }

    // Método auxiliar para convertir una línea CSV a un paciente
    private Paciente csvParaPaciente(String linea) {
        String[] partes = linea.split(",");
        return new Paciente(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], partes[6], partes[7], partes[8], partes[9], partes[10], partes[11], partes[12]);
    }

    // Método auxiliar para asegurar que el archivo exista antes de cargar pacientes
    private void asegurarArchivoExistente() {
        Path archivo = Paths.get(archivoPacientes);
        if (!Files.exists(archivo)) {
            try {
                Files.createFile(archivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
