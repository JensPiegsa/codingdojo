package medicineclash;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PatientTest {
	
	@Test @DisplayName("no clash for patient without prescription")
	void noClashForPatientWithoutPrescription() {
		// given
		Patient patient = new Patient();
		
		// when
		final Collection<ZonedDateTime> clash = patient.clash(asList("Med one", "Med two"), 10);

		// then
		assertThat(clash).isEmpty();
	}

	@Test @DisplayName("clash for patient with prescription")
	void clashForPatientWithPrescription() {
		// given
		final ZonedDateTime today = ZonedDateTime.parse("2020-12-03T12:00:00.000Z");
		final ZonedDateTime yesterday = ZonedDateTime.parse("2020-12-02T12:00:00.000Z");
		
		Prescription prescriptionOne = new Prescription(today, 1);
		Prescription prescriptionTwo = new Prescription(today, 1);
		Medicine medicineOne = new Medicine("First");
		medicineOne.addPrescription(prescriptionOne);
		Medicine medicineTwo = new Medicine("Second");
		medicineTwo.addPrescription(prescriptionTwo);
		
		Patient patient = new Patient();
		patient.setClock(Clock.fixed(today.toInstant(), today.getZone()));
		patient.addMedicine(medicineOne);
		patient.addMedicine(medicineTwo);
		
		// when
		final Collection<ZonedDateTime> clash = patient.clash(asList("First", "Second"), 1);

		// then
		assertThat(clash).hasSize(2);
		assertThat(clash).containsExactlyInAnyOrder(today, yesterday);
	}
	
	@Test @DisplayName("no clash for patient with prescription")
	void noClashForPatientWithPrescription() {
		// given
		final ZonedDateTime today = ZonedDateTime.parse("2020-12-03T12:00:00.000Z");
		final ZonedDateTime yesterday = ZonedDateTime.parse("2020-12-02T12:00:00.000Z");

		Prescription prescriptionOne = new Prescription(today, 1);
		Prescription prescriptionTwo = new Prescription(today, 1);
		Medicine medicineOne = new Medicine("First");
		medicineOne.addPrescription(prescriptionOne);
		Medicine medicineTwo = new Medicine("Second");
		medicineTwo.addPrescription(prescriptionTwo);

		Patient patient = new Patient();
		patient.setClock(Clock.fixed(today.toInstant(), today.getZone()));
		patient.addMedicine(medicineOne);
		patient.addMedicine(medicineTwo);

		// when
		final Collection<ZonedDateTime> clash = patient.clash(asList("First", "Third"), 1);
		
		// then
		assertThat(clash).isEmpty();
	}

	@Test @DisplayName("clash for patient with complex prescription")
	void clashForPatientWithComplexPrescription() {
		// given
		final ZonedDateTime today = ZonedDateTime.parse("2020-12-03T12:00:00.000Z");
		final ZonedDateTime yesterday = ZonedDateTime.parse("2020-12-02T12:00:00.000Z");
		final ZonedDateTime twoDaysAgo = ZonedDateTime.parse("2020-12-01T12:00:00.000Z");
		final ZonedDateTime threeDaysAgo = ZonedDateTime.parse("2020-11-30T12:00:00.000Z");

		// p1: ☑☑☑☐
		// p2: ☐☑☑☑
		Prescription prescriptionOne = new Prescription(threeDaysAgo, 2);
		Prescription prescriptionTwo = new Prescription(twoDaysAgo, 2);
		
		Medicine medicineOne = new Medicine("First");
		medicineOne.addPrescription(prescriptionOne);
		Medicine medicineTwo = new Medicine("Second");
		medicineTwo.addPrescription(prescriptionTwo);

		Patient patient = new Patient();
		patient.setClock(Clock.fixed(today.toInstant(),today.getZone()));
		patient.addMedicine(medicineOne);
		patient.addMedicine(medicineTwo);

		// when
		final Collection<ZonedDateTime> clash = patient.clash(asList("First", "Second"), 3);

		// then
		assertThat(clash).hasSize(2);
		assertThat(clash).containsExactlyInAnyOrder(twoDaysAgo, yesterday);
	}
}