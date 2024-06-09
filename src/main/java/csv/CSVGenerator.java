package csv;

import Controlador.GestionMedico;
import Controlador.GestionPaciente;
import Modelo.Medico;
import Modelo.Paciente;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CSVGenerator {

    public static void generateMedicosCSV(String filePath, Map<String, Medico> medicos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Medico medico : medicos.values()) {
                writer.write(medico.getRut() + "," + medico.getNombre() + "," + medico.getEspecialidad());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generatePacientesCSV(String filePath, Map<String, Paciente> pacientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
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

    public static GestionMedico cargarMedicosDesdeCSV(String filePath) {
        Map<String, Medico> medicos = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Medico medico = new Medico(parts[0], parts[1], parts[2], parts[3]);
                    medicos.put(parts[0], medico);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GestionMedico((HashMap<String, Medico>) medicos);
    }

    public static GestionPaciente cargarPacientesDesdeCSV(String filePath) {
        Map<String, Paciente> pacientes = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
