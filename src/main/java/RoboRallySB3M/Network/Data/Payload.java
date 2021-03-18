package RoboRallySB3M.Network.Data;

import java.io.Serializable;

public class Payload implements Serializable {

    public PayloadAction action;
    public PayloadData data;

    public static Payload create(PayloadAction action) {
        return new Payload(action, null);
    }

    public static Payload create(PayloadAction action, PayloadData data) {
        return new Payload(action, data);
    }

    private Payload(PayloadAction action, PayloadData data) {
        this.action = action;
        this.data = data;
    }

    public boolean hasData() {
        return data != null;
    }
}
