package tcp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class StatesTest {

    @Test
    void canBeConstructedFromEnum() {

        enum Test {
            C,
            D
        }

        States<Test> states = States.from(Test.class);
        then(states.toString()).isEqualTo("{C, D}");
    }
}
