package atoms;

import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"ImplicitNumericConversion", "StringConcatenationInLoop", "NonConstantStringShouldBeStringBuffer"})
class ParseMolecule {

    // goal: on open parenthesis: execute getAtoms with substring and merge resulting partial map multiplied by trailing number to parent map
    // TODO detect open parenthesis
    // TODO detect matching parenthesis
    // TODO extract substring
    // TODO execute recursion
    // TODO merge result map in finish step after parsing trailing number
    private final String formula;
    private String atom = "";
    private String count = "";
    private final Map<String, Integer> atoms = new HashMap<>();

    public ParseMolecule(final String formula) {
        this.formula = formula;
    }

    public static Map<String,Integer> getAtoms(final String formula) {

        return new ParseMolecule(formula).parse();

    }

    private Map<String, Integer> parse() {

        for (int i = 0; i < formula.length(); i++) {
            final char c = formula.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    if (!atom.isEmpty()) {
                        addCountResult();
                    }
                }

                atom += c;
            } else if (Character.isDigit(c)) {
                count += c;
            } else if (c == '(') {
                final int positionOfClosingParenthesis = formula.lastIndexOf(')');
                final String subFormula = formula.substring(i + 1, positionOfClosingParenthesis);
                // TODO store getAtoms of subFormula
                i = positionOfClosingParenthesis;
            }
        }

        if (!atom.isEmpty()) {
            addCountResult();
        }

        if (Character.isLowerCase(formula.charAt(0))) {
            throw new IllegalArgumentException("atom symbols must start with uppercase letter");
        }

        return atoms;
    }

    private void addCountResult() {
        atoms.put(atom, count.isEmpty() ? 1 : Integer.parseInt(count));
        atom = "";
        count = "";
    }
}