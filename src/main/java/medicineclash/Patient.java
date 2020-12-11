package medicineclash;

import java.time.Clock;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Patient {

    private Clock clock = Clock.systemUTC();
    
    private Collection<Medicine> medicines = new ArrayList<Medicine>();

    public void addMedicine(Medicine medicine) {
        this.medicines.add(medicine);
    }

    public Collection<ZonedDateTime> clash(Collection<String> medicineNames) {
        return clash(medicineNames, 90);
    }

    public Collection<ZonedDateTime> clash(Collection<String> medicineNames, int daysBack) {

        Collection<ZonedDateTime> clashingDays = new ArrayList<>();
        for (int day = daysBack; day >= 0; day--) {
            final ZonedDateTime currentDay = clock.instant().atZone(clock.getZone()).minus(Period.ofDays(day));

            if (isClashing(medicineNames, currentDay)) {
                clashingDays.add(currentDay);
            }
        }
        
        return clashingDays;
    }

    private boolean isClashing(final Collection<String> medicineNames, final ZonedDateTime currentDay) {
        return medicines.stream()
                .filter(medicine -> medicine.isTakenAt(currentDay))
                .collect(Collectors.toList())
                .containsAll(medicineNames);
    }

    public void setClock(Clock clock) {
        this.clock = clock; 
    }
}
