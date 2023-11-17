package learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.extra.AmountFormats;

import java.time.Duration;
import java.time.Period;
import java.util.Locale;

import static org.assertj.core.api.BDDAssertions.then;

public class ThreeTenExtraLT {

    @Test
    @DisplayName("use of three ten extra")
    void useOfThreeTenExtra() {
        final Period period = Period.ofDays(2);

        final String formattedPeriod = AmountFormats.wordBased(period, Locale.ENGLISH);

        then(formattedPeriod).isEqualTo("2 days");
    }

    @Test
    @DisplayName("use of three ten extra time")
    void useOfThreeTenExtraTime() {
        final Duration duration = Duration.ofDays(2);

        final String formattedPeriod = AmountFormats.wordBased(duration, Locale.ENGLISH);

        then(formattedPeriod).isEqualTo("48 hours");
    }

    @Test @DisplayName("combine hours and minutes.")
    void combineHoursAndMinutes() {
        final Duration duration = Duration.ofHours(10).plusMinutes(10);

        final String formattedPeriod = AmountFormats.wordBased(duration, Locale.ENGLISH);

        then(formattedPeriod).isEqualTo("10 hours and 10 minutes");
    }

    @Test @DisplayName("test german exaktheit!!!")
    void newTest() {
        final Period period = Period.ofDays(3);
        final Duration duration = Duration.ofHours(5);
        final String result = AmountFormats.wordBased(period, duration, Locale.GERMAN);
        then(result).isEqualTo("3 Tage und 5 Stunden");
    }

    @Test @DisplayName("combination of period and duration with days.")
    void combinationOfPeriodAndDurationWithDays() {
        final Period period = Period.ofDays(3);
        final Duration duration = Duration.ofDays(5);
        final String result = AmountFormats.wordBased(period, duration, Locale.GERMAN);
        then(result).isEqualTo("8 Tage");
    }

    @Test @DisplayName("approximate calculation.")
    void approximateCalculation() {
        final Period period = Period.ofDays(3);
        final Duration duration = Duration.ofHours(5);
        final String result = AmountFormats.wordBased(period, duration, Locale.GERMAN);
        then(result).isEqualTo("3 Tage und 5 Stunden");
    }
}
