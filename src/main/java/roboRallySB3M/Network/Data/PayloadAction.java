package roboRallySB3M.Network.Data;

/**
 * Keywords to decide the action on server side or client side
 */
public enum PayloadAction {
    UPDATE,
    MOVE,
    CARD,
    QUIT,
    JOIN,
    ERROR,
    SUCCESS,
    DISCONNECT,
    NOT_YOUR_TURN
}
