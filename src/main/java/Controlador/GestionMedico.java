package Controlador;

import Modelo.Paciente;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GestionMedico {
    private GestionCSV gestionCSV;

    public GestionMedico(String archivoPacientes) {
        this.gestionCSV = new GestionCSV("pacientes.csv", archivoPacientes);
    }

    // Modificar paciente
    public void modificarPaciente(String rut) {
        List<Paciente> pacientes = gestionCSV.cargarPacientes();
        Optional<Paciente> pacienteOptional = pacientes.stream().filter(p -> p.getRut().equals(rut)).findFirst();
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Modificar datos del paciente (dejar en blanco para no modificar):");

            System.out.print("Nombre completo [" + paciente.getNombreCompleto() + "]: ");
            String nombreCompleto = scanner.nextLine();
            if (!nombreCompleto.isEmpty()) {
                paciente.setNombreCompleto(nombreCompleto);
            }

            // Similar for other fields
            System.out.print("Edad [" + paciente.getEdad() + "]: ");
            String edad = scanner.nextLine();
            if (!edad.isEmpty()) {
                paciente.setEdad(edad);
            }

            System.out.print("Fecha de nacimiento [" + paciente.getFechaNacimiento() + "]: ");
            String fechaNacimiento = scanner.nextLine();
            if (!fechaNacimiento.isEmpty()) {
                paciente.setFechaNacimiento(fechaNacimiento);
            }

            System.out.print("Tipo de sangre [" + paciente.getTipoSangre() + "]: ");
            String tipoSangre = scanner.nextLine();
            if (!tipoSangre.isEmpty()) {
                paciente.setTipoSangre(tipoSangre);
            }

            System.out.print("Peso [" + paciente.getPeso() + "]: ");
            String peso = scanner.nextLine();
            if (!peso.isEmpty()) {
                paciente.setPeso(peso);
            }

            System.out.print("Estado civil [" + paciente.getEstadoCivil() + "]: ");
            String estadoCivil = scanner.nextLine();
            if (!estadoCivil.isEmpty()) {
                paciente.setEstadoCivil(estadoCivil);
            }

            System.out.print("Domicilio [" + paciente.getDomicilio() + "]: ");
            String domicilio = scanner.nextLine();
            if (!domicilio.isEmpty()) {
                paciente.setDomicilio(domicilio);
            }

            System.out.print("Enfermedades [" + paciente.getEnfermedades() + "]: ");
            String enfermedades = scanner.nextLine();
            if (!enfermedades.isEmpty()) {
                paciente.setEnfermedades(enfermedades);
            }

            System.out.print("Alergias [" + paciente.getAlergias() + "]: ");
            String alergias = scanner.nextLine();
            if (!alergias.isEmpty()) {
                paciente.setAlergias(alergias);
            }

            System.out.print("Medicamentos [" + paciente.getMedicamentos() + "]: ");
            String medicamentos = scanner.nextLine();
            if (!medicamentos.isEmpty()) {
                paciente.setMedicamentos(medicamentos);
            }

            System.out.print("Cirugías [" + paciente.getCirugias() + "]: ");
            String cirugias = scanner.nextLine();
            if (!cirugias.isEmpty()) {
                paciente.setCirugias(cirugias);
            }

            System.out.print("Otros [" + paciente.getOtros() + "]: ");
            String otros = scanner.nextLine();
            if (!otros.isEmpty()) {
                paciente.setOtros(otros);
            }

            gestionCSV.guardarPacientes(pacientes);
            System.out.println("Paciente modificado correctamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    // Eliminar paciente
    public void eliminarPaciente(String rut) {
        List<Paciente> pacientes = gestionCSV.cargarPacientes();
        boolean removed = pacientes.removeIf(p -> p.getRut().equals(rut));
        if (removed) {
            gestionCSV.guardarPacientes(pacientes);
            System.out.println("Paciente eliminado correctamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    // Agregar paciente
    public void agregarPaciente(Paciente paciente) {
        List<Paciente> pacientes = gestionCSV.cargarPacientes();
        pacientes.add(paciente);
        gestionCSV.guardarPacientes(pacientes);
        System.out.println("Paciente agregado correctamente.");
    }

    // Ver paciente
    public void verPaciente(String rut) {
        List<Paciente> pacientes = gestionCSV.cargarPacientes();
        Optional<Paciente> pacienteOptional = pacientes.stream().filter(p -> p.getRut().equals(rut)).findFirst();
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            System.out.println(paciente);
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    // Ver lista de pacientes
    public void verListaDePacientes() {
        List<Paciente> pacientes = gestionCSV.cargarPacientes();
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }
}
