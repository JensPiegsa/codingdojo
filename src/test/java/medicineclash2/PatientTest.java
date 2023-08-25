package medicineclash2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.*;

@DisplayName("A patient")
public class PatientTest {
    
    @Test @DisplayName("taking no medicine has no clash.")
    void takingNoMedicineHasNoClash() {
        Patient patient = new Patient();
        var clashed = patient.clash(List.of("medOne", "medTwo"), 1);
        then(clashed).isEmpty();
    }

    @Test @DisplayName("can detect clash.")
    void canDetectClash() {
        //given
        Patient patient = new Patient();
        Medicine med1 = new Medicine("medOne");
        Medicine med2 = new Medicine("medTwo");
        
        final Instant nowInst = Instant.now();
        final Clock nowClock = Clock.fixed(nowInst, ZoneId.of("Europe/Berlin"));

        final Date now = Date.from(nowClock.instant());
        Prescription prescr1 = new Prescription(now, 1);
        Prescription prescr2 = new Prescription(now, 1);
        med1.addPrescription(prescr1);
        med2.addPrescription(prescr2);

        patient.addMedicine(med1);
        patient.addMedicine(med2);

        // when
        Collection<Date> clashed = patient.clash(List.of("medOne", "medTwo"), 1);

        // then
        then(clashed).isNotEmpty();
        then(clashed).containsExactly(now);
    }
    
    @Test @DisplayName("can generate sequence of days.")
    void canGenerateSequenceOfDays() {
        final Instant nowInstant = Instant.now();
        final Clock nowClock = Clock.fixed(nowInstant, ZoneId.of("Europe/Berlin"));
        final Clock yesterdayClock = Clock.offset(nowClock, Duration.of(1, ChronoUnit.DAYS));
        final Date today = Date.from(nowClock.instant());
        final Date yesterday = Date.from(yesterdayClock.instant());
        
        int daysBack = 2;
        Collection<Date> sequence = new Patient().generateSequenceOfDays(daysBack, today);
        then(sequence).containsExactly(today, yesterday);
    }
}
