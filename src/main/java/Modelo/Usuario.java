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

    public String getRut() {
        return rut;
    }

    public String toString() {
        return rut;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario usuario = (Usuario) obj;
            return rut.equals(usuario.getRut());
        }
        return false;
    }

    public int hashCode() {
        return rut.hashCode();
    }

}
