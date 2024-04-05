package ohce;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/**
 * When you start ohce, it greets you differently depending on the current time, but only in Spanish:
 *
 *     Between 20 and 6 hours, ohce will greet you saying: ¡Buenas noches < your name >!
 *     Between 6 and 12 hours, ohce will greet you saying: ¡Buenos días < your name >!
 *     Between 12 and 20 hours, ohce will greet you saying: ¡Buenas tardes < your name >!
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("A Greeter")
class GreeterTest {

    Greeter greeter;

    @DisplayName("greets person in spanish.")
    @ParameterizedTest
    @CsvSource({
            "04,¡Buenas noches Pedro!",
            "10,¡Buenos días Pedro!",
            "13,¡Buenas tardes Pedro!"})
    void greetsPersonInSpanish(final String hour, final String expectedOutput) {
        final Clock clock = Clock.fixed(Instant.parse("2024-04-05T" + hour + ":00:00.000000000Z"), ZoneId.of("Europe/Berlin"));
        greeter = new Greeter(clock);

        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream, true, StandardCharsets.UTF_8));
        System.setIn(new BufferedInputStream(new ByteArrayInputStream(" ".getBytes(StandardCharsets.UTF_8))));

        greeter.greet("Pedro");

        then(outStream.toString()).startsWith(expectedOutput);
    }

}