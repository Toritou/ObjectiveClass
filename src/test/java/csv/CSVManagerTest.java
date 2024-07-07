package csv;

import Modelo.Paciente;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CSVManagerTest {

    @Test
    void testLeerPacientes() {
        List<Paciente> pacientes = CSVManager.leerPacientes();
        assertNotNull(pacientes);
        assertFalse(pacientes.isEmpty());
    }

    @Test
    void testEscribirPacientes() {
        List<Paciente> pacientes = CSVManager.leerPacientes();
        Paciente nuevoPaciente = new Paciente("Nuevo Paciente", "87654321-0", "nuevo@correo.com", "25", "02/02/1999", "B+", "65kg", "Casado", "Nuevo Domicilio", "Ninguna", "Ninguna", "Ninguno", "Ninguna", "Ninguno", "Nueva Ficha");
        pacientes.add(nuevoPaciente);
        CSVManager.escribirPacientes(pacientes);

        List<Paciente> pacientesLeidos = CSVManager.leerPacientes();
        assertTrue(pacientesLeidos.stream().anyMatch(p -> p.getRut().equals("87654321-0")));
    }
}
