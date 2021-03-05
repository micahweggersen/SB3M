package inf112.skeleton.app.Cards;

public class UTurn extends Cards {

    /** See Card class
     * @param priorityValue int
     * @param id String
     * @param direction direction
     * @param momentum int
     */
    
    public UTurn(int priorityValue, String id, int direction, int momentum) {
        super(priorityValue, "U-Turn", ROTATE_180, 0);
    }
}
