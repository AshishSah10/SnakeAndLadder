public interface Rule {

    boolean canPlayerEnterTheBoard(Player player, Dice.VALUE value);

    void movePlayerPosition(Player player, int value, Board board);

    boolean checkWin(Player player, Board board);


}
