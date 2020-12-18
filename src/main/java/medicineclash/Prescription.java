package medicineclash;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Prescription {
    
    private final ZonedDateTime dispenseDate;
    private final int daysSupply;
    
    public Prescription(ZonedDateTime dispenseDate, int daysSupply) {
        this.dispenseDate = dispenseDate;
        this.daysSupply = daysSupply;
    }

    public boolean isTakenAt(final ZonedDateTime queryDate) {
        final ZonedDateTime dispenseDay = dispenseDate.truncatedTo(ChronoUnit.DAYS);
        final ZonedDateTime queryDay = queryDate.truncatedTo(ChronoUnit.DAYS);
        final ZonedDateTime lastDay = dispenseDay.plusDays(daysSupply);
        
        return isBetweenInclusive(dispenseDay, queryDay, lastDay);
    }

    private boolean isBetweenInclusive(final ZonedDateTime dispenseDay, final ZonedDateTime queryDay, final ZonedDateTime lastDay) {
        return queryDay.isEqual(dispenseDay)
                || queryDay.isEqual(lastDay)
                || (queryDay.isBefore(lastDay) && queryDay.isAfter(dispenseDay));
    }
}
