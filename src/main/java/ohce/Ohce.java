package ohce;

/**
 * @author Jens Piegsa
 */
public class Ohce {

    Greeter greeter;
    InputReverser inputReverser;
    Saluter saluter;

    private final String name;

    public Ohce(final Greeter greeter, final InputReverser inputReverser, final Saluter saluter, final String name) {
        this.greeter = greeter;
        this.inputReverser = inputReverser;
        this.saluter = saluter;
        this.name = name;
    }
    
    public static void main(final String[] args) {
        final String name = args[0];
        new Ohce(null, new InputReverser(), null, name).start();
    }

    public void start() {
        greeter.greet(name);
        inputReverser.reverse();
        saluter.salute(name);
    }

}
