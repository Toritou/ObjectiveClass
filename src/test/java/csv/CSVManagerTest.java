package csv;

import Modelo.Pacientes;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CSVManagerTest {

    @Test
    void testLeerPacientes() {
        List<Pacientes> pacientes = CSVManager.leerPacientes();
        assertNotNull(pacientes);
        assertFalse(pacientes.isEmpty());
    }

    @Test
    void testEscribirPacientes() {
        List<Pacientes> pacientes = CSVManager.leerPacientes();
        Pacientes nuevoPaciente = new Pacientes("Nuevo Paciente", "87654321-0", "nuevo@correo.com", "25", "02/02/1999", "B+", "65kg", "Casado", "Nuevo Domicilio", "Ninguna", "Ninguna", "Ninguno", "Ninguna", "Ninguno", "Nueva Ficha");
        pacientes.add(nuevoPaciente);
        CSVManager.escribirPacientes(pacientes);

        List<Pacientes> pacientesLeidos = CSVManager.leerPacientes();
        assertTrue(pacientesLeidos.stream().anyMatch(p -> p.getRut().equals("87654321-0")));
    }
}
