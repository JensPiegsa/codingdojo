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
        int y = 0;
        int z = 0;
        int counterY = 0;
        int counterZ = 0;
        while (result.size() <= n + 1) {
            if (y >= z) {
                int x = result.get(counterZ);
                z = z(x);
                if (!result.contains(z)) {
                    result.add(z);
                }
                counterZ++;
            }
            if (y <= z){
                int x = result.get(counterY);
                y = y(x);
                if (!result.contains(y)) {
                    result.add(y);
                }
                counterY++;
            }
            Collections.sort(result);
        }
        return result.get(n);
    }
}