package Controlador;

import Modelo.Medico;
import Modelo.Paciente;
import csv.CSVGenerator;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GestionPaciente {
    private final Medico medico;

    public GestionPaciente(Medico medico) {
        this.medico = medico;
    }

    public void agregarPaciente(Paciente nuevoPaciente) {
        medico.agregarPaciente(nuevoPaciente);
        List<Paciente> pacientesActualizados = medico.getPacientes();
        CSVGenerator.generatePacientesCSV("pacientes.csv", pacientesActualizados);
        System.out.println("Paciente agregado correctamente.");
    }

    public Paciente iniciarSesion(String rut, String contrasena) {
        List<Paciente> pacientes = medico.getPacientes();
        for (Paciente paciente : pacientes) {
            if (paciente.getRut().equals(rut) && paciente.verificarContrasena(contrasena)) {
                return paciente;
            }
        }
        return null;
    }

    public void modificarInformacionPersonal(Paciente pacienteActual) {
        System.out.println("Modificar datos personales (dejar en blanco para no modificar):");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre completo [" + pacienteActual.getNombreCompleto() + "]: ");
        String nombreCompleto = scanner.nextLine();
        if (!nombreCompleto.isEmpty()) {
            pacienteActual.setNombreCompleto(nombreCompleto);
        }

        System.out.print("Edad [" + pacienteActual.getEdad() + "]: ");
        String edad = scanner.nextLine();
        if (!edad.isEmpty()) {
            pacienteActual.setEdad(edad);
        }

        System.out.print("Fecha de nacimiento [" + pacienteActual.getFechaNacimiento() + "]: ");
        String fechaNacimiento = scanner.nextLine();
        if (!fechaNacimiento.isEmpty()) {
            pacienteActual.setFechaNacimiento(fechaNacimiento);
        }

        System.out.print("Tipo de sangre [" + pacienteActual.getTipoSangre() + "]: ");
        String tipoSangre = scanner.nextLine();
        if (!tipoSangre.isEmpty()) {
            pacienteActual.setTipoSangre(tipoSangre);
        }

        System.out.print("Peso [" + pacienteActual.getPeso() + "]: ");
        String peso = scanner.nextLine();
        if (!peso.isEmpty()) {
            pacienteActual.setPeso(peso);
        }

        System.out.print("Estado civil [" + pacienteActual.getEstadoCivil() + "]: ");
        String estadoCivil = scanner.nextLine();
        if (!estadoCivil.isEmpty()) {
            pacienteActual.setEstadoCivil(estadoCivil);
        }
    }

    public void modificarFichaMedica(Paciente paciente) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la nueva información para la ficha médica:");
        String nuevaInformacion = scanner.nextLine();

        paciente.setFichaMedica(nuevaInformacion);
        medico.modificarFichaPaciente(paciente.getRut(), nuevaInformacion);
        System.out.println("Ficha médica modificada correctamente.");
    }

    public void agendarCita(Paciente paciente, Date fechaHora) {
        // Implementa la lógica para guardar la cita
        System.out.println("Cita agendada para el paciente " + paciente.getNombreCompleto() +
                " en la fecha y hora: " + fechaHora);

        // Aquí puedes añadir la lógica para guardar la cita en la base de datos, archivo, etc.
    }
}
