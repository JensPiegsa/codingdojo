package versions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.*;
import java.lang.reflect.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("A VersionManager")
public class VersionManagerTest {

	// https://www.codewars.com/kata/5bc7bb444be9774f100000c3/train/java

	@Test
	@DisplayName("'s version defaults to 0.0.1")
	void sVersionDefaultsTo001() {
		assertThat(new VersionManager().release()).isEqualTo("0.0.1");
	}

	@Test
	@DisplayName("can start with given version.")
	void canStartWithGivenVersion() {
		assertThat(new VersionManager("1.0.0").release()).isEqualTo("1.0.0");
	}

	@Test
	@DisplayName("works with only a given major segment.")
	void worksWithOnlyAGivenMajorSegment() {
		assertThat(new VersionManager("1").release()).isEqualTo("1.0.0");
	}

	@Test @DisplayName("can patch by incrementing third version segment.")
	void canPatchByIncrementingThirdVersionSegment() {
		assertThat(new VersionManager().patch().release()).isEqualTo("0.0.2");
	}
	
	@Test @DisplayName("can do minor release by incrementing second version segment.")
	void canDoMinorReleaseByIncrementingSecondVersionSegment() {
		assertThat(new VersionManager().minor().release()).isEqualTo("0.1.0");
	}

	@Test @DisplayName("can do major release by incrementing first version segment.")
	void canDoMajorReleaseByIncrementingFirstVersionSegment() {
		assertThat(new VersionManager().major().release()).isEqualTo("1.0.0");
	}

	@Test @DisplayName("can not rollback without previous version.")
	void canNotRollbackWithoutPreviousVersion() {
		try {
			new VersionManager().rollback();
			fail("Exception expected");
		} catch (Exception e) {
			assertThat(e).hasMessage("Error occured while parsing version!");
		}
	}
	
	@Test @DisplayName("can rollback to previous version.")
	void canRollbackToPreviousVersion() {
		assertThat(new VersionManager().major().rollback().release()).isEqualTo("0.0.1");
	}


	@Nested @DisplayName("acceptance tests")
	class AcceptanceTests {

		@Test
		public void initializationTests() {
			try {
				assertEquals("0.0.1", new VersionManager().release());
				assertEquals("0.0.1", new VersionManager("").release());
				assertEquals("1.2.3", new VersionManager("1.2.3").release());
				assertEquals("1.2.3", new VersionManager("1.2.3.4").release());
				assertEquals("1.2.3", new VersionManager("1.2.3.d").release());
				assertEquals("1.0.0", new VersionManager("1").release());
				assertEquals("1.1.0", new VersionManager("1.1").release());
			} catch (Exception e) {
				fail();
			}
		}

		@Test
		public void majorReleasesTests() {
			try {
				assertEquals("1.0.0", new VersionManager().major().release());
				assertEquals("2.0.0", new VersionManager("1.2.3").major().release());
				assertEquals("3.0.0", new VersionManager("1.2.3").major().major().release());
			} catch (Exception e) {
				fail();
			}
		}

		@Test
		public void minorReleasesTests() {
			try {
				assertEquals("0.1.0", new VersionManager().minor().release());
				assertEquals("1.3.0", new VersionManager("1.2.3").minor().release());
				assertEquals("1.1.0", new VersionManager("1").minor().release());
				assertEquals("4.2.0", new VersionManager("4").minor().minor().release());
			} catch (Exception e) {
				fail();
			}
		}

		@Test
		public void patchReleasesTests() {
			try {
				assertEquals("0.0.2", new VersionManager().patch().release());
				assertEquals("1.2.4", new VersionManager("1.2.3").patch().release());
				assertEquals("4.0.2", new VersionManager("4").patch().patch().release());
			} catch (Exception e) {
				fail();
			}
		}

		@Test
		public void rollbacksTests() {
			try {
				assertEquals("0.0.1", new VersionManager().major().rollback().release());
				assertEquals("0.0.1", new VersionManager().minor().rollback().release());
				assertEquals("0.0.1", new VersionManager().patch().rollback().release());
				assertEquals("1.0.0", new VersionManager().major().patch().rollback().release());
				assertEquals("1.0.0", new VersionManager().major().patch().rollback().major().rollback().release());
			} catch (Exception e) {
				fail();
			}
		}

		@Test
		public void seperatedCallsTests() {
			try {
				VersionManager m = new VersionManager("1.2.3");
				m.major();
				m.minor();
				assertEquals("2.1.0", m.release());
			} catch (Exception e) {
				fail();
			}
		}

		@Test
		public void exceptionsTests() {
			try {
				VersionManager m = new VersionManager("a.b.c");
				fail("Expected an Exception to be thrown");
			} catch (Exception e) {
				assertEquals("Error occured while parsing version!", e.getMessage());
			}
			try {
				VersionManager m = new VersionManager().rollback();
				fail("Expected an Exception to be thrown");
			} catch (Exception e) {
				assertEquals("Cannot rollback!", e.getMessage());
			}
		}
	}
}
