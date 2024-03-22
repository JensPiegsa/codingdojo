package ohce;

/**
 * @author Jens Piegsa
 */
public class Ohce {
    Greeter greeter;
    InputReverser inputReverser;
    
    public static void main(final String[] args) {
        new Ohce().start();
    }

    public void start() {
        inputReverser.reverser();
    }

}
