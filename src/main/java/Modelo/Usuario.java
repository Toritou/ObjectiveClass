package Modelo;

public class Usuario {
    private String rut;
    private String contrasena;

    public Usuario(String rut, String contrasena) {
        try {
            this.rut = rut;
            this.contrasena = contrasena;
        } catch (Exception e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
        }
    }

    public boolean verificarContrasena(String contrasena) {
        try {
            return this.contrasena.equals(contrasena);
        } catch (Exception e) {
            System.err.println("Error al verificar la contraseña: " + e.getMessage());
            return false;
        }
    }

    // Getters
    public String getRut() {
        return rut;
    }

    // Setters
    public void setRut(String rut) {
        this.rut = rut;
    }

}
