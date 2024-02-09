package findtheunknowndigit;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runes {

    public static int solveExpression(final String expression) {

        final int missingDigit = -1;

        //Write code to determine the missing digit or unknown rune
        //Expression will always be in the form
        //(number)[operator](number)=(number)
        //Unknown digit will not be the same as any other digits used in expression

		for (int i = 0; i <= 9; i++) {
			if (evaluateExpression(replaceQuestionMark(expression, i))) {
				return i;
			}
		}


        return missingDigit;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
	public static boolean evaluateExpression(final String expression) {
		final Pattern pattern = Pattern.compile("(-?[0-9]+)" +
												"([+*-])" +
												"(-?[0-9]+)" +
												"=" +
												"(-?[0-9]+)");
		final Matcher matcher = pattern.matcher(expression);
		matcher.find();
		// https://stackoverflow.com/questions/8938498/get-the-index-of-a-pattern-in-a-string-using-regex/8938549#8938549
		String firsOperandString = matcher.group(1);
		final int firstOperand = Integer.parseInt(firsOperandString);
		final String operator = matcher.group(2);
		String secondOperandString = matcher.group(3);
		final int secondOperand = Integer.parseInt(secondOperandString);
		String resultString = matcher.group(4);
		final int result = Integer.parseInt(resultString);

		if (firsOperandString.startsWith("00")
			|| secondOperandString.startsWith("00")
			|| resultString.startsWith("00")) {
			return false;
		}

		return switch (operator) {
			case "+" -> firstOperand + secondOperand == result;
			case "-" -> firstOperand - secondOperand == result;
			case "*" -> firstOperand * secondOperand == result;
			default -> throw new IllegalArgumentException("unexpected: " + expression);
		};
    }

	public static String replaceQuestionMark(final String expressionWithQuestionMark, final int digitValue) {
		return expressionWithQuestionMark.replace("?", String.valueOf(digitValue));
	}
}