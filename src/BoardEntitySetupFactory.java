import java.util.Scanner;

public class BoardEntitySetupFactory {

    public enum BOARD_ENTITY_SETUP_TYPE{
        STANDARD,
        RANDOM,
        CUSTOM
    }

    private static BoardEntitySetupStrategy instance;

    public static BoardEntitySetupStrategy getInstance(BOARD_ENTITY_SETUP_TYPE type){

        if(instance == null){
            switch (type){
                case STANDARD -> instance = new StandardBoardEntitySetupStrategy();
                case RANDOM -> {
                    System.out.println("Choose Difficulty Level");
                    System.out.println("1. Easy");
                    System.out.println("2. Medium");
                    System.out.println("3. Hard");
                    Scanner sc = new Scanner(System.in);
                    int input = sc.nextInt();

                    switch (input){
                        case 1 -> {
                            return new RandomBoardEntitySetupStrategy(RandomBoardEntitySetupStrategy.DifficultyLevel.EASY);
                        }
                        case 2 -> {
                            return new RandomBoardEntitySetupStrategy(RandomBoardEntitySetupStrategy.DifficultyLevel.MEDIUM);
                        }
                        case 3 -> {
                            return new RandomBoardEntitySetupStrategy(RandomBoardEntitySetupStrategy.DifficultyLevel.HARD);
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                default -> instance = new StandardBoardEntitySetupStrategy();
            }
        }
        return instance;
    }
}
