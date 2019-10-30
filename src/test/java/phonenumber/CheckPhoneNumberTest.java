package phonenumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class CheckPhoneNumberTest {

	final CheckPhoneNumber checkPhoneNumber = new CheckPhoneNumber();


	@Test
	public void testIsConsistentIsFalse() throws Exception {

		// given
		final Map<String, String> phoneNumbers = new HashMap<>();
		phoneNumbers.put("Bob", "91 12 54 26");
		phoneNumbers.put("Alice", "97 625 992");
		phoneNumbers.put("Emergency", "911");

		// when
		final boolean result = checkPhoneNumber.isConsistent(phoneNumbers.values());

		// then
		assertThat(result).isFalse();
	}

	@Test
	public void testIsConsistentIsTrue() throws Exception {

		// given
		final Map<String, String> phoneNumbers = new HashMap<>();
		phoneNumbers.put("Bob", "91 12 54 26");
		phoneNumbers.put("Alice", "97 625 992");

		// when
		final boolean result = checkPhoneNumber.isConsistent(phoneNumbers.values());

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void testIsConsistentEmptyMap() throws Exception {

		// given
		final Map<String, String> phoneNumbers = new HashMap<>();

		// when
		final boolean result = checkPhoneNumber.isConsistent(phoneNumbers.values());

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void testIsConsistentBigData() throws Exception {

		// given
		List<String> phoneNrList=Files.readAllLines(Paths.get(this.getClass().getResource("phone.txt").toURI()));
		final List<String> phoneNumbers = phoneNrList.stream().skip(1).map(line -> line.substring(line.indexOf(",")+1)).collect(Collectors.toList());
		// when
		final boolean result = checkPhoneNumber.isConsistent(phoneNumbers);

		// then
		assertThat(result).isFalse();
	}
}
