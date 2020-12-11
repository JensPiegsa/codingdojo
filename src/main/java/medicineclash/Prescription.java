package medicineclash;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;

public class Prescription {
    
    private final ZonedDateTime dispenseDate;
    private final int daysSupply;
    
    public Prescription(ZonedDateTime dispenseDate, int daysSupply) {
        this.dispenseDate = dispenseDate;
        this.daysSupply = daysSupply;
    }

    public boolean isTakenAt(final ZonedDateTime currentDay) {
        final LocalDate startDateInclusive = dispenseDate.toLocalDate();
        final LocalDate endDateExclusive = currentDay.plusDays(1).toLocalDate();
        final int daysBetween = Period.between(startDateInclusive, endDateExclusive).getDays();
        System.out.println("###\n" +
                "startDateInclusive: " + startDateInclusive +
                "\nendDateExclusive: " + endDateExclusive +
                "\ndaysBetween: " + daysBetween);
        return daysBetween >= 0 && daysBetween <= daysSupply;
    }
}
