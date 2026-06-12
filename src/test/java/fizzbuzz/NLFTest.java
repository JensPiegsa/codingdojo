package fizzbuzz;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NLFTest {

    @Test
    @DisplayName("can detect system")
    void canDetectSystem() {
        NLF n = new NLF();
        assertThat(n.s()).isEqualTo("Windows 11");
    }
}