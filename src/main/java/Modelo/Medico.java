package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medico extends Usuario {
    private String nombre;
    private String rut;
    private String especialidad;
    private List<Paciente> pacientes;

    public Medico(String nombre, String rut, String especialidad) {
        super(rut, "1234", "Medico");
        this.nombre = nombre;
        this.rut = rut;
        this.especialidad = especialidad;
        this.pacientes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void modificarPaciente(String rut, Paciente pacienteModificado) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getRut().equals(rut)) {
                pacientes.set(i, pacienteModificado);
                return;
            }
        }
    }

    public void eliminarPaciente(String rut) {
        pacientes.removeIf(paciente -> paciente.getRut().equals(rut));
    }

    public List<String> verAgenda() {
        List<String> agenda = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            agenda.addAll(paciente.getHorasMedicas());
        }
        return agenda;
    }
}
