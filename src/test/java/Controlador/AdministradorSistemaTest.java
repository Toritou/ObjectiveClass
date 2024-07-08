package Controlador;

import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class AdministradorSistemaTest {

    @Test
    void testParseDateValid() throws ParseException {
        String fechaHora = "12/07/2024 14:30";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fecha = sdf.parse(fechaHora);
        assertNotNull(fecha);
    }

    @Test
    void testParseDateInvalid() {
        String fechaHora = "invalid_date";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        assertThrows(ParseException.class, () -> sdf.parse(fechaHora));
    }
}
