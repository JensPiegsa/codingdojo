package tcp;

/**
 * @author Jens Piegsa
 */
public enum TcpSymbol implements Symbol {
    APP_ACTIVE_OPEN,
    APP_PASSIVE_OPEN,
    APP_SEND,
    RCV_SYN_ACK,
    RCV_SYN,
    APP_CLOSE,
    RCV_ACK,
    RCV_FIN,
    RCV_FIN_ACK,
    APP_TIMEOUT
}
