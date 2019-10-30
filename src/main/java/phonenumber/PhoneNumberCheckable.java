package phonenumber;

import java.util.Collection;
import java.util.Map;

public interface PhoneNumberCheckable {

    boolean isConsistent(final Collection<String> phoneNumbers);

}
