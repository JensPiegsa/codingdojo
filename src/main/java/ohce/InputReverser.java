package ohce;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InputReverser {
    public void reverser() {
        final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        final String nextLine = scanner.nextLine();

        final String reversed = StringUtils.reverse(nextLine);
        System.out.println(reversed);
    }
}
