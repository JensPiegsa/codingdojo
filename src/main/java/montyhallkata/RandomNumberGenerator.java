package montyhallkata;

import java.util.Random;

/**
 * @author Jens Piegsa
 */
public class RandomNumberGenerator {
    
    public int nextInt(final int bound) {
        return new Random().nextInt(bound);
    }
}
