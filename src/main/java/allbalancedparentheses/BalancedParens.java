package allbalancedparentheses;

import java.util.ArrayList;
import java.util.List;

public class BalancedParens {
    
    public static List<String> balancedParens(final int n) {
        return buildRecursively("", n, n);
    }

    private static List<String> buildRecursively(final String head,
                                                 final int remainingOpen,
                                                 final int remainingClosed) {
        
        final List<String> configurations = new ArrayList<>();

        if (remainingOpen > 0) {
            configurations.addAll(buildRecursively(head + "(", remainingOpen - 1, remainingClosed));
        }
        if (remainingClosed > 0 && remainingClosed > remainingOpen) {
            configurations.addAll(buildRecursively(head + ")", remainingOpen, remainingClosed - 1));
        }
        if (remainingOpen == 0 && remainingClosed == 0) {
            configurations.add(head);
        }

        return configurations;
    }
}