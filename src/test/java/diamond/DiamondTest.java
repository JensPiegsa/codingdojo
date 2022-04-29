package diamond;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

class DiamondTest {

	// https://hackernoon.com/property-based-testing-the-diamond-kata-first-and-last-line-content-2h1x3tpf
	
	/*
	input: E
	
	----A----
	---B-B---
	--C---C--
	-D-----D-
	E-------E
	-D-----D-
	--C---C--
	---B-B---
	----A----
	
	more properties:
	column count == row count
	column count == letter - 'A' * 2 + 1
	top corner at [0, letter - 'A'] container 'A'
	bottom corner at [letter - 'A' * 2, letter - 'A'] contains 'A'
	right corner [letter - 'A', letter - 'A' * 2] contains letter
	diamond has expected shape, filling and background (-)
	diagonals
	*/

	@Property
	boolean firstLineContainsLetterA(@ForAll("letters") char letter) {
		return Diamond.create(letter).get(0).contains("A");
	}
	
	@Property
	boolean leftCornerContainsLetter(@ForAll("letters") char letter) {
		final int lineIndexOfLetter = letter - 'A';
		return Diamond.create(letter).get(lineIndexOfLetter).charAt(0) == letter;  
	}
	
//	@Property
//	boolean
	
	
	@Provide Arbitrary<Character> letters() {
		return Arbitraries.chars().range('A', 'Z');
	}
}