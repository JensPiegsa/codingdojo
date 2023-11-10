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
        Period period = Period.ofDays(2);

        String formatedPeriod = AmountFormats.wordBased(period, Locale.ENGLISH);

        then(formatedPeriod).isEqualTo("2 days");
    }

//    @Test
//    @DisplayName("use of three ten extra")
//    void useOfThreeTenExtra() {
//        Duration period = Period.ofDays(2);
//
//        String formatedPeriod = AmountFormats.wordBased(period, Locale.ENGLISH);
//
//        then(formatedPeriod).isEqualTo("2 days");
//    }
}
