package bankocr;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("StringConcatenationInLoop")
public class AccountNumber {

    static final int NUMBER_OF_DIGITS = 9;
    static final int DIGIT_WIDTH = 3;

    private final String accountNumberString;

    public AccountNumber(final String firstLine, final String secondLine, final String thirdLine) {
        
        if (firstLine.length() != NUMBER_OF_DIGITS*DIGIT_WIDTH) {
            throw new IllegalArgumentException("Should contain 9 digits");
        }
        accountNumberString = parseNextAccountNumber(firstLine, secondLine, thirdLine);
    }

    public AccountNumber(String accountNumber) {
        this.accountNumberString = accountNumber;
    }

    private String parseNextAccountNumber(final String... lines) {

        List<List<Integer>> combinations = new ArrayList<>();
        String accountNr = "";
        for (int pos = 0; pos < NUMBER_OF_DIGITS; pos++) {
            final Digit actualDigit = parseColumnAsDigit(pos, lines);
            final List<Integer> anInt = actualDigit.getPossibleValues();
            combinations.add(anInt);


            accountNr += anInt.isEmpty() ? "?" : anInt;
        }
        
        List<String> possibleAccountNumbers = permutate(combinations);
//        calculateChecksum(anAccountNumber);
        return accountNr;
    }

    static List<String> permutate(final List<List<Integer>> combinations) {
        return permutateRecursively(singletonList(""), combinations);
    }

    private static List<String> permutateRecursively(final List<String> head, final List<List<Integer>> tail) {
        if (tail.isEmpty()) {
            return head;
        }

        List<String> newHead = head.stream()
                .map(headElement -> tail.get(0).stream()
                        .map(integer -> headElement + integer)
                        .collect(toList()))
                .flatMap(List::stream)
                .collect(toList());
        
        return permutateRecursively(newHead, tail.subList(1, tail.size()));
    }

    public boolean isValid() {
        return isParsable() && isChecksumValid();
    }

    private boolean isParsable() {
        return !accountNumberString.contains("?");
    }

    boolean isChecksumValid() {
        int checksum = calculateChecksum(this.accountNumberString);
        final boolean isValid = checksum % 11 == 0;
        return isValid;
    }

    private int calculateChecksum(final String accountNumberString) {
        int checksum = 0;
        for (int pos = NUMBER_OF_DIGITS - 1; pos >= 0; pos--) {
            final char digitChar = accountNumberString.charAt(pos);
            final int digit = Integer.parseInt(String.valueOf(digitChar));
            final int factor = NUMBER_OF_DIGITS - pos;
            checksum += digit * factor;
        }
        return checksum;
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
        final String suffix;
        final boolean parsable = isParsable();
        if(parsable) {
            suffix = isChecksumValid() ? "" : " ERR";
        } else {
            suffix = " ILL";
        }
        return toString() + suffix;
    }
}
