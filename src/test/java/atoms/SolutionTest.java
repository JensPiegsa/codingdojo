package atoms;

import java.util.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.JUnit4;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


class SolutionTest {

	static Collection<Object[]> data() {
        
		return Arrays.asList(new Object[][]{{
                Arrays.asList("H", "O"),
                Arrays.asList(2, 1),
                "H2O",
                "water"},

                {Arrays.asList("Mg", "H", "O"),
                        Arrays.asList(1, 2, 2),
                        "Mg(OH)2",
                        "magnesium hydroxide"},

                {Arrays.asList("K", "O", "N", "S"),
                        Arrays.asList(4, 14, 2, 4),
                        "K4[ON(SO3)2]2",
                        "Fremy's salt"},
        });
    }

	@ParameterizedTest
	@MethodSource("data")
	public void testMolecule(final List<String> atoms, final List<Integer> nums, final String formula, final String name) {
		final Map<String, Integer> expected = new HashMap<>();
		for (int i = 0; i < atoms.size(); i++) {
			expected.put(atoms.get(i), nums.get(i));
		}

		System.out.println(expected);
		assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));
	}
}
