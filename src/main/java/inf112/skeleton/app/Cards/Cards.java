package inf112.skeleton.app.Cards;

import java.util.ArrayList;

public class Cards {
    private int priorityValue;
    private String id;
    private int momentum;
    private int direction;

    public static final int ROTATE_0 = 0;
    public static final int ROTATE_90 = 1;
    public static final int ROTATE_180 = 2;
    public static final int ROTATE_270 = 3;
    
    /**
     * Constructor. Used by sub-classes.
     * @param priorityValue The value that a card has in order to decide priority of the card at start of each round.
     * @param id The id/type of the card
     */
    public Cards(int priorityValue, String id, int direction, int momentum){

        this.momentum = momentum;
        this.direction = direction;

        this.priorityValue = priorityValue;
        this.id = id;
//        this.hiddenStatus = false;
//        this.lockedStatus = false;
    }
    
    //dem fikse en queue/giveNextcard metode
    //lager en queue med et kort av hver type
    //Getters
    /**
     * Gets the priority value a card has.
     * @return the priority value
     */
    public int getPriorityValue() {
        return priorityValue;
    }

    /**
     * Retrieves the message on the card. Tells the console what to print.
     * @return the message on the card
     */
//    public String getMessage(){
//        return message;
//    }

    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return "UpdateCards{" +
                "id=" + id + "priorityValue=" + priorityValue +
                '}';
    }

    /** Returns the momentum of the given card
     * @return momentum of gard
     */
    public int getMomentum(){
        return momentum;
    }

    public int getDirection(){
        return direction;
    }

}
