package bankingkata;

import java.math.BigInteger;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private BigInteger balance;
    private List<Transaction> transactions = new ArrayList<>();
    
    Clock clock;

    public Account() {
        balance = new BigInteger("0");
    }

    public String printStatement() {
        return "Date        Amount  Balance\n";
    }

    public void deposit(final int amount) {
        balance = balance.add(BigInteger.valueOf(amount));
    }

    public void withdraw(final int amount) {
        balance = balance.subtract(BigInteger.valueOf(amount));
    }

    public BigInteger getBalance() {
        return balance;
    }
}
