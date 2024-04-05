package ohce;

import static org.assertj.core.api.BDDAssertions.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Jens Piegsa
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("An InputReverser")
class InputReverserTest {

    @InjectMocks InputReverser inputReverser;

    @Test @DisplayName("reverses input.")
    void reversesInput() {
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outStream, true, StandardCharsets.UTF_8));
        System.setIn(new BufferedInputStream(new ByteArrayInputStream("echo".getBytes(StandardCharsets.UTF_8))));

        inputReverser.reverse();

        then(outStream.toString()).startsWith("ohce");
    }
}