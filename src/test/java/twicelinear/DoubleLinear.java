package twicelinear;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DoubleLinear {

    public static int dblLinear(final int n) {
        return u(n);
    }

    public static int y(final int x) {
        return 2 * x + 1;
    }

    public static int z(final int x) {
        return 3 * x + 1;
    }

    public static int u(final int n) {
        final List<Integer> result = new ArrayList<>();
        result.add(1);

        if (n == 0) {
            return result.get(n);
        }
        int counter = 0;
        while (result.size() <= n) {
            int x = result.get(counter);
            int y = y(x);
            int z = z(x);
            if (!result.contains(y)) {
                result.add(y);
            }
            if (!result.contains(z)) {
                result.add(z);
            }
            counter++;
        }
        Collections.sort(result);
        return result.get(n);
    }
}