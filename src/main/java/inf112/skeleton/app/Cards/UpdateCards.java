package inf112.skeleton.app.Cards;

public abstract class UpdateCards {
    private int value;
    private String id;

    /**
     * Constructor. Used by sub-classes.
     * @param value The value that a card has in order to decide priority of the card
     * @param id The id/type of the card
     */
    UpdateCards(int value, String id){
        this.value = value;
        this.id = id;
    }

    //Getters

    public int getValue() {
        return value;
    }


    //Setters
}
