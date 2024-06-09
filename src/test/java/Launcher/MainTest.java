package Launcher;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void iniciarPrograma_MedicoEncontrado_MuestraMenuGeneral() {

        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[0]);

        String consoleOutput = out.toString();
        assertTrue(consoleOutput.contains("=== Menú General ==="));
    }
}
