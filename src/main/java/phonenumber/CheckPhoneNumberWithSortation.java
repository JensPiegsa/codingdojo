package phonenumber;

import java.util.*;
import java.util.stream.Collectors;

public class CheckPhoneNumberWithSortation implements PhoneNumberCheckable {

    @Override
    public boolean isConsistent(Map<String, String> phoneNumbers) {
        Objects.requireNonNull(phoneNumbers);

        List<String> sortedNumbers = new ArrayList<>(phoneNumbers.values()).stream()
                .map(this::format)
                .sorted()
                .collect(Collectors.toList());


        for (int i = 0; i < sortedNumbers.size(); i++) {

            for (int ii = i + 1; ii < sortedNumbers.size(); ii++) {

                if (sortedNumbers.get(ii).startsWith(sortedNumbers.get(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    private String format(String input) {
        return input.replace(" ", "");
    }

}
