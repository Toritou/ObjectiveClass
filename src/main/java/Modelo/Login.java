package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    private final Scanner scanner;
    private final List<Usuario> usuarios;

    public Login() {
        this.scanner = new Scanner(System.in);
        this.usuarios = new ArrayList<>();

    }

    public Usuario iniciarSesion(String s, String password1) {
        System.out.print("Ingrese su RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = obtenerUsuarioPorRut(rut);
        if (usuario != null && usuario.verificarContrasena(contrasena)) {
            System.out.println("Inicio de sesión exitoso.");
            return usuario;
        } else {
            System.out.println("RUT o contraseña incorrectos.");
            return null;
        }
    }

    private Usuario obtenerUsuarioPorRut(String rut) {
        for (Usuario usuario : usuarios) {
            if (usuario.getRut().equals(rut)) {
                return usuario;
            }
        }
        return null;
    }
}