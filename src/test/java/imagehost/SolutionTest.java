package imagehost;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import imagehost.Solution.NameGenerator;
import net.jqwik.api.Property;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * <a href="https://www.codewars.com/kata/586a933fc66d187b6e00031a/train/java">https://www.codewars.com/kata/586a933fc66d187b6e00031a/train/java</a>
 */
@DisplayName("A Solution")
@ExtendWith(MockitoExtension.class)
class SolutionTest {

	@Mock PhotoManager photoManager;
	@Mock NameGenerator instance;
	private NameGenerator nameGenerator;

	@BeforeEach
	void init() {
		nameGenerator = new NameGenerator(new Random(1));
	}

	// jqwick: unique items ??
	@Property(tries = 1000)
	boolean nameHasAlwaysSizeOfSix() {
		final String name = Solution.generateName(photoManager);
		return name != null && name.length() == 6;
	}

	@Test
	@Disabled
	@DisplayName("generated name has expected length")
	void generatedNameHasExpectedLength() {
		final String name = Solution.generateName(photoManager);
		then(name).hasSize(6);
	}

	@Test
	@Disabled("does not work")
	public void test() {
		PhotoManager photoManager = new PhotoManager();
		for (int i = 0; i < 10; i++) {
			String name = Solution.generateName(photoManager);
			assertTrue(photoManager.nameWasUnique(name));
			assertEquals(6, name.length());
		}
	}
}