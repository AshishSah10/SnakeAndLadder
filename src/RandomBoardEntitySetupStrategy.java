public class RandomBoardEntitySetupStrategy implements BoardEntitySetupStrategy{
    DifficultyLevel level;

    public enum DifficultyLevel{
        EASY, // #ladders > #snakes,
        MEDIUM, //  #ladders == #snakes
        HARD; //  #ladders < #snakes
    }

    public RandomBoardEntitySetupStrategy(DifficultyLevel level){
        this.level = level;
    }

    @Override
    public Board setupBoard(Board board) {
        switch (this.level){
            case EASY -> {
                return setupBoard(board, 0.3);
            }
            case MEDIUM -> {
                return setupBoard(board, 0.5);
            }
            case HARD -> {
                return setupBoard(board, 0.7);
            }

            default -> {
                return null;
            }
        }
    }

    private Board setupBoard(Board board, double snakeProbability) {
        int boardSize = board.getSize();
        int totalEntities = boardSize/10; // 10% of the board size

        int snakeCount = (int)(snakeProbability*10);
        int ladderCount = totalEntities - snakeCount;


        while(snakeCount > 0){
            // Snake goes DOWN: start > end, min length 30, avoid cells 0,1 and boardSize
            int start = (int)(Math.random() * (boardSize - 32)) + 32; // 32 to boardSize-1
            int end = (int)(Math.random() * (start - 30)) + 2; // 2 to start-30

            Cell startingPos = board.getCells()[start];
            Cell endingPos = board.getCells()[end];

            if(!startingPos.canPlaceBoardEntity() || !endingPos.canPlaceBoardEntity() || start == end){
                System.out.println("startingPos: "+startingPos.value);
                System.out.println("startingPos entity: "+startingPos.getBoardEntity());
                System.out.println("startingPos -> hasStartingOfBoardEntity: "+startingPos.hasStartingOfBoardEntity);
                System.out.println("startingPos -> hasEndingOfBoardEntity: "+startingPos.hasEndingOfBoardEntity);

                System.out.println("endingPos: "+endingPos.value);
                System.out.println("endingPos entity: "+endingPos.getBoardEntity());
                System.out.println("endingPos -> hasStartingOfBoardEntity: "+endingPos.hasStartingOfBoardEntity);
                System.out.println("endingPos -> hasEndingOfBoardEntity: "+endingPos.hasEndingOfBoardEntity);

                System.out.println("Two Snakes are conflicting for same cells");
                continue;
            }
            int length = startingPos.value - endingPos.value;

            BoardEntitySetupStrategy.addEntityToBoard(board, length, startingPos, endingPos, BoardEntityType.SNAKE);
            snakeCount--;
        }
        while(ladderCount > 0){
            // Ladder goes UP: end > start, min length 30, avoid cells 0,1 and boardSize
            int start = (int)(Math.random() * (boardSize - 32)) + 2; // 2 to boardSize-32
            int end = (int)(Math.random() * (boardSize - start - 30)) + start + 30; // start+30 to boardSize-1

            Cell startingPos = board.getCells()[start];
            Cell endingPos = board.getCells()[end];

            if(!startingPos.canPlaceBoardEntity() || !endingPos.canPlaceBoardEntity() || start == end){
                System.out.println("startingPos: "+startingPos.value);
                System.out.println("startingPos entity: "+startingPos.getBoardEntity());
                System.out.println("startingPos -> hasStartingOfBoardEntity: "+startingPos.hasStartingOfBoardEntity);
                System.out.println("startingPos -> hasEndingOfBoardEntity: "+startingPos.hasEndingOfBoardEntity);

                System.out.println("endingPos: "+endingPos.value);
                System.out.println("endingPos entity: "+endingPos.getBoardEntity());
                System.out.println("endingPos -> hasStartingOfBoardEntity: "+endingPos.hasStartingOfBoardEntity);
                System.out.println("endingPos -> hasEndingOfBoardEntity: "+endingPos.hasEndingOfBoardEntity);
                System.out.println("Two Ladders are conflicting for same cells");
                continue;
            }
            int length = endingPos.value - startingPos.value;

            BoardEntitySetupStrategy.addEntityToBoard(board, length, startingPos, endingPos, BoardEntityType.LADDER);
            ladderCount--;
        }

        return board;
    }
}
