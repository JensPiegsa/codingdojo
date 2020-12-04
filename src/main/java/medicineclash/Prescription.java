package medicineclash;

import java.time.Instant;

public class Prescription {
    
    private Instant dispenseDate = Instant.now();
    private int daysSupply = 30;
    
    public Prescription(Instant dispenseDate, int daysSupply) {
        this.dispenseDate = dispenseDate;
        this.daysSupply = daysSupply;
    }

}
