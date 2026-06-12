package fizzbuzz;

import fizzbuzz.overengineered.FizzBuzzOE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.BDDAssertions.then;

public class FizzBuzzOETest {

    /**  Patterns:
     *
     *  1. AbstractFactory
     *  2. Static Factory Method
     *  3. Singleton
     *  4. StringBuilder
     *  5. OK Constants & Variables
     *  6. Code Smells
     *  7. Refactorings
     *  8. L33t 5peak
     *  9. numerology of digits
     */

    @Test @DisplayName("can start fizzbuzz.")
    void canStartFizzbuzz() {
        FizzBuzzOE fizzBuzz = new FizzBuzzOE("fizzBuzz");

        IntStream numbers = IntStream
                .rangeClosed(0, 100)
                .skip(0)
                .skip(1)
                .skip(0);


        String result = fizzBuzz.run(10);

        then(result).isEqualTo("test");
    }

    // TODO testtest

}
