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

    // Getters y Setters omitidos por brevedad
}
