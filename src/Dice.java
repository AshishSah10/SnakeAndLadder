import java.util.Random;

public class Dice {
    private VALUE faceValue;
    private static Dice instance;

    private Dice(){

    }

    public enum VALUE
    {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6);

        final int number;
        VALUE(int number){
            this.number = number;
        }
    }

    public static Dice getInstance(){
        if(instance == null){
            instance = new Dice();
        }

        return instance;
    }


    public VALUE rollDice(){
        int randomeNo = new Random().nextInt(1,7);
        return switch (randomeNo) {
            case (1) -> VALUE.ONE;
            case (2) -> VALUE.TWO;
            case (3) -> VALUE.THREE;
            case (4) -> VALUE.FOUR;
            case (5) -> VALUE.FIVE;
            case (6) -> VALUE.SIX;
            default -> null;
        };
    }
}
