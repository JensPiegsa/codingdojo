package bankingkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.BDDAssertions.then;

class AccountTest {


    // https://kata-log.rocks/banking-kata

    /*
    Write a class Account that offers the following methods void deposit(int) void withdraw(int) String printStatement()

    An example statement would be:
    
    Date        Amount  Balance
    24.12.2015   +500      500
    23.8.2016    -100      400

     */

    @Test @DisplayName("can print statement.")
    void canPrintStatement() {
        final Instant now = Instant.now();
        System.out.println(now);
        final Account account = new Account();

        account.clock = Clock.fixed(Instant.parse("2015-12-24T12:00:00.000000000Z"), ZoneId.of("Europe/Berlin"));
        account.deposit(500);

        account.clock = Clock.fixed(Instant.parse("2016-08-23T12:00:00.000000000Z"), ZoneId.of("Europe/Berlin"));
        account.withdraw(100);
        
        final String result = account.printStatement();
        then(result).isEqualTo("""
                Date        Amount  Balance
                24.12.2015   +500      500
                23.8.2016    -100      400
                """);
    }

    @Test @DisplayName("can print empty statement.")
    void canPrintEmptyStatement() {
        final Account account = new Account();
        final String result = account.printStatement();
        then(result).isEqualTo("Date        Amount  Balance\n");
    }

    @Test @DisplayName("new Account has balance zero")
    void newAccountHasBalanceZero () {
        final Account account = new Account();
        then(account.getBalance()).isZero();
    }

    @Test @DisplayName("can deposit money.")
    void canDepositMoney() {
        final Account account = new Account();
        account.clock = Clock.fixed(Instant.parse("2015-12-24T12:00:00.000000000Z"), ZoneId.of("Europe/Berlin"));
        account.deposit(1);
        then(account.getBalance()).isEqualTo(1);
    }

    @Test @DisplayName("can withdraw money.")
    void canWithdrawMoney() {
        final Account account = new Account();
        account.clock = Clock.fixed(Instant.parse("2015-12-24T12:00:00.000000000Z"), ZoneId.of("Europe/Berlin"));
        account.withdraw(1);
        then(account.getBalance()).isEqualTo(-1);
    }
    
    
}