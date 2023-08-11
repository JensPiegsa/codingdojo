package tcp;

/**
 * @author Jens Piegsa
 */
public enum TcpState {
    
    SYN_SENT,
    CLOSED,
    ESTABLISHED,
    LISTEN,
    SYN_RCVD,
    FIN_WAIT_1,
    CLOSE_WAIT,
    CLOSING,
    TIME_WAIT,
    FIN_WAIT_2,
    LAST_ACK
}
