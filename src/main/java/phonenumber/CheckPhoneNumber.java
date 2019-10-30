package phonenumber;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class CheckPhoneNumber {

    public boolean isConsistent(Collection<String> phoneNumbers) {
        Objects.requireNonNull(phoneNumbers);
        if (phoneNumbers.isEmpty()) {
            return true;
        }
        final List<String> formatedNumbers = phoneNumbers
                .stream().map(this::formatNumber)
                .collect(Collectors.toList());

        Optional<String> duplicateNr = formatedNumbers.stream()
                .filter(formatedNumber -> checkExistsAsPrefix(formatedNumber, new ArrayList<>(formatedNumbers))).findFirst();

        if (!duplicateNr.isPresent()) {
            return true;
        }

		System.out.println(Collections.indexOfSubList(formatedNumbers,Arrays.asList(duplicateNr.get())));
        System.out.println(duplicateNr.get());
        return false;
    }


    private boolean checkExistsAsPrefix(final String formatedNumber, final List<String> formatedNumbers) {
        formatedNumbers.remove(formatedNumber);
        return formatedNumbers.stream()
                .filter(currentNumber -> currentNumber.startsWith(formatedNumber)).peek(line -> System.out.println(line)).findFirst().isPresent();
    }

    private String formatNumber(final String number) {
        return number.replace(" ", "").replace("-", "");
    }

}
