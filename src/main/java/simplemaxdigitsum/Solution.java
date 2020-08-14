package simplemaxdigitsum;

class Solution {

    public static long solve(final long n) {
    	long largestDigitSum=0;
    	for(long i=n;i>=0;i--){
    		if(digitSum(i)>largestDigitSum) {
				largestDigitSum = digitSum(i);
			}
		}
    	return largestDigitSum;
        /*if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return 9;
        }*/
    }


	public static long digitSum(final long n) {
		String characters = String.valueOf(n);
		long sum = 0;
		for (int i = 0; i < characters.length(); i++) {
			sum += Integer.parseInt("" + characters.charAt(i));
		}
		return sum;
	}
}