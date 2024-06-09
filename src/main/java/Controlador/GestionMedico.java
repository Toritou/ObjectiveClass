package Controlador;

import Modelo.Medico;
import Modelo.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GestionMedico {
    private final List<Paciente> pacientes;
    private final List<Medico> medicos;

    public GestionMedico() {
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        // Agregar pacientes manualmente para propósitos de prueba
        // Puedes reemplazar estos valores con datos reales
        pacientes.add(new Paciente("11111111-1", "Juan Pérez", 30, "01/01/1993", "A+", 70, "Soltero", "Calle 123", "Ninguna", "Ninguna", "Ninguno", "Ninguna", "Ninguno"));
        pacientes.add(new Paciente("22222222-2", "María López", 25, "02/02/1998", "O-", 60, "Casada", "Calle 456", "Asma", "Polen", "Ventolin", "Apéndice", "Ninguno"));
        // Agregar médicos manualmente para propósitos de prueba
        // Puedes reemplazar estos valores con datos reales
        medicos.add(new Medico("1234", "Dr. Juan Perez", "Medicina General", "Hospital ABC"));
    }

    // Modificar paciente
    public void modificarPaciente(String rut) {
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

            System.out.print("Edad [" + paciente.getEdad() + "]: ");
            String edad = scanner.nextLine();
            if (!edad.isEmpty()) {
                paciente.setEdad(String.valueOf(Integer.parseInt(edad)));
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
                paciente.setPeso(String.valueOf(Double.parseDouble(peso)));
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

            System.out.println("Paciente modificado correctamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    // Eliminar paciente
    public void eliminarPaciente(String rut) {
        boolean removed = pacientes.removeIf(p -> p.getRut().equals(rut));
        if (removed) {
            System.out.println("Paciente eliminado correctamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    // Ver paciente
    public void verPaciente(String rut) {
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
        for (Paciente paciente : pacientes)  {
            System.out.println(paciente);
        }
    }

    public void modificarFichaPaciente(String rut, String nuevaInformacion) {
        for (Paciente paciente : pacientes) {
            if (paciente.getRut().equals(rut)) {
                paciente.setFichaMedica(nuevaInformacion);
                break;
            }
        }
    }

    // Agregar paciente (Medico) a la lista
    public void agregarPaciente(Medico paciente) {
        pacientes.add(paciente);
        System.out.println("Paciente agregado correctamente.");
    }

    // Obtener médico por su RUT
    public Medico obtenerMedicoPorRut(String rut) {
        for (Paciente paciente : pacientes) {
            if (paciente instanceof Medico && paciente.getRut().equals(rut)) {
                return (Medico) paciente;
            }
        }
        return null; // Manejar el caso en que el usuario no sea un médico
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
}
