package tcp;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.Test;

public class AlphabetTest {

    @Test
    void canBeConstructedFromEnum() {
        
        enum Test {
            A,
            B
        }

        Alphabet<Test> alphabet = Alphabet.from(Test.class);
        then(alphabet.toString()).isEqualTo("{A, B}");
    }

}
