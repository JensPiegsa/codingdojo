package medicineclash;

import static org.assertj.core.api.BDDAssertions.*;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrescriptionTest {

	@Test @DisplayName("same day with one supply")
	void sameDay() {
		// given
		final ZonedDateTime today = ZonedDateTime.parse("2020-12-03T12:00:00.000Z");
		int daysSupply = 0;
		Prescription prescription = new Prescription(today, daysSupply);

		// when
		final boolean takenAt = prescription.isTakenAt(ZonedDateTime.parse("2020-12-03T00:00:00.000Z"));

		// then
		then(takenAt).isTrue();
	}
	
	@Test @DisplayName("prescription with one extra supply")
	void prescriptionWithOneExtraSupply() {
		// given
		final ZonedDateTime yesterday = ZonedDateTime.parse("2020-12-02T12:00:00.000Z");
		final ZonedDateTime today = ZonedDateTime.parse("2020-12-03T12:00:00.000Z");
		final ZonedDateTime tomorrow = ZonedDateTime.parse("2020-12-04T12:00:00.000Z");
		final ZonedDateTime dayAfterTomorrow = ZonedDateTime.parse("2020-12-05T12:00:00.000Z");
		int daysSupply = 1;
		Prescription prescription = new Prescription(today, daysSupply);
		
		// when
		final boolean takenAtYesterday = prescription.isTakenAt(yesterday);
		final boolean takenAtToday = prescription.isTakenAt(today);
		final boolean takenAtTomorrow = prescription.isTakenAt(tomorrow);
		final boolean takenAtDayAfterTomorrow = prescription.isTakenAt(dayAfterTomorrow);
		
		// then
		then(takenAtYesterday).isFalse();
		then(takenAtToday).isTrue();
		then(takenAtTomorrow).isTrue();
		then(takenAtDayAfterTomorrow).isFalse();
	}
}