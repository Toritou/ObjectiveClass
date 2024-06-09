package csv;

import Modelo.Medico;
import Modelo.Paciente;
import Modelo.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVGenerator {

    private static String safeString(String value) {
        return value == null ? "" : value;
    }

    public static void generatePacientesCSV(String fileName, List<Paciente> pacientes) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Escribir encabezados
            writer.append("Nombre Completo,RUT,Edad,Fecha de Nacimiento,Tipo de Sangre,Peso,Estado Civil,Domicilio,Enfermedades,Alergias,Medicamentos,Cirugias,Otros,Ficha Médica\n");
            // Escribir datos de pacientes
            for (Paciente paciente : pacientes) {
                writer.append(String.join(",",
                        safeString(paciente.getNombreCompleto()),
                        safeString(paciente.getRut()),
                        safeString(paciente.getEdad()),
                        safeString(paciente.getFechaNacimiento()),
                        safeString(paciente.getTipoSangre()),
                        safeString(paciente.getPeso()),
                        safeString(paciente.getEstadoCivil()),
                        safeString(paciente.getDomicilio()),
                        safeString(paciente.getEnfermedades()),
                        safeString(paciente.getAlergias()),
                        safeString(paciente.getMedicamentos()),
                        safeString(paciente.getCirugias()),
                        safeString(paciente.getOtros()),
                        safeString(paciente.getFichaMedica())));
                writer.append("\n");
            }
            System.out.println("CSV de Pacientes generado correctamente: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al generar el CSV de Pacientes: " + e.getMessage());
        }
    }

    public static void generateMedicosCSV(String fileName, List<Medico> medicos) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Escribir encabezados
            writer.append("Nombre,Especialidad,Hospital,RUT,Pacientes\n");
            // Escribir datos de médicos
            for (Medico medico : medicos) {
                writer.append(String.join(",",
                        safeString(medico.getNombreCompleto()),
                        safeString(medico.getEspecialidad()),
                        safeString(medico.getRut()),
                        String.valueOf(medico.getPacientes() != null ? medico.getPacientes().size() : 0)));
                writer.append("\n");
            }
            System.out.println("CSV de Médicos generado correctamente: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al generar el CSV de Médicos: " + e.getMessage());
        }
    }

    public static void generateUsuariosCSV(String fileName, List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Escribir encabezados
            writer.append("RUT,Contraseña\n");

            for (Usuario usuario : usuarios) {
                writer.append(String.join(",",
                        safeString(usuario.getRut()),
                        safeString(usuario.getContrasena())));
                writer.append("\n");
            }
            System.out.println("CSV de Usuarios generado correctamente: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al generar el CSV de Usuarios: " + e.getMessage());
        }
    }
}
