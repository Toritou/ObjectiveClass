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

    public Paciente(String nombreCompleto, String juanPérez, int i, String fechaNacimiento, String tipoSangre, int i1, String soltero, String domicilio, String ninguna, String ninguna1, String ninguno, String ninguna2, String ninguno1) {
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

    public String getFichaMedica() {
        return fichaMedica;
    }

    // Setters
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
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

    public void setFichaMedica(String fichaMedica) {
        this.fichaMedica = fichaMedica;
    }

    @Override
    public String toString() {
        return "Nombre completo: " + nombreCompleto +
                "\nRUT: " + rut +
                "\nEdad: " + edad +
                "\nFecha de nacimiento: " + fechaNacimiento +
                "\nTipo de sangre: " + tipoSangre +
                "\nPeso: " + peso +
                "\nEstado civil: " + estadoCivil +
                "\nDomicilio: " + domicilio +
                "\nEnfermedades: " + enfermedades +
                "\nAlergias: " + alergias +
                "\nMedicamentos: " + medicamentos +
                "\nCirugías: " + cirugias +
                "\nOtros: " + otros;
    }

    public boolean verificarContrasena(String contrasena) {
        return this.fichaMedica.equals(contrasena);

    }
}