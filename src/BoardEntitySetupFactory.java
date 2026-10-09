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
                case CUSTOM -> {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter the number of Snakes:");
                    int snakeCount = sc.nextInt();
                    System.out.println("Enter the number of Ladders:");
                    int ladderCount = sc.nextInt();
                    System.out.println("Please choose:");
                    System.out.println("1. Keep their position random.");
                    System.out.println("2. I will choose their positions myself");
                    int choice = sc.nextInt();
                    if(choice == 1){
                        return new CustomBoardEntitySetupStrategy(snakeCount, ladderCount, true);
                    }
                    else{
                        CustomBoardEntitySetupStrategy customBoardEntitySetupStrategy = new CustomBoardEntitySetupStrategy(snakeCount, ladderCount, false);
                        while(snakeCount-- > 0){
                            System.out.println("Enter the head and tail of Snake: e.g. 88 33");
                            int start = sc.nextInt();
                            int end = sc.nextInt();
                            customBoardEntitySetupStrategy.addSnakePosition(start, end);
                        }
                        while(ladderCount-- > 0){
                            System.out.println("Enter the starting and ending of Ladder: e.g. 33 67");
                            int start = sc.nextInt();
                            int end = sc.nextInt();
                            customBoardEntitySetupStrategy.addLadderPosition(start, end);
                        }

                        return customBoardEntitySetupStrategy;
                    }
                }
                default -> instance = new StandardBoardEntitySetupStrategy();
            }
        }
        return instance;
    }
}
