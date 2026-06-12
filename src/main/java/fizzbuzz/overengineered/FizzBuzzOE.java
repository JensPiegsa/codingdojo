package fizzbuzz.overengineered;

public class FizzBuzzOE {

	private String fizzBuzz = "FizzBuzz";

	public FizzBuzzOE(String fizzBuzz) {
		this.fizzBuzz = fizzBuzz;
	}

	public String run(final int i) {

		if (i % 3 == 0 && i % 5 == 0) {
			return this.fizzBuzz;
		}
		if (i % 3 == 0) {
			return "Fizz";
		}
		if (i % 5 == 0) {
			return "Buzz";
		}
		return String.valueOf(i);
	}
}
