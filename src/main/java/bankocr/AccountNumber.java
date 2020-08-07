package bankocr;

@SuppressWarnings("StringConcatenationInLoop")
public class AccountNumber {

    static final int NUMBER_OF_DIGITS = 9;
    static final int DIGIT_WIDTH = 3;

    private final String accountNumberString;

    public AccountNumber(final String firstLine, final String secondLine, final String thirdLine) {
        accountNumberString = parseNextAccountNumber(firstLine, secondLine, thirdLine);
    }

    public AccountNumber(String accountNumber) {
        this.accountNumberString = accountNumber;
    }

    private String parseNextAccountNumber(final String... lines) {

        String accountNr = "";
        for (int pos = 0; pos < NUMBER_OF_DIGITS; pos++) {
            final Digit actualDigit = parseColumnAsDigit(pos, lines);
            final int anInt = actualDigit.getInt();
            accountNr += anInt == -1 ? "?" : anInt;
        }
        return accountNr;
    }

    public boolean isValid() {
        return isParsable() && isChecksumValid();
    }

    private boolean isParsable() {
        return false;
    }

    private boolean isChecksumValid() {
        int checksum = 0;
        for (int pos = NUMBER_OF_DIGITS - 1; pos >= 0; pos--) {
            final char digitChar = accountNumberString.charAt(pos);
            final int digit = Integer.parseInt(String.valueOf(digitChar));
            final int factor = NUMBER_OF_DIGITS - pos;
            checksum += digit * factor;
        }
        return checksum % 11 == 0;
    }

    static Digit parseColumnAsDigit(final int columnIndex, final String... lines) {

        final int charIndex = columnIndex * DIGIT_WIDTH;
        return new Digit(lines[0].substring(charIndex, charIndex + DIGIT_WIDTH),
                lines[1].substring(charIndex, charIndex + DIGIT_WIDTH),
                lines[2].substring(charIndex, charIndex + DIGIT_WIDTH));
    }

    @Override
    public String toString() {
        return accountNumberString;
    }

    public String debug() {
        final String suffix = isChecksumValid() ? "" : " ERR";
        return toString() + suffix;
    }
}
