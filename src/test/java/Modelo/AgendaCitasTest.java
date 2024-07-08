package Modelo;

import Controlador.AgendaCitas;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class AgendaCitasTest {

    @Test
    void testEliminarCitaValidIndex() {
        AgendaCitas agenda = new AgendaCitas();
        Pacientes paciente = new Pacientes("Juan Perez", "12345678-9", "correo@ejemplo.com", "30", "01/01/1990", "A+", "70kg", "Soltero", "Domicilio", "Ninguna", "Ninguna", "Ninguno", "Ninguna", "Ninguno", "Ficha");
        agenda.agendarCita(paciente, new Date(), "Cita de prueba");
        assertEquals(1, agenda.obtenerCitas().size());

        agenda.eliminarCita(0);
        assertEquals(0, agenda.obtenerCitas().size());
    }

    @Test
    void testEliminarCitaInvalidIndex() {
        AgendaCitas agenda = new AgendaCitas();
        agenda.eliminarCita(0);
    }
}
