package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paciente {
    private String nombreCompleto;
    private final String rut;
    private String correo;
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

    public Paciente(String nombreCompleto, String rut, String Correo ,String edad, String fechaNacimiento, String tipoSangre, String peso, String estadoCivil, String domicilio, String enfermedades, String alergias, String medicamentos, String cirugias, String otros, String fichaMedica) {
        this.nombreCompleto = nombreCompleto;
        this.rut = rut;
        this.correo = Correo;
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
        this.citas = new ArrayList<>();
    }

    public Paciente(String adminRut, String adminContrasena) {
        this.rut = adminRut;
        this.fichaMedica = adminContrasena;
        this.citas = new ArrayList<>();
    }

    public Paciente(String nombre, String rut, String fechaNacimiento, String tipoSangre) {
        this.nombreCompleto = nombre;
        this.rut = rut;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.fichaMedica = "";
        this.citas = new ArrayList<>();
    }

    // Getters y Setters

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getRut() {
        return rut;
    }

    public String getCorreo() {
        return correo;
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

    public void setCorreo(String correo) {
        this.correo = correo;
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

    @Override
    public String toString() {
        return "Paciente{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", rut='" + rut + '\'' +
                ", correo='" + correo + '\'' +
                ", edad='" + edad + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", tipoSangre='" + tipoSangre + '\'' +
                ", peso='" + peso + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", enfermedades='" + enfermedades + '\'' +
                ", alergias='" + alergias + '\'' +
                ", medicamentos='" + medicamentos + '\'' +
                ", cirugias='" + cirugias + '\'' +
                ", otros='" + otros + '\'' +
                ", fichaMedica='" + fichaMedica + '\'' +
                ", citas=" + citas +
                '}';
    }
}
