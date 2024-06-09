package Launcher;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void iniciarPrograma_MedicoEncontrado_MuestraMenuGeneral() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Act
        Main.iniciarPrograma();

        // Assert
        String consoleOutput = out.toString();
        assertTrue(consoleOutput.contains("=== Menú General ==="));
    }
}
