package csv;

import Controlador.GestionPaciente;
import Modelo.Paciente;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CSVGenerator {



    // Método para generar un archivo CSV con Rut y Contraseña para el login
    public static void generateLoginCSV(String filePath, Map<String, String> logins) {
        // Verificar y crear el directorio si no existe
        Path path = Paths.get(filePath);
        Path directory = path.getParent();
        if (directory != null && !Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        // Escribir en el archivo CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Escribir la fila de encabezado solo si el archivo no existe
            if (!Files.exists(path)) {
                writer.write("RUT,Contraseña");
                writer.newLine();
            }

            // Escribir cada entrada de logins en el archivo
            for (Map.Entry<String, String> entry : logins.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para generar un archivo CSV con datos de pacientes
    public static void generatePacientesCSV(String filePath, Map<String, Paciente> pacientes) {
        File file = new File(filePath);
        boolean exists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Escribir la fila de encabezado solo si el archivo no existe
            if (!exists) {
                writer.write("RUT,NombreCompleto,Edad,FechaNacimiento,TipoSangre,Peso,EstadoCivil,Domicilio,Enfermedades,Alergias,Medicamentos,Cirugias,Otros,FichaMedica");
                writer.newLine();
            }

            for (Paciente paciente : pacientes.values()) {
                writer.write(paciente.getRut() + "," + paciente.getNombreCompleto() + "," + paciente.getEdad() + "," + paciente.getFechaNacimiento() + "," +
                        paciente.getTipoSangre() + "," + paciente.getPeso() + "," + paciente.getEstadoCivil() + "," + paciente.getDomicilio() + "," +
                        paciente.getEnfermedades() + "," + paciente.getAlergias() + "," + paciente.getMedicamentos() + "," + paciente.getCirugias() + "," +
                        paciente.getOtros() + "," + paciente.getFichaMedica());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar datos de pacientes desde un archivo CSV
    public static GestionPaciente cargarPacientesDesdeCSV(String filePath) {
        Map<String, Paciente> pacientes = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // saltar la fila de encabezado
                }
                String[] parts = line.split(",");
                if (parts.length >= 14) {
                    Paciente paciente = new Paciente(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11], parts[12], parts[13]);
                    pacientes.put(parts[0], paciente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GestionPaciente(pacientes);
    }
}
