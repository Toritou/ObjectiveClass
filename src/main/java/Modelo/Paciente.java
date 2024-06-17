package Modelo;

public class Paciente {
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
    private String fichaMedica;

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

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getCirugias() {
        return cirugias;
    }

    public void setCirugias(String cirugias) {
        this.cirugias = cirugias;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getFichaMedica() {
        return fichaMedica;
    }

    public void setFichaMedica(String fichaMedica) {
        this.fichaMedica = fichaMedica;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", rut='" + rut + '\'' +
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
                '}';
    }

    public boolean verificarContrasena(String contrasena) {
        return this.fichaMedica.equals(contrasena);
    }

    public CharSequence getContrasena() {
        return this.fichaMedica;
    }

    public void setContrasena(String contrasena) {
        this.fichaMedica = contrasena;
    }
}
