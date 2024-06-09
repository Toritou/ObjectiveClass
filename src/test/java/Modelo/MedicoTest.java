package Modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MedicoTest {

    @Test
    public void modificarFichaPaciente_Existente_ModifiesFicha() {
        Medico medico = new Medico("1234", "Dr. Juan Perez", "Medicina General", "Hospital ABC");
        Paciente paciente = new Paciente("Nombre", "12345678-9", "20", "01/01/2000", "O+", "70", "Soltero",
                "Dirección", "", "", "", "", "", "Ficha médica anterior");
        medico.agregarPaciente(paciente);

        medico.modificarFichaPaciente("12345678-9", "Nueva ficha médica");

        assertEquals("Nueva ficha médica", paciente.getFichaMedica());
    }

    @Test
    public void modificarFichaPaciente_NoExistente_ReturnsNull() {

        Medico medico = new Medico("1234", "Dr. Juan Perez", "Medicina General", "Hospital ABC");
        Paciente paciente = new Paciente("Nombre", "12345678-9", "20", "01/01/2000", "O+", "70", "Soltero",
                "Dirección", "", "", "", "", "", "Ficha médica anterior");
        medico.agregarPaciente(paciente);


        medico.modificarFichaPaciente("11111111-1", "Nueva ficha médica");

        assertNull(medico.obtenerPacientePorRut("11111111-1"));
    }
}
