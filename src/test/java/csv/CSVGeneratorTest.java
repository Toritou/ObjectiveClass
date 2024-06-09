package csv;

import Modelo.Medico;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVGeneratorTest {

    @Test
    void generateMedicosCSVTest() {
        List<Medico> medicos = new ArrayList<>();
        medicos.add(new Medico("11111111-1", "Dr. Juan Pérez", "Cardiología", "Hospital A"));

        String fileName = "medicos_test.csv";

        CSVGenerator.generateMedicosCSV(fileName, medicos);

        File file = new File(fileName);
        assertTrue(file.exists(), "El archivo CSV no se ha creado");

        file.delete();
    }
}
