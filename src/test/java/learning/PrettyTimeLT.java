package learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.BDDAssertions.then;

public class PrettyTimeLT {


    @Test @DisplayName("use of pretty time (de)")
    void useOfPrettyTime() {
        final PrettyTime prettyTime = new PrettyTime(Locale.GERMAN);
        String format = prettyTime.format(LocalDateTime.now().minusDays(1));
        then(format).isEqualTo("vor 1 Tag");
    }

    @Test @DisplayName("prettier time (en)")
    void prettierTime() {
        final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
        String format = prettyTime.format(LocalDateTime.now().minusDays(1));
        then(format).isEqualTo("1 day ago");
    }

    @Test @DisplayName("compound duration (larger part wins)")
    void compoundDuration() {
        final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
        String format = prettyTime.format(LocalDateTime.now().minusDays(1).minusHours(5));
        then(format).isEqualTo("1 day ago");
    }

    @Test @DisplayName("can format 59 min duration")
    void canFormat59MinDuration() {
        final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
        String format = prettyTime.format(LocalDateTime.now().minusMinutes(59));
        then(format).isEqualTo("59 minutes ago");
    }

    @Test @DisplayName("can format 60 min duration")
    void canFormat60MinDuration() {
        final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
        String format = prettyTime.format(LocalDateTime.now().minusMinutes(60));
        then(format).isEqualTo("1 hour ago");
    }

    @Test @DisplayName("can calc durations")
    void canCalcDurations() {
        LocalDateTime now = LocalDateTime.now();
        final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
        prettyTime.setReference(now);
        LocalDateTime creationTime = now.minusMinutes(10).minusHours(7);
        
        final List<Duration> durations = prettyTime.calculatePreciseDuration(creationTime);
        String format = prettyTime.formatDuration(durations);
        
        then(format).isEqualTo("7 hours 10 minutes");
    }

    @Test @DisplayName("can calc durations (appr.)")
    void canCalcDurationsApproximate() {
        LocalDateTime now = LocalDateTime.now();
        final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
        prettyTime.setReference(now);
        LocalDateTime creationTime = now.minusMinutes(10).minusHours(7);
        
        final Duration durations = prettyTime.approximateDuration(creationTime);
        String format = prettyTime.formatDuration(durations);
        
        then(format).isEqualTo("7 hours");
    }

}
