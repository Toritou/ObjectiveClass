package Modelo;

import java.util.Date;

public class Paciente {
    private String nombreCompleto;
    private final String rut;
    private String edad;
    private String fechaNacimiento;
    private String tipoSangre;
    private String peso;
    private String estadoCivil;
    private String domicilio;
    private String enfermedades;
    private String alergias;
    private String medicamentos;
    private String cirugias;
    private String otros;
    private final String fichaMedica;

    public Paciente(String nombreCompleto, String rut, String edad, String fechaNacimiento, String tipoSangre, String peso, String estadoCivil, String domicilio, String enfermedades, String alergias, String medicamentos, String cirugias, String otros, String fichaMedica) {
        this.nombreCompleto = nombreCompleto;
        this.rut = rut;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.peso = peso;
        this.estadoCivil = estadoCivil;
        this.domicilio = domicilio;
        this.enfermedades = enfermedades;
        this.alergias = alergias;
        this.medicamentos = medicamentos;
        this.cirugias = cirugias;
        this.otros = otros;
        this.fichaMedica = fichaMedica;
    }

    public Paciente(String adminRut, String adminContrasena) {
        this.rut = adminRut;
        this.fichaMedica = adminContrasena;
    }


    // Getters y Setters

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getRut() {
        return rut;
    }

    public String getEdad() {
        return edad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getPeso() {
        return peso;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public String getCirugias() {
        return cirugias;
    }

    public String getOtros() {
        return otros;
    }

    public String getFichaMedica() {
        return fichaMedica;
    }

    public boolean verificarContrasena(String contrasena) {
        return this.fichaMedica.equals(contrasena);
    }

    public void agendarCita(Date selectedDate) {
        System.out.println("Cita agendada para el paciente " + this.nombreCompleto);
    }
}
