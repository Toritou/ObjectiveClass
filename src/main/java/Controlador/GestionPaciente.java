package Controlador;

import Modelo.Paciente;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GestionPaciente {
    private Map<String, Paciente> pacientes;

    public GestionPaciente(Map<String, Paciente> pacientes) {
        this.pacientes = new HashMap<>();
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.put(paciente.getRut(), paciente);
    }

    public Paciente obtenerPacientePorRut(String rut) {
        return pacientes.get(rut);
    }

    public void eliminarPaciente(String rut) {
        pacientes.remove(rut);
    }

    public Map<String, Paciente> getPacientes() {
        return pacientes;
    }

    public Paciente iniciarSesion(String rut, String contrasena) {
        Paciente paciente = pacientes.get(rut);
        if (paciente != null && paciente.verificarContrasena(contrasena)) {
            return paciente;
        }
        return null;
    }

    public void modificarInformacionPersonal(Paciente pacienteActual) {
        Paciente paciente = pacientes.get(pacienteActual.getRut());
        if (paciente != null) {
            paciente.setNombreCompleto(pacienteActual.getNombreCompleto());
            paciente.setEdad(pacienteActual.getEdad());
            paciente.setFechaNacimiento(pacienteActual.getFechaNacimiento());
            paciente.setTipoSangre(pacienteActual.getTipoSangre());
            paciente.setPeso(pacienteActual.getPeso());
            paciente.setEstadoCivil(pacienteActual.getEstadoCivil());
            paciente.setDomicilio(pacienteActual.getDomicilio());
            paciente.setEnfermedades(pacienteActual.getEnfermedades());
            paciente.setAlergias(pacienteActual.getAlergias());
            paciente.setMedicamentos(pacienteActual.getMedicamentos());
            paciente.setCirugias(pacienteActual.getCirugias());
            paciente.setOtros(pacienteActual.getOtros());
            System.out.println("Información personal actualizada exitosamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    public void modificarFichaMedica(Paciente pacienteActual) {
        Paciente paciente = pacientes.get(pacienteActual.getRut());
        if (paciente != null) {
            paciente.setFichaMedica(pacienteActual.getFichaMedica());
            System.out.println("Ficha médica actualizada exitosamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    public void agendarCita(Paciente pacienteActual, Date selectedDate) {
        System.out.println("Cita agendada para " + pacienteActual.getNombreCompleto() + " el " + selectedDate);
        // Aquí puedes implementar la lógica adicional para gestionar la agenda del médico.
    }
}
