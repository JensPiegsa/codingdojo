package bankingkata;

import java.time.Instant;

public record Transaction(Instant instant, int amount) {
}
