package descendingorder;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("A DescendingOrder")
class DescendingOrderTest {

	@Nested @DisplayName("own")
	class Own {
		
		@Test @DisplayName("test")
		void test() {
			assertThat(DescendingOrder.sortDesc(1)).isEqualTo(1);
		}
		
		@DisplayName("test2") 
		@ParameterizedTest(name = "{0} -> {1}")
		@CsvSource({
				"1,1", 
				"2,2",
				"12,21"})
		void test(final int input, final int expectedOutput) {
			assertThat(DescendingOrder.sortDesc(input)).isEqualTo(expectedOutput);
		}
	}
	
	@Nested
	@DisplayName("acceptance")
	class Acceptance {

		@Test
		public void test_01() {
			assertEquals(0, DescendingOrder.sortDesc(0));
		}

		@Test
		public void test_02() {
			assertEquals(51, DescendingOrder.sortDesc(15));
		}


		@Test
		public void test_03() {
			assertEquals(987654321, DescendingOrder.sortDesc(123456789));
		}
	}
}