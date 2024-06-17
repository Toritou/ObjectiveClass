package Launcher;

import java.util.Scanner;

public class MenuAdministrador {
    private Scanner scanner;

    public MenuAdministrador() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("=== Menú Administrador ===");
            System.out.println("1. Opción de administrador 1");
            System.out.println("2. Opción de administrador 2");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume el salto de línea

            switch (opcion) {
                case 1:
                    // Implementar lógica para la opción 1
                    break;
                case 2:
                    // Implementar lógica para la opción 2
                    break;
                case 3:
                    System.out.println("Saliendo del Menú Administrador...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 3);
    }
}
