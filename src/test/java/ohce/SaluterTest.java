package ohce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("A Saluter")
class SaluterTest {
    @InjectMocks
    Saluter saluter;

    @Test
    @DisplayName("salutes person in spanish.")
    void salutesPersonInSpanish() {
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream, true, StandardCharsets.UTF_8));
        System.setIn(new BufferedInputStream(new ByteArrayInputStream(" ".getBytes(StandardCharsets.UTF_8))));

        saluter.salute("Pedro");

        then(outStream.toString()).startsWith("Adios Pedro");
    }
}