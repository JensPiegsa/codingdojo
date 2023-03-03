package humanreadableduration;

import static java.util.stream.Collectors.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeFormatter {
    
    public static String formatDuration(final int seconds) {
        
        final Duration duration = Duration.ofSeconds(seconds);
        final long daysPart = duration.toDaysPart();
        final int hourPart = duration.toHoursPart();
        final int minutesPart = duration.toMinutesPart();
        final int secondsPart = duration.toSecondsPart();
        
        final List<String> parts = new ArrayList<>();

        if (seconds == 0) {
            return "now";
        }

        if (daysPart > 0L) {
            parts.add(daysPart + " " + pluralize(daysPart, "day"));
        }
        
        if (hourPart > 0) {
            parts.add(hourPart + " " + pluralize(hourPart, "hour"));
        }
        
        if (minutesPart > 0) {
            parts.add(minutesPart + " " + pluralize(minutesPart, "minute"));
        }
        
        if (secondsPart > 0) {
            parts.add(secondsPart + " " + pluralize(secondsPart, "second"));
        }
        return combine(parts);
    }

    private static String pluralize(final long amount, String unit) {
        if (amount > 1L) {
            unit += "s";
        }
        return unit;
    }

    public static String combine(final List<String> elements) {
        final int length = elements.size();
        return length == 1
                ? elements.get(0)
                : elements.stream()
                .limit(length - 1L)
                .collect(joining(", ")) + " and " + elements.get(length - 1);
    }
}