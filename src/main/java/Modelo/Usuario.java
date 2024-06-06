package Modelo;

public class Usuario {
    private String rut;
    private String contrasena;
    private String rol;

public Usuario(String rut, String contrasena, String rol){
        this.rut = rut;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters
    public String getRut() {
        return rut;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRol() {
        return rol;
    }

    // Setters
    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(String rol) {
        if (rol.equals("Paciente") || rol.equals("Medico")) {
            this.rol = rol;
        } else {
            throw new IllegalArgumentException("Rol inválido. Debe ser 'paciente' o 'medico'.");
        }
    }
}
