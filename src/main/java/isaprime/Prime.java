package isaprime;

public class Prime {
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        int potentialDivisor = number - 1;
        while (potentialDivisor > 1) {
            if (isDivisible(number, potentialDivisor)) {
                return false;
            }
            potentialDivisor--;
        }
        return true;
    }

    private static boolean isDivisible(int number, int potentialDivisor) {
        return number % potentialDivisor == 0;
    }
}