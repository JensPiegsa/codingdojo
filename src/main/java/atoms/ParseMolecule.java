package atoms;

import java.util.Map;
import java.util.HashMap;

class ParseMolecule {
    
    public static Map<String,Integer> getAtoms(final String formula) {

        final var atoms = new HashMap<String, Integer>();
        
        String atom = "";
        String count = "";

        for (int i = 0; i < formula.length(); i++) {
            final char c = formula.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    if (!atom.isEmpty()) {
                        atoms.put(atom, count.isEmpty() ? 1 : Integer.parseInt(count));
                        atom = "";
                        count = "";
                    }
                }

                atom += c;
            } else if (Character.isDigit(c)) {
                count += c;
            }
        }

        if (!atom.isEmpty()) {
            atoms.put(atom, count.isEmpty() ? 1 : Integer.parseInt(count));
        }

        if (Character.isLowerCase(formula.charAt(0))) {
            throw new IllegalArgumentException("atom symbols must start with uppercase letter");
        }

        return atoms;
    }
}