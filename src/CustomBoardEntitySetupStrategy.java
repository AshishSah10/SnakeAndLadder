import java.util.HashMap;
import java.util.Map;

public class CustomBoardEntitySetupStrategy implements BoardEntitySetupStrategy{
    private int snakeCount;
    private int ladderCount;
    private boolean positionRandomly;

    Map<Integer, Integer> snakePosition;
    Map<Integer, Integer> ladderPosition;

    public CustomBoardEntitySetupStrategy(int snakeCount, int ladderCount, boolean positionRandomly){
        this.snakeCount = snakeCount;
        this.ladderCount = ladderCount;
        this.positionRandomly = positionRandomly;

        if(!positionRandomly){
            snakePosition = new HashMap<>();
            ladderPosition = new HashMap<>();
        }
    }

    @Override
    public Board setupBoard(Board board) {
        int boardSize = board.getSize();
        if(this.positionRandomly){
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
        }
        else{
            for(Map.Entry<Integer, Integer> snake : snakePosition.entrySet()){
                int start = snake.getKey();
                int end = snake.getValue();

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
                int length = endingPos.value - startingPos.value;
                BoardEntitySetupStrategy.addEntityToBoard(board, length, startingPos, endingPos, BoardEntityType.SNAKE);
            }

            for(Map.Entry<Integer, Integer> ladder : ladderPosition.entrySet()){
                int start = ladder.getKey();
                int end = ladder.getValue();

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
            }
        }
        return board;
    }

    public int getLadderCount() {
        return ladderCount;
    }

    public void addSnakePosition(int start, int end){
        if(start > end){
            this.snakePosition.put(start, end);
        }
        else{
            System.out.println("Start should be greater than end.");
        }
    }

    public void addLadderPosition(int start, int end){
        if(start < end){
            this.ladderPosition.put(start, end);
        }
        else{
            System.out.println("End should be greater than start.");
        }
    }
}
