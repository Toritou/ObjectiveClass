package csv;

import Modelo.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    private static final String PACIENTES_CSV = "pacientes.csv";
    private static final String AGENDA_CSV = "agenda.csv";
    public static final String DELIMITER = ",";


    public static List<Paciente> leerPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PACIENTES_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(DELIMITER);
                if (datos.length >= 14) {
                    Paciente paciente = new Paciente(datos[0], datos[1], datos[2], datos[3],
                            datos[4], datos[5], datos[6], datos[7], datos[8],
                            datos[9], datos[10], datos[11], datos[12], datos[13]);
                    pacientes.add(paciente);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV de pacientes: " + e.getMessage());
        }
        return pacientes;
    }

    public static void escribirPacientes(List<Paciente> pacientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PACIENTES_CSV))) {
            for (Paciente paciente : pacientes) {
                String linea = paciente.getNombreCompleto() + DELIMITER +
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
                        paciente.getFichaMedica();
                writer.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV de pacientes: " + e.getMessage());
        }
    }

    public static List<String> leerAgenda() {
        List<String> citas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AGENDA_CSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                citas.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV de agenda: " + e.getMessage());
        }
        return citas;
    }

    public static void guardarAgenda(List<String> citas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AGENDA_CSV))) {
            for (String cita : citas) {
                writer.println(cita);
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV de agenda: " + e.getMessage());
        }
    }
}
