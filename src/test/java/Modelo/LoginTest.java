package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginTest {

    @Test
    public void iniciarSesion_UsuarioExistente_ContrasenaCorrecta_ReturnsUsuario() {
        Login login = new Login();
        Usuario usuario = new Usuario("11111111-1", "password1");

        Usuario resultado = login.iniciarSesion("11111111-1", "password1");

        assertEquals(usuario, resultado);
    }

    @Test
    public void iniciarSesion_UsuarioExistente_ContrasenaIncorrecta_ReturnsNull() {
        Login login = new Login();

        Usuario resultado = login.iniciarSesion("11111111-1", "contrasenaIncorrecta");

        assertNull(resultado);
    }

    @Test
    public void iniciarSesion_UsuarioNoExistente_ReturnsNull() {
        Login login = new Login();

        Usuario resultado = login.iniciarSesion("99999999-9", "password");

        assertNull(resultado);
    }
}
