package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PacienteTest {

    @Test
    public void verificarContrasena_Correcta_ReturnTrue() {
        // Arrange
        Paciente paciente = new Paciente("Nombre", "12345678-9", "20", "01/01/2000", "O+", "70", "Soltero",
                "Dirección", "", "", "", "", "", "password123");

        // Act
        boolean resultado = paciente.verificarContrasena("password123");

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void verificarContrasena_Incorrecta_ReturnFalse() {
        // Arrange
        Paciente paciente = new Paciente("Nombre", "12345678-9", "20", "01/01/2000", "O+", "70", "Soltero",
                "Dirección", "", "", "", "", "", "password123");

        // Act
        boolean resultado = paciente.verificarContrasena("contrasenaIncorrecta");

        // Assert
        assertFalse(resultado);
    }

    @Test
    public void verificarContrasena_ContrasenaNula_ReturnFalse() {
        // Arrange
        Paciente paciente = new Paciente("Nombre", "12345678-9", "20", "01/01/2000", "O+", "70", "Soltero",
                "Dirección", "", "", "", "", "", "password123");

        // Act
        boolean resultado = paciente.verificarContrasena(null);

        // Assert
        assertFalse(resultado);
    }
}
