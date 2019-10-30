package phonenumber;

import java.util.Map;

public interface PhoneNumberCheckable {

    boolean isConsistent(final Map<String, String> phoneNumbers);

}
