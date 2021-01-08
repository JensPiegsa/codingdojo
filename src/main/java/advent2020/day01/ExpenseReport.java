package advent2020.day01;

import java.util.ArrayList;
import java.util.List;

public class ExpenseReport {
	
	public int calculateProduct(final int sum, final List<Integer> expenses) {

		final int second = expenses.stream()
				.map(expense -> sum - expense)
				.filter(expenses::contains)
				.findAny()
				.orElseThrow(IllegalArgumentException::new);

		return second * (sum - second);
	}

	public int calculateProductOfThree(final int sum, final List<Integer> expenses) {
		
		for (int a : expenses) {
			int bPlusC = sum - a;

			final List<Integer> remainingExpenses = new ArrayList<>(expenses);
			remainingExpenses.remove(Integer.valueOf(a));
			
			final Integer c = remainingExpenses.stream()
					.map(expense -> bPlusC - expense)
					.filter(expenses::contains)
					.findAny()
					.orElse(null);
			
			if (c != null) {
				return a * c * (bPlusC - c);
			}
		}
		return 0;
	}
}
