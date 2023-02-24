package twicelinear;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Consider a sequence u where u is defined as follows:
 *
 *     The number u(0) = 1 is the first one in u.
 *     For each x in u, then y = 2 * x + 1 and z = 3 * x + 1 must be in u too.
 *     There are no other numbers in u.
 *
 * Ex: u = [1, 3, 4, 7, 9, 10, 13, 15, 19, 21, 22, 27, ...]
 *
 * 1 gives 3 and 4, then 3 gives 7 and 10, 4 gives 9 and 13, then 7 gives 15 and 22 and so on...
 * Task:
 *
 * Given parameter n the function dbl_linear (or dblLinear...) returns the element u(n) of the ordered (with <) sequence u (so, there are no duplicates).
 * Example:
 *
 * dbl_linear(10) should return 22
 * Note:
 *
 * Focus attention on efficiency
 */
public class DoubleLinearTest {
    
    @Test @DisplayName("test y()")
    void testY() {
        final var x = 1;
        final var y = DoubleLinear.y(x);
        then(y).isEqualTo(3);        
    }

    @Test @DisplayName("test z()")
    void testZ() {
        final var x = 1;
        final var z = DoubleLinear.z(x);
        then(z).isEqualTo(4);
    }

    @Test @DisplayName("test u(0) = 1")
    void testU01() {
        final var n = 0;
        final var u = DoubleLinear.u(n);
        then(u).isEqualTo(1);
    }
    
    @Test @DisplayName("dblLinear(3)")
    void dblLinear3() {
        final var n = 3;
        final var u = DoubleLinear.dblLinear(n);
        then(u).isEqualTo(7);
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests dblLinear");
        testing(DoubleLinear.dblLinear(10), 22);
        testing(DoubleLinear.dblLinear(20), 57);
        testing(DoubleLinear.dblLinear(30), 91);
        testing(DoubleLinear.dblLinear(50), 175);
    }

    private static void testing(final int actual, final int expected) {
        Assertions.assertEquals(expected, actual);
    }
}
