package medicineclash;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PatientTest {
	
	@Test @DisplayName("no clash for patient without prescription")
	void noClashForPatientWithoutPrescription() {
		// given
		Patient patient = new Patient();
		
		// when
		final Collection<Instant> clash = patient.clash(asList("Med one", "Med two"), 10);

		// then
		assertThat(clash).isEmpty();
	}

	@Test @DisplayName("clash for patient with prescription")
	void clashForPatientWithPrescription() {
		// given
		Instant today = Instant.now();
		Prescription prescriptionOne = new Prescription(today, 2);
		Prescription prescriptionTwo = new Prescription(today, 2);
		Medicine medicineOne = new Medicine("First");
		medicineOne.addPrescription(prescriptionOne);
		Medicine medicineTwo = new Medicine("Second");
		medicineTwo.addPrescription(prescriptionTwo);
		
		Patient patient = new Patient();
		patient.addMedicine(medicineOne);
		patient.addMedicine(medicineTwo);
		
		// when
		final Collection<Instant> clash = patient.clash(asList("First", "Second"), 1);

		// then
		assertThat(clash).hasSize(2);
	}
}