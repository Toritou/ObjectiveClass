package Modelo;

import Controlador.GestionCSV;

import java.util.Scanner;

public class Login {
    private final Scanner scanner;
    private final GestionCSV gestionCSV;

    public Login() {
        this.scanner = new Scanner(System.in);
        this.gestionCSV = new GestionCSV("pacientes.csv", "usuarios.csv");
    }

    public Usuario iniciarSesion() {
        System.out.print("Ingrese su RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        try {
            Usuario usuario = gestionCSV.obtenerUsuarioPorRut(rut);
            if (usuario != null && usuario.verificarContrasena(contrasena)) {
                System.out.println("Inicio de sesión exitoso.");
                return usuario;
            } else {
                System.out.println("RUT o contraseña incorrectos.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            return null;
        }
    }
}
