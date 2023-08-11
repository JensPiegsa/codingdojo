package tcp;

public class DFA<STATE, SYMBOL> {

    private final States<STATE> states;
    private final Alphabet<SYMBOL> alphabet;
    private final TransitionFunction<STATE, SYMBOL> transitionFunction;
    private final STATE initialState;
    private final States<STATE> acceptStates;
    
    private TcpState currentState = TcpState.CLOSED;

    public DFA(final States<STATE> states,
               final Alphabet<SYMBOL> alphabet,
               final TransitionFunction<STATE, SYMBOL> transitionFunction,
               final STATE initialState,
               final States<STATE> acceptStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitionFunction = transitionFunction;
        this.initialState = initialState;
        this.acceptStates = acceptStates;
    }

    public STATE process(final InputSymbols<SYMBOL> input) {
        STATE currentState = initialState;
        for (var symbol : input.getSymbols()) {
            currentState = transitionFunction.doTransition(currentState, symbol);
        }
        return currentState;
    }
}
