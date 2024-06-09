package Modelo;

public class Usuario {
    private String rut;
    private String contrasena;

    public Usuario(String rut, String contrasena) {
        this.rut = rut;
        this.contrasena = contrasena;
    }

    public boolean verificarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }

    // Getters
    public String getRut() {
        return rut;
    }

    public String getContrasena() {
        return contrasena;
    }

    // Setters
    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
