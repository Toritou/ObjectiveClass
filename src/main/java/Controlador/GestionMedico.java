package Controlador;

import Modelo.Medico;
import Modelo.Paciente;
import java.util.HashMap;
import java.util.Map;

public class GestionMedico {
    private Map<String, Medico> medicos;

    public GestionMedico(HashMap<String, Medico> medicos) {
        this.medicos = medicos;
    }

    public void agregarMedico(Medico medico) {
        medicos.put(medico.getRut(), medico);
    }

    public Medico obtenerMedicoPorRut(String rut) {
        return medicos.get(rut);
    }

    public void eliminarMedico(String rut) {
        medicos.remove(rut);
    }

    public Map<String, Paciente> getPacientes() {
        Map<String, Paciente> pacientes = new HashMap<>();
        for (Medico medico : medicos.values()) {
            pacientes.putAll(medico.getPacientes());
        }
        return pacientes;
    }

    public Paciente obtenerPacientePorRut(String rut) {
        for (Medico medico : medicos.values()) {
            Paciente paciente = medico.obtenerPacientePorRut(rut);
            if (paciente != null) {
                return paciente;
            }
        }
        return null;
    }

    public void agregarPaciente(Paciente paciente) {
        // Asumiendo que el médico se selecciona a partir de la información proporcionada
        // Aquí se debe agregar el paciente al médico correspondiente
        String rutMedico = paciente.getRut(); // Ajustar según cómo se determine el médico
        Medico medico = medicos.get(rutMedico);
        if (medico != null) {
            medico.agregarPaciente(paciente);
        } else {
            System.out.println("Médico no encontrado. No se puede agregar el paciente.");
        }
    }

    public void actualizarPaciente(String rut, Paciente pacienteActualizado) {
        for (Medico medico : medicos.values()) {
            Paciente paciente = medico.obtenerPacientePorRut(rut);
            if (paciente != null) {
                medico.modificarFichaPaciente(rut, pacienteActualizado.getFichaMedica());
                return;
            }
        }
        System.out.println("Paciente no encontrado.");
    }

    public void eliminarPaciente(String rut) {
        for (Medico medico : medicos.values()) {
            Paciente paciente = medico.obtenerPacientePorRut(rut);
            if (paciente != null) {
                medico.getPacientes().remove(rut);
                return;
            }
        }
        System.out.println("Paciente no encontrado.");
    }

    public Map<String, Medico> getMedicos() {
        return medicos;
    }
}
