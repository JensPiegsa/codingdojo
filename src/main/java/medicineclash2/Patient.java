package medicineclash2;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Patient {

    private Collection<Medicine> medicines = new ArrayList<Medicine>();

    public void addMedicine(Medicine medicine) {
        this.medicines.add(medicine);
    }

    public Collection<Date> clash(Collection<String> medicineNames) {
        return clash(medicineNames, 90);
    }

    public Collection<Date> clash(Collection<String> medicineNames, int daysBack) {
        Collection<Date> clashes = new ArrayList<>();

        for (Medicine med : medicines) {
            Collection<Prescription> medPrescriptions = med.getPrescriptions();

//            for (Prescription prescription : medPrescriptions) {
//
//
//            }
            final Collection<Date> allIntakesOfSingleMedicineInRange = medPrescriptions.stream()
                    .map(prescription -> generateSequenceOfDays(prescription.getDaysSupply(), prescription.getDispenseDate()))
                    .flatMap(Collection::stream)
                    .distinct()
                    .toList();
        }
//            Collection<Date> result = 
//            for (var intakeSequence : allIntakesOfSingleMedicineInRange) {
//                int
//            }

        return clashes;
    }


    public Collection<Date> generateSequenceOfDays(final int daysBack, Date dueDate) {

        //final Instant now = Instant.now();
        Collection<Date> datesSince = new ArrayList<>();

        for (int day = 0; day < daysBack; day++) {
            datesSince.add(generateDate(dueDate.toInstant(), day));
        }

        return datesSince;
    }

    private Date generateDate(Instant startDate, int daysBefore) {
        final Clock startClock = Clock.fixed(startDate, ZoneId.of("Europe/Berlin"));
        final Clock calculatedClock = Clock.offset(startClock, Duration.of(daysBefore, ChronoUnit.DAYS));
        return Date.from(calculatedClock.instant());
    }


}