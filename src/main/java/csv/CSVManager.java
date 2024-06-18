package csv;

import Modelo.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    private static final String PACIENTES_CSV = "pacientes.csv";
    private static final String AGENDA_CSV = "agenda.csv";
    private static final String DELIMITER = ",";

    private static List<Paciente> cargarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        File file = new File(PACIENTES_CSV);
        if (!file.exists()) {
            return pacientes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(PACIENTES_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(DELIMITER);
                if (campos.length == 14) { // Asumiendo que hay 14 campos en cada línea del CSV
                    Paciente paciente = new Paciente(
                            campos[0], campos[1], campos[2], campos[3],
                            campos[4], campos[5], campos[6], campos[7],
                            campos[8], campos[9], campos[10], campos[11],
                            campos[12], campos[13]
                    );
                    pacientes.add(paciente);
                } else {
                    System.out.println("Error: formato de línea incorrecto en el archivo CSV");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo CSV no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return pacientes;
    }

    public static void escribirPacientes(List<Paciente> pacientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PACIENTES_CSV))) {
            for (Paciente paciente : pacientes) {
                writer.println(
                        paciente.getNombreCompleto() + DELIMITER +
                                paciente.getRut() + DELIMITER +
                                paciente.getEdad() + DELIMITER +
                                paciente.getFechaNacimiento() + DELIMITER +
                                paciente.getTipoSangre() + DELIMITER +
                                paciente.getPeso() + DELIMITER +
                                paciente.getEstadoCivil() + DELIMITER +
                                paciente.getDomicilio() + DELIMITER +
                                paciente.getEnfermedades() + DELIMITER +
                                paciente.getAlergias() + DELIMITER +
                                paciente.getMedicamentos() + DELIMITER +
                                paciente.getCirugias() + DELIMITER +
                                paciente.getOtros() + DELIMITER +
                                paciente.getFichaMedica()
                );
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    public static void guardarAgenda(List<String> citas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AGENDA_CSV, true))) {
            for (String cita : citas) {
                writer.println(cita);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV de agenda: " + e.getMessage());
        }
    }

    public static List<String> leerAgenda() {
        List<String> citas = new ArrayList<>();
        File file = new File(AGENDA_CSV);
        if (!file.exists()) {
            return citas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(AGENDA_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                citas.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo CSV de agenda no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV de agenda: " + e.getMessage());
        }
        return citas;
    }

    public static List<Paciente> getPacientes() {
        return cargarPacientes();
    }

    public static List<Paciente> leerPacientes() {
        return cargarPacientes();
    }
}
