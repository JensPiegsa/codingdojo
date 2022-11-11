package mumbling;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("A Accumul")
class AccumulTest {

	@Nested @DisplayName("own")
	class Own {

		@Test @DisplayName("one letter to upper case.")
		void oneLetterToUpperCase() {
			assertThat(Accumul.accum("a")).isEqualTo("A");
		}

		@Test @DisplayName("two letters.")
		void twoLetters() {
			assertThat(Accumul.accum("bo")).isEqualTo("B-Oo");
		}
	}
	@Nested @DisplayName("acceptance")
	class Acceptance {

		private static void testing(String actual, String expected) {
			assertEquals(expected, actual);
		}
		
		@Test
		public void test() {
			System.out.println("Fixed Tests accum");
			testing(Accumul.accum("ZpglnRxqenU"), "Z-Pp-Ggg-Llll-Nnnnn-Rrrrrr-Xxxxxxx-Qqqqqqqq-Eeeeeeeee-Nnnnnnnnnn-Uuuuuuuuuuu");
			testing(Accumul.accum("NyffsGeyylB"), "N-Yy-Fff-Ffff-Sssss-Gggggg-Eeeeeee-Yyyyyyyy-Yyyyyyyyy-Llllllllll-Bbbbbbbbbbb");
			testing(Accumul.accum("MjtkuBovqrU"), "M-Jj-Ttt-Kkkk-Uuuuu-Bbbbbb-Ooooooo-Vvvvvvvv-Qqqqqqqqq-Rrrrrrrrrr-Uuuuuuuuuuu");
			testing(Accumul.accum("EvidjUnokmM"), "E-Vv-Iii-Dddd-Jjjjj-Uuuuuu-Nnnnnnn-Oooooooo-Kkkkkkkkk-Mmmmmmmmmm-Mmmmmmmmmmm");
			testing(Accumul.accum("HbideVbxncC"), "H-Bb-Iii-Dddd-Eeeee-Vvvvvv-Bbbbbbb-Xxxxxxxx-Nnnnnnnnn-Cccccccccc-Ccccccccccc");
		}
		
	}
}