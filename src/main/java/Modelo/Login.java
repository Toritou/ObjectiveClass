package Modelo;

import Controlador.GestionCSV;
import Modelo.Usuario;

import java.util.Scanner;

public class Login {
    private Scanner scanner;
    private GestionCSV gestionCSV;

    public Login() {
        this.scanner = new Scanner(System.in);
        this.gestionCSV = new GestionCSV("pacientes.csv", "usuarios.csv");
    }

    public Usuario iniciarSesion() {
        System.out.print("Ingrese su RUT: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = gestionCSV.obtenerUsuarioPorRut(rut);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            System.out.println("Inicio de sesión exitoso.");
            return usuario;
        } else {
            System.out.println("RUT o contraseña incorrectos.");
            return null;
        }
    }
}
