package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void verificarContrasena_Correcta_ReturnTrue() {
        // Arrange
        Usuario usuario = new Usuario("12345678-9", "password123");

        // Act
        boolean resultado = usuario.verificarContrasena("password123");

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void verificarContrasena_Incorrecta_ReturnFalse() {
        // Arrange
        Usuario usuario = new Usuario("12345678-9", "password123");

        // Act
        boolean resultado = usuario.verificarContrasena("contrasenaIncorrecta");

        // Assert
        assertFalse(resultado);
    }

    @Test
    public void verificarContrasena_ContrasenaNula_ReturnFalse() {
        // Arrange
        Usuario usuario = new Usuario("12345678-9", "password123");

        // Act
        boolean resultado = usuario.verificarContrasena(null);

        // Assert
        assertFalse(resultado);
    }
}
