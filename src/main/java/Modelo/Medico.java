package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Medico extends Paciente {
    private final String especialidad;
    private List<Paciente> pacientes;

    public Medico(String rut, String nombre, String especialidad, String hospital) {
        super(rut, nombre, "", "", "", "", "", "", "", "", "", "", "", "");
        this.especialidad = especialidad;
        this.pacientes = new ArrayList<>();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Map<? extends String, ? extends Paciente> getPacientes() {
        return (Map<? extends String, ? extends Paciente>) pacientes;
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public Paciente obtenerPacientePorRut(String rut) {
        for (Paciente paciente : pacientes) {
            if (paciente.getRut().equals(rut)) {
                return paciente;
            }
        }
        return null;
    }

    public void modificarFichaPaciente(String rut, String nuevaInformacion) {
        for (Paciente paciente : pacientes) {
            if (paciente.getRut().equals(rut)) {
                paciente.setFichaMedica(nuevaInformacion);
                return;
            }
        }
        System.out.println("Paciente no encontrado.");
    }

    @Override
    public String toString() {
        return super.toString() + ", Especialidad: " + especialidad + ", Pacientes: " + pacientes.size();
    }

    public char getNombre() {
        return 0;
    }
}
