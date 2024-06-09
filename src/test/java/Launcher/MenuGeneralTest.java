package Launcher;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;


public class MenuGeneralTest {

    @Test
    void mostrarMenu_OpcionSalir() {
        // Arrange
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        MenuGeneral menuGeneral = new MenuGeneral(scanner, null, null);

        menuGeneral.mostrarMenu();

    }

    @Test
    void mostrarMenu_OpcionMedico() {
        String input = "1\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        MenuGeneral menuGeneral = new MenuGeneral(scanner, null, null);

        menuGeneral.mostrarMenu();

    }

    @Test
    void mostrarMenu_OpcionPaciente() {

        String input = "2\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        MenuGeneral menuGeneral = new MenuGeneral(scanner, null, null);

    }
}
