package simplemaxdigitsum;

class Solution {

	public static long solve(final long n) {
		// DRY: Dont repeat yourself
		long largestDigitSum = 0L;
		long numberWithLargestDigitSum = n;
		for (long i = n; i >= 0L; i--) {
			final long digitSum = digitSum(i);
			if (digitSum > largestDigitSum) {
				largestDigitSum = digitSum;
				numberWithLargestDigitSum = i;
			}
		}
		return numberWithLargestDigitSum;
	}

	public static long digitSum(final long n) {
		final String characters = String.valueOf(n);
		long sum = 0L;
		for (int i = 0; i < characters.length(); i++) {
			sum += characters.charAt(i) - '0';
		}
		return sum;
	}
}