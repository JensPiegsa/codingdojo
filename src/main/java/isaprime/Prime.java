package isaprime;

public class Prime {
    public static boolean isPrime(int num) {
        int index = num;
        boolean isPrime = true;
        while (index >= 1) {
            index--;
            if (num % index == 0) {
                isPrime = false;
                break;
            }

        }
        return isPrime;
    }
}