public interface BoardEntitySetupStrategy {
    Board setupBoard(Board board);

    static boolean addEntityToBoard(Board board, int length, Cell startingPos, Cell endingPos, BoardEntityType type) {
        BoardEntity boardEntity;
        if(type == BoardEntityType.SNAKE)
            boardEntity = new Snake(length, startingPos, endingPos);
        else{
            boardEntity = new Ladder(length, startingPos, endingPos);
        }

        // rechecking for thread-safe
        if(!startingPos.setBoardEntity(boardEntity)){
            return false;
        }

        startingPos.hasStartingOfBoardEntity = true;
        endingPos.hasEndingOfBoardEntity = true;
        board.addBoardEntity(boardEntity);
        return true;
    }
}
