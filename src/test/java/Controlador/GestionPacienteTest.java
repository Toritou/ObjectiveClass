package Controlador;

import Modelo.Medico;
import Modelo.Paciente;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestionPacienteTest {

    @Test
    public void agregarPaciente_PacienteAgregado_Correctamente() {
        // Arrange
        Medico medico = new Medico("1234", "Dr. Juan Perez", "Medicina General", "Hospital ABC");
        GestionPaciente gestionPaciente = new GestionPaciente(medico);

        Paciente paciente = new Paciente("Juan", "123456789", "30", "1990-01-01",
                "A+", "70", "Soltero", "Calle 123", "", "", "", "", "", "");

        List<Paciente> pacientesEsperados = new ArrayList<>();
        pacientesEsperados.add(paciente);

        // Act
        gestionPaciente.agregarPaciente(paciente);

        // Assert
        List<Paciente> pacientesActuales = medico.getPacientes();
        assertEquals(pacientesEsperados.size(), pacientesActuales.size());
        assertEquals(pacientesEsperados.get(0).getNombreCompleto(), pacientesActuales.get(0).getNombreCompleto());
        assertEquals(pacientesEsperados.get(0).getRut(), pacientesActuales.get(0).getRut());
    }
}
