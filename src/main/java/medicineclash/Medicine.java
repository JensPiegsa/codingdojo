package medicineclash;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class Medicine {
    
    private Collection<Prescription> prescriptions = new ArrayList<Prescription>();

    private final String name;

    public Medicine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }
    
    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public boolean isTakenAt(final ZonedDateTime currentDay) {
        return prescriptions.stream().anyMatch(p -> p.isTakenAt(currentDay));
    }
}
