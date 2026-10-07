public class RandomBoardEntitySetupStrategy implements BoardEntitySetupStrategy{
    DifficultyLevel level;

    enum DifficultyLevel{
        EASY,
        MEDIUM,
        HARD;
    }

    @Override
    public Board setupBoard(Board board) {
        return null;
    }
}
