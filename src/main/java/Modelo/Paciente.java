package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<Date> citas; // Nueva lista para almacenar las citas del paciente

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
        this.citas = new ArrayList<>(); // Inicialización de la lista de citas
    }

    public Paciente(String adminRut, String adminContrasena) {
        this.rut = adminRut;
        this.fichaMedica = adminContrasena;
        this.citas = new ArrayList<>(); // Inicialización de la lista de citas
    }

    public Paciente(String nombre, String rut, String fechaNacimiento, String tipoSangre) {
        this.nombreCompleto = nombre;
        this.rut = rut;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.fichaMedica = "";
        this.citas = new ArrayList<>(); // Inicialización de la lista de citas
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

    public void setNombre(String nombre) {
        this.nombreCompleto = nombre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public List<Date> getCitas() {
        return citas;
    }

    public void setCitas(List<Date> citas) {
        this.citas = citas;
    }
}
