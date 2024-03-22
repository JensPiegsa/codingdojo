package ohce;

import static org.assertj.core.api.BDDAssertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willCallRealMethod;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Jens Piegsa
 */
@ExtendWith(MockitoExtension.class)
class OhceTest {
    
    @Mock private Greeter greeter;
    @Mock private InputReverser inputReverser;


    @InjectMocks
    private Ohce ohce;

    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
    }

    @Test @DisplayName("ohce reverses input.")
    void ohceReversesInput() {
        willCallRealMethod().given(inputReverser).reverser();

        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outStream, true, StandardCharsets.UTF_8));
        System.setIn(new BufferedInputStream(new ByteArrayInputStream("echo".getBytes(StandardCharsets.UTF_8))));

        ohce.start();

        then(outStream.toString()).startsWith("ohce");
    }
    
    @Test @DisplayName("greeter greets person in spanish.")
    void greeterGreetsPersonInSpanish() {
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream, true, StandardCharsets.UTF_8));
        System.setIn(new BufferedInputStream(new ByteArrayInputStream(" ".getBytes(StandardCharsets.UTF_8))));
    	
        Ohce.main(new String[]{"Pedro"});
        
        then(outStream.toString()).startsWith("¡Buenos días Pedro!");
    }
    
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
