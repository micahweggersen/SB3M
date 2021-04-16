package RoboRallySB3M.Network.Data;

import java.io.Serializable;

public class Payload implements Serializable {

    public PayloadAction action;
    public PayloadData data;

    /**
     * @param action Keyword to decide action
     * @return Payload action
     */
    public static Payload create(PayloadAction action) {
        return new Payload(action, null);
    }

    /**
     * @param action Keyword to decide action
     * @param data to be used with keyword
     * @return Payload action and date for that action
     */
    public static Payload create(PayloadAction action, PayloadData data) {
        return new Payload(action, data);
    }

    private Payload(PayloadAction action, PayloadData data) {
        this.action = action;
        this.data = data;
    }

    /**
     * @return checks if payload has a value
     * Not finished implementing and will be used later
     */
    public boolean hasData() {
        return data != null;
    }
}
