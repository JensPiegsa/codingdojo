package isaprime;

public class Prime {
	public static boolean isPrime(int num) {
		System.out.println("isPrime(" + num + "):");
		int index = num;
		boolean isPrime = true;
		while (index > 2) {
			index--;
			System.out.println("num: " + num + " index: " + index);
			if (num % index == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}
}