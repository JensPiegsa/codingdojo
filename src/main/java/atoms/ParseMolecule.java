package atoms;

import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"ImplicitNumericConversion", "StringConcatenationInLoop", "NonConstantStringShouldBeStringBuffer"})
class ParseMolecule {

    private static final Map<Character, Character> matchingParentheses = Map.of('(',')','[',']');

    private final String formula;
    private String atom = "";
    private String count = "";
    private final Map<String, Integer> atoms = new HashMap<>();
    private Map<String, Integer> subResult;

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
                    postCompute();
                }
                atom += c;
            } else if (Character.isDigit(c)) {
                count += c;
            } else if (c == '(' || c == '[') {
                postCompute();
                final char ch = matchingParentheses.get(c);
                final int positionOfClosingParenthesis = formula.indexOf(ch, i);
                final String subFormula = formula.substring(i + 1, positionOfClosingParenthesis);
                subResult = getAtoms(subFormula);
                i = positionOfClosingParenthesis;
            }
        }

        postCompute();

        if (Character.isLowerCase(formula.charAt(0))) {
            throw new IllegalArgumentException("atom symbols must start with uppercase letter");
        }

        return atoms;
    }

    private void postCompute() {
        if (!atom.isEmpty() || subResult != null) {
            addCountResult();
        }
    }

    private void addCountResult() {

        if (subResult == null) {
            atoms.put(atom, count());
            atom = "";
        } else {
            mergeAtoms(subResult, count());
            subResult = null;
        }
        count = "";
    }

    private void mergeAtoms(final Map<String, Integer> subResult, final int count) {
        for (final String element : subResult.keySet()) {
            final int oldCount = atoms.getOrDefault(element, 0);
            final int added = subResult.get(element);
            final int combinedCount = oldCount + added * count;
            atoms.put(element, combinedCount);
        }
    }

    private int count() {
        return count.isEmpty() ? 1 : Integer.parseInt(count);
    }
}