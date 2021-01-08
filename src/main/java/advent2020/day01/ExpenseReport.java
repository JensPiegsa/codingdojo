package advent2020.day01;

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
}
