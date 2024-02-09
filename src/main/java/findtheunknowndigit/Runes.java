package findtheunknowndigit;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runes {

    public static int solveExpression(final String expression) {

        for (int i = 0; i <= 9; i++) {
			if (!containsDigit(expression, i) 
					&& evaluateExpression(replaceQuestionMark(expression, i))) {
				return i;
			}
		}

        return -1;
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
		final String firstOperandString = matcher.group(1);
		final int firstOperand = Integer.parseInt(firstOperandString);
		final String operator = matcher.group(2);
		final String secondOperandString = matcher.group(3);
		final int secondOperand = Integer.parseInt(secondOperandString);
		final String resultString = matcher.group(4);
		final int result = Integer.parseInt(resultString);

		if ((firstOperandString.startsWith("0") && !firstOperandString.equals("0"))
			|| (secondOperandString.startsWith("0") && !secondOperandString.equals("0"))
			|| (resultString.startsWith("0") && !resultString.equals("0"))
			|| (firstOperandString.startsWith("-0"))
			|| (secondOperandString.startsWith("-0"))
			|| (resultString.startsWith("-0"))) {
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

	static boolean containsDigit(final String expression, final int digit) {
		return expression.contains(String.valueOf(digit));
	}
}