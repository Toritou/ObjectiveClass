package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendaCitas {
    private List<Cita> citas;

    public AgendaCitas() {
        this.citas = new ArrayList<>();
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void agregarCita(Cita cita) {
        citas.add(cita);
    }

    public void modificarCita(int indice, Cita nuevaCita) {
        if (indice >= 0 && indice < citas.size()) {
            citas.set(indice, nuevaCita);
        } else {
            System.out.println("Índice de cita fuera de rango.");
        }
    }

    public void eliminarCita(int indice) {
        if (indice >= 0 && indice < citas.size()) {
            citas.remove(indice);
        } else {
            System.out.println("Índice de cita fuera de rango.");
        }
    }

    public void verAgenda() {
        System.out.println("=== Agenda de Citas ===");
        for (int i = 0; i < citas.size(); i++) {
            System.out.println((i + 1) + ". " + citas.get(i));
        }
    }

    public void agendarCita(Paciente paciente, Date fecha, String descripcion) {
        Cita cita = new Cita(paciente, fecha, descripcion);
        citas.add(cita);
    }

    public void modificarCita(int indice, Paciente paciente, Date fecha, String descripcion) {
        if (indice >= 0 && indice < citas.size()) {
            Cita cita = new Cita(paciente, fecha, descripcion);
            citas.set(indice, cita);
        } else {
            System.out.println("Índice de cita fuera de rango.");
        }
    }

    public List<Cita> obtenerCitas() {
        return citas;
    }

    public static class Cita {
        private Paciente paciente;
        private Date fechaHora;
        private String descripcion;

        public Cita(Paciente paciente, Date fechaHora, String descripcion) {
            this.paciente = paciente;
            this.fechaHora = fechaHora;
            this.descripcion = descripcion;
        }

        public Paciente getPaciente() {
            return paciente;
        }

        public void setPaciente(Paciente paciente) {
            this.paciente = paciente;
        }

        public Date getFechaHora() {
            return fechaHora;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
