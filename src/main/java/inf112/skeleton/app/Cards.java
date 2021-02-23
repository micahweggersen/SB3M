package inf112.skeleton.app;

public class Cards {

    private String id;
    private int value;
    private int magnitude_of_direction;
//    private int interval;
//    private int number_of_cards_of_this_type;


    public Cards(String id, int value, int magnitude_of_direction){
        this.id = id;
        this.value = value;
        this.magnitude_of_direction = magnitude_of_direction;
    }

    public int get_magnitude_of_direction(){ return magnitude_of_direction; }
    public int get_value(){ return value; }
    public String get_id(){ return id; }

    public void changeFigureDirection(int[] coordinate, int direction){
        //[5,7]
        // 1-4 nord, øst, sør, vest


    }

    //gitt at player figur har en direction.



//    move 1: 18 (490 - 650, intervall 10) magnitude_of_direction 1
//    move 2: 12 (670 - 780, intervall 10) magnitude_of_direction 2
//    move 3: 6 (790 - 840, intervall 10) magnitude_of_direction 3
//    back up: 6 (430 - 480)  magnitude_of_direction -1
//    rotate right: 18 (80 - 420, intervall 20)
//    rotate left: 18 (70 - 410, intervall 20)
//    U-turn: 6 (10 - 60)

    /*
    (1,0) øst -> (0, -1)
    (-1,0) vest
    (0,1) nord
    (0,-1) sør
     */







}
