package bankingkata;

import java.math.BigInteger;
import java.time.Clock;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final String DATE_PATTERN = "d.M.yyyy";
    private BigInteger balance;
    private final List<Transaction> transactions = new ArrayList<>();
    
    Clock clock;

    public Account() {
        balance = new BigInteger("0");
    }

    public String printStatement() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Date        Amount  Balance\n");
        int currentBalance = 0;

        for (final Transaction transaction : transactions) {
            
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(clock.getZone());
            
            final Instant instant = transaction.instant();

            currentBalance += transaction.amount();
            final String sign = transaction.amount() >= 0 ? "+" : "";

            final String date = dateTimeFormatter.format(instant);
            final String amount = sign + transaction.amount();
            final String balance = String.valueOf(currentBalance);

            stringBuilder
                    .append(padRight(date, 13))
                    .append(padRight(amount, 10))
                    .append(balance)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    private String padRight(final String string, final int width) {
        return string + " ".repeat(width - string.length());
    }

    public void deposit(final int amount) {
        balance = balance.add(BigInteger.valueOf(amount));
        transactions.add(new Transaction(clock.instant(), amount));
    }

    public void withdraw(final int amount) {
        balance = balance.subtract(BigInteger.valueOf(amount));
        transactions.add(new Transaction(clock.instant(), -amount));
    }

    public BigInteger getBalance() {
        return balance;
    }
}
