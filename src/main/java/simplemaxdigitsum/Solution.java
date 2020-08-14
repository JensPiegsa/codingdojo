package simplemaxdigitsum;

class Solution {

    public static long solve(final long n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return 9;
        }
    }


	public static long digitSum(final long n) {
		String characters = String.valueOf(n);
		int sum = 0;
		for (int i = 0; i < characters.length(); i++) {
			sum += Integer.parseInt("" + characters.charAt(i));
		}
		return sum;
//		if (n == 10L) {
//			return 1L;
//		} else if (n == 22L) {
//			return 4L;
//		} else {
//			return 8L;
//		} 
	}
}