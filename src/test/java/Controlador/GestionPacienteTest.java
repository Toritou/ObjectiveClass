package Controlador;

import Modelo.Paciente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GestionPacienteTest {

    @Test
    void testIniciarSesionPacienteExistente() {
        GestionPaciente gestion = new GestionPaciente();

        Paciente paciente = new Paciente("Juan Perez", "12345678-9", "correo@ejemplo.com", "30", "01/01/1990", "A+", "70kg", "Soltero", "Domicilio", "Ninguna", "Ninguna", "Ninguno", "Ninguna", "Ninguno", "Ficha");
        gestion.agregarPaciente(paciente);

    }

    @Test
    void testIniciarSesionPacienteNoExistente() {
        GestionPaciente gestion = new GestionPaciente();
        Paciente pacienteIniciado = gestion.iniciarSesion("123456789", "contrasena_incorrecta");
        assertNull(pacienteIniciado);
    }
}
