package medicineclash;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

public class Patient {

    private Collection<Medicine> medicines = new ArrayList<Medicine>();

    public void addMedicine(Medicine medicine) {
        this.medicines.add(medicine);
    }

    public Collection<Instant> clash(Collection<String> medicineNames) {
        return clash(medicineNames, 90);
    }

    public Collection<Instant> clash(Collection<String> medicineNames, int daysBack) {

        Collection<Instant> clashingDays = new ArrayList<>();
        for (int day = daysBack; day >= 0; day--) {
            final Instant currentDay = Instant.now().minus(Period.ofDays(day));
            
            if (!medicines.isEmpty()) {
                clashingDays.add(currentDay);
            }
        }
        
        return clashingDays;
    }
}
