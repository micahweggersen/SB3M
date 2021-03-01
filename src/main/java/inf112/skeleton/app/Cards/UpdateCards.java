package inf112.skeleton.app.Cards;

import java.util.ArrayList;

public class UpdateCards {
    private int priorityValue;
    private String id;
    private String message;
    private boolean lockedStatus;
    private boolean hiddenStatus;
    private ArrayList<String> actionSequence = new ArrayList<>(); //skal sikkert ikke være en String

    /**
     * Constructor. Used by sub-classes.
     * @param priorityValue The value that a card has in order to decide priority of the card at start of each round.
     * @param id The id/type of the card
     */
    public UpdateCards(int priorityValue, String id){
        this.priorityValue = priorityValue;
        this.id = id;
        this.hiddenStatus = false;
        this.lockedStatus = false;
    }

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
    public String getMessage(){
        return message;
    }

    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return "UpdateCards{" +
                "id=" + id + "priorityValue=" + priorityValue +
                '}';
    }

    /**
     *
     * @return True if card is hidden
     */
    public boolean isHidden(){
        return hiddenStatus;
    }

    /**
     *
     * @return True is card is locked
     */
    public boolean isLocked(){
        return lockedStatus;
    }

    // get icon of card

    /*
    //get actions associated with card
    public ArrayList<String> getActionSequence(){
        ArrayList<String> move = new ArrayList<>();
            for(String action : actionSequence){
            }
            return move;
    }
     */

    //Setters

    //set action associated with card

    //set hidden
    public void setHidden(boolean x){
        hiddenStatus = x;
    }

    //set locked
    public void setLocked(boolean x){
        lockedStatus = x;
    }
}
