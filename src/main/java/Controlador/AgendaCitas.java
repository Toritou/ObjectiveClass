package Controlador;

import Modelo.Pacientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendaCitas {
    private final List<Cita> citas;

    public AgendaCitas() {
        this.citas = new ArrayList<>();
    }


    public void eliminarCita(int indice) {
        if (indice >= 0 && indice < citas.size()) {
            citas.remove(indice);
        } else {
            System.out.println("Índice de cita fuera de rango.");
        }
    }

    public void agendarCita(Pacientes paciente, Date fecha, String descripcion) {
        Cita cita = new Cita(paciente, fecha, descripcion);
        citas.add(cita);
    }

    public void modificarCita(int indice, Pacientes paciente, Date fecha, String descripcion) {
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
        private Pacientes paciente;
        private final Date fechaHora;
        private final String descripcion;

        public Cita(Pacientes paciente, Date fechaHora, String descripcion) {
            this.paciente = paciente;
            this.fechaHora = fechaHora;
            this.descripcion = descripcion;
        }

        public Pacientes getPaciente() {
            return paciente;
        }

        public void setPaciente(Pacientes paciente) {
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
