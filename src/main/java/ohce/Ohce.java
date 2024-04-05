package ohce;

/**
 * @author Jens Piegsa
 */
public class Ohce {
    Greeter greeter;
    InputReverser inputReverser;

    public Ohce(Greeter greeter, InputReverser inputReverser) {
        this.greeter = greeter;
        this.inputReverser = inputReverser;
    }
    
    public static void main(final String[] args) {
        new Ohce(null, new InputReverser()).start();
    }

    public void start() {
        inputReverser.reverser();
    }

}
