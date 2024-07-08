package Controlador;

import Modelo.Pacientes;
import csv.CSVManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdministradorSistema {
    private final List<Pacientes> listaPacientes;
    private final AgendaCitas agendaCitas;

    public AdministradorSistema() {
        listaPacientes = CSVManager.leerPacientes();
        agendaCitas = new AgendaCitas();
        cargarAgenda();
    }

    public void agendarCita(Pacientes paciente, String fechaHora, String descripcion) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date fecha = sdf.parse(fechaHora);
            agendaCitas.agendarCita(paciente, fecha, descripcion);
            guardarAgenda();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void modificarCita(int indice, Pacientes paciente, String fechaHora, String descripcion) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date fecha = sdf.parse(fechaHora);
            agendaCitas.modificarCita(indice, paciente, fecha, descripcion);
            guardarAgenda();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCita(int indice) {
        agendaCitas.eliminarCita(indice);
        guardarAgenda();
    }

    public List<AgendaCitas.Cita> obtenerAgenda() {
        return agendaCitas.obtenerCitas();
    }

    public void agregarPaciente(Pacientes paciente) {
        listaPacientes.add(paciente);
        guardarPacientes();
    }

    public void modificarPaciente(String rut, String nombre, String tipoSangre) {
        for (Pacientes paciente : listaPacientes) {
            if (paciente.getRut().equals(rut)) {
                paciente.setNombre(nombre);
                paciente.setTipoSangre(tipoSangre);
                guardarPacientes();
                break;
            }
        }
    }

    public void eliminarPaciente(String rut) {
        listaPacientes.removeIf(paciente -> paciente.getRut().equals(rut));
        guardarPacientes();
    }

    public List<Pacientes> obtenerListaPacientes() {
        return listaPacientes;
    }

    public void verAgenda() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (AgendaCitas.Cita cita : agendaCitas.obtenerCitas()) {
            System.out.println("Paciente: " + cita.getPaciente().getNombreCompleto());
            System.out.println("Fecha y Hora: " + sdf.format(cita.getFechaHora()));
            System.out.println("Descripción: " + cita.getDescripcion());
            System.out.println("-------------------------");
        }
    }

    private void cargarAgenda() {
        List<String> citas = CSVManager.leerAgenda();
        for (String cita : citas) {
            String[] partes = cita.split(",");
            if (partes.length == 4) {
                String rut = partes[0];
                String fechaHora = partes[1];
                String nombre = partes[2];
                String descripcion = partes[3];
                Pacientes paciente = buscarPaciente(rut);
                if (paciente == null) {
                    paciente = new Pacientes(nombre, rut, "", "");
                    listaPacientes.add(paciente);
                }
                agendarCita(paciente, fechaHora, descripcion);
            }
        }
    }

    private void guardarAgenda() {
        List<String> citas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (AgendaCitas.Cita cita : agendaCitas.obtenerCitas()) {
            String linea = cita.getPaciente().getRut() + "," +
                    sdf.format(cita.getFechaHora()) + "," +
                    cita.getPaciente().getNombreCompleto() + "," +
                    cita.getDescripcion();
            citas.add(linea);
        }
        CSVManager.guardarAgenda(citas);
    }

    private void guardarPacientes() {
        CSVManager.escribirPacientes(listaPacientes);
    }

    public boolean iniciarSesionComoAdmin(String rut, String contrasena) {
        return rut.equals("admin") && contrasena.equals("admin");
    }

    private Pacientes buscarPaciente(String rut) {
        for (Pacientes paciente : listaPacientes) {
            if (paciente.getRut().equals(rut)) {
                return paciente;
            }
        }
        return null;
    }
}
