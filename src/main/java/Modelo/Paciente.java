package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario {
    private String nombreCompleto;
    private String rut;
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
    private List<String> horasMedicas; // Lista para almacenar las horas médicas

    // Constructor
    public Paciente(String nombreCompleto, String rut, String edad, String fechaNacimiento, String tipoSangre, String peso, String estadoCivil, String domicilio, String enfermedades, String alergias, String medicamentos, String cirugias, String otros) {
        super(rut, "1234", "Paciente");
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
        this.horasMedicas = new ArrayList<>();
    }

    // Getters
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

    public List<String> getHorasMedicas() {
        return horasMedicas;
    }

    // Setters
    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void setCirugias(String cirugias) {
        this.cirugias = cirugias;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    // Metodos para gestionar horas medicas
    public void agendarHoraMedica(String fechaHora, String medico) {
        String nuevaHora = fechaHora + " - " + medico;
        horasMedicas.add(nuevaHora);
    }

    public void modificarHoraMedica(String fechaHoraActual, String nuevaFechaHora, String nuevoMedico) {
        for (int i = 0; i < horasMedicas.size(); i++) {
            if (horasMedicas.get(i).startsWith(fechaHoraActual)) {
                horasMedicas.set(i, nuevaFechaHora + " - " + nuevoMedico);
                return;
            }
        }
    }

    public void eliminarHoraMedica(String fechaHora) {
        horasMedicas.removeIf(hora -> hora.startsWith(fechaHora));
    }

}
