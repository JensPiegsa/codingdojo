package phonenumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class CheckPhoneNumberTest {

	@Test
	public void testIsConsistentIsFalse() throws Exception {

		// given
		final Map<String, String> phoneNumbers = new HashMap<>();
		phoneNumbers.put("Bob", "91 12 54 26");
		phoneNumbers.put("Alice", "97 625 992");
		phoneNumbers.put("Emergency", "911");

		final CheckPhoneNumber checkPhoneNumber = new CheckPhoneNumber();

		// when
		final boolean result = checkPhoneNumber.isConsistent(phoneNumbers);

		// then
		assertThat(result).isFalse();
	}

	@Test
	public void testIsConsistentIsTrue() throws Exception {

		// given
		final Map<String, String> phoneNumbers = new HashMap<>();
		phoneNumbers.put("Bob", "91 12 54 26");
		phoneNumbers.put("Alice", "97 625 992");

		final CheckPhoneNumber checkPhoneNumber = new CheckPhoneNumber();

		// when
		final boolean result = checkPhoneNumber.isConsistent(phoneNumbers);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void testIsConsistentEmptyMap() throws Exception {

		// given
		final Map<String, String> phoneNumbers = new HashMap<>();

		final CheckPhoneNumber checkPhoneNumber = new CheckPhoneNumber();

		// when
		final boolean result = checkPhoneNumber.isConsistent(phoneNumbers);

		// then
		assertThat(result).isTrue();
	}
}
