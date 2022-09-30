package atoms;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * https://www.codewars.com/kata/52f831fa9d332c6591000511/train/java
 */
@DisplayName("A Molecule parser")
class ParseMoleculeTest {

	@Test @DisplayName("raises exception for wrong case.")
	void raisesExceptionForWrongCase() {
		assertThatThrownBy(() -> ParseMolecule.getAtoms("h"))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test @DisplayName("returns expected map for single-letter atom.")
	void returnsExpectedMapForSingleLetterAtom() {
		assertThat(ParseMolecule.getAtoms("H"))
				.hasSize(1)
				.contains(entry("H", 1));
	}
	
	@Test @DisplayName("returns expected map for two-letter atom.")
	void returnsExpectedMapForTwoLetterAtom() {
		assertThat(ParseMolecule.getAtoms("Hg"))
				.hasSize(1)
				.contains(entry("Hg", 1));
	}

	@Test @DisplayName("returns map of size two for two atom molecule.")
	void returnMapOfSizeTwoForTwoAtomMolecule() {
		assertThat(ParseMolecule.getAtoms("NaCl"))
				.hasSize(2)
				.contains(entry("Na", 1), entry("Cl", 1));
	}

	@Test @DisplayName("returns map of size two for two atoms single-letter and two-letter.")
	void returnMapOfSizeTwoForTwoAtomsSingleLetterAndTwoLetter() {
		assertThat(ParseMolecule.getAtoms("HCl"))
				.hasSize(2)
				.contains(entry("H", 1), entry("Cl", 1));
	}

	@Test @DisplayName("returns map of size two for two atoms two-letter and single-letter.")
	void returnsMapOfSizeTwoForTwoAtomsTwoLetterAndSingleLetter() {
		assertThat(ParseMolecule.getAtoms("MgO"))
				.hasSize(2)
				.contains(entry("Mg", 1), entry("O", 1));
	}
	
	@Test @DisplayName("returns map of size two for two single-letter atoms.")
	void returnsMapOfSizeTwoForTwoSingleLetterAtoms() {
		assertThat(ParseMolecule.getAtoms("CO"))
				.hasSize(2)
				.contains(entry("C", 1), entry("O", 1));
	}
	
	@Test @DisplayName("returns map of size one for molecule containing number.")
	void returnsMapOfSizeOneForMoleculeContainingNumber() {
		assertThat(ParseMolecule.getAtoms("H2"))
				.hasSize(1)
				.contains(entry("H", 2));
	}

	@Test @DisplayName("returns map of size two for tow atom molecule containing number.")
	void returnsMapOfSizeTwoForTwoAtomMoleculeContainingNumber() {
		assertThat(ParseMolecule.getAtoms("H2O"))
				.hasSize(2)
				.contains(entry("H", 2), entry("O", 1));
	}
	
	@Test @DisplayName("supports parentheses.")
	void supportsParentheses() {
		assertThat(ParseMolecule.getAtoms("Ca(OH)2"))
				.hasSize(3)
				.contains(
						entry("Ca", 1), 
						entry("O", 2),
						entry("H", 2));
	}

	@Test @DisplayName("supports complex parentheses.")
	void supportsComplexParentheses() {
		assertThat(ParseMolecule.getAtoms("Ca(OH)2(Mg)2"))
				.hasSize(4)
				.contains(
						entry("Ca", 1),
						entry("O", 2),
						entry("H", 2),
						entry("Mg", 2));
	}
	
	@Test @DisplayName("bugs")
	void bugs() {
		final String noNumberAfterParenthesis = "(C5H5)Fe(CO)2CH3";
		final String thirdTypeOfBraces1 = "As2{Be4C5[BCo3(CO2)3]2}4Cu5";
		final String thirdTypeOfBraces2 = "{[Co(NH3)4(OH)2]3Co}(SO4)3";
		// Should parse maleic acid: "C2H2(COOH)2"
		// Your function should throw an IllegalArgumentException for a wrong formula: Mg(OH
		// Your function should throw an IllegalArgumentException for a wrong formula: MgOH)2
		// Your function should throw an IllegalArgumentException for a wrong formula: Mg(OH]2
		// Your function should throw an IllegalArgumentException for a wrong formula: Au5(C2H5[OH)3Li]3
	}
}