import java.io.File;
import java.util.Map;

public class StandardBoardEntitySetupStrategy implements BoardEntitySetupStrategy {

    // File file = new File("/StandardBoardEntitiesSetupStrategy.txt");
    Map<Integer, Integer> ladders = Map.of(
            4, 14,
            9, 31,
            20, 38,
            28, 84,
            40, 59,
            51, 67,
            63, 81,
            71, 91
    );

    Map<Integer, Integer> snakes = Map.of(
            17, 7,
            54, 34,
            62, 19,
            64, 60,
            87, 24,
            93, 73,
            95, 75,
            99, 78
    );

    @Override
    public Board setupBoard(Board board) {
        for(Map.Entry<Integer, Integer> snakeEntry : snakes.entrySet()){
            if (!setupSnakeEntity(board, snakeEntry)) return null;
        }

        for(Map.Entry<Integer, Integer> ladderEntry : ladders.entrySet()){
            if (!setupLadderEntity(board, ladderEntry)) return null;
        }
        return board;
    }

    private static boolean setupSnakeEntity(Board board, Map.Entry<Integer, Integer> snakeEntry) {
        Cell startingPos = board.getCells()[snakeEntry.getKey()];
        Cell endingPos = board.getCells()[snakeEntry.getValue()];

        if(!startingPos.canPlaceBoardEntity() || !endingPos.canPlaceBoardEntity()){
            System.out.println("Two Snakes are conflicting for same cells");
            return false;
        }
        int length = startingPos.value - endingPos.value;

        return BoardEntitySetupStrategy.addEntityToBoard(board, length, startingPos, endingPos, BoardEntityType.SNAKE);
    }

    private static boolean setupLadderEntity(Board board, Map.Entry<Integer, Integer> ladderEntry) {
        Cell startingPos = board.getCells()[ladderEntry.getKey()];
        Cell endingPos = board.getCells()[ladderEntry.getValue()];

        if(startingPos.hasStartingOfBoardEntity || startingPos.hasEndingOfBoardEntity || endingPos.hasStartingOfBoardEntity || endingPos.hasEndingOfBoardEntity){
            System.out.println("Two Ladders are conflicting for same cells");
            return false;
        }
        int length = endingPos.value - startingPos.value;

        return BoardEntitySetupStrategy.addEntityToBoard(board, length, startingPos, endingPos, BoardEntityType.LADDER);
    }
}
