package ohce;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

/**
 * <a href="https://kata-log.rocks/ohce-kata">https://kata-log.rocks/ohce-kata</a>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("An Ohce")
class OhceTest {
    
    String name = "Pedro";
    
    @Mock private Greeter greeter;
    @Mock private InputReverser inputReverser;
    @Mock private Saluter saluter;

    @InjectMocks
    private Ohce ohce;

    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        ohce = new Ohce(greeter, inputReverser, saluter, name);
    }


    @Test @DisplayName("greets, reverses and salutes in order.")
    void greetsReversesAndSalutesInOrder() {
        
        final InOrder inOrder = BDDMockito.inOrder(greeter, inputReverser, saluter);

        ohce.start();

        inOrder.verify(greeter).greet("Pedro");
        inOrder.verify(inputReverser).reverse();
        inOrder.verify(saluter).salute("Pedro");
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
