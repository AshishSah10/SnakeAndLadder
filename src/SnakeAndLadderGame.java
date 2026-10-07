import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SnakeAndLadderGame {
    private Board board;

    private Deque<Player> players;

    private Dice dice;
    private Rule rule;

    private SnakeAndLadderGame(int size, Deque<Player> players, RuleFactory.RULETYPE ruleType, BoardEntitySetupFactory.BOARD_ENTITY_SETUP_TYPE boardSetupType) {
        this.board = new Board(size, boardSetupType);
        this.players = players;
        this.rule = RuleFactory.getInstance(ruleType);
    }

    public static SnakeAndLadderGame createGame(int boardSize, Player[] players, RuleFactory.RULETYPE ruleType, BoardEntitySetupFactory.BOARD_ENTITY_SETUP_TYPE boardSetupType){
        Deque<Player> playersQueue = new ArrayDeque<>(Arrays.asList(players));
        return new SnakeAndLadderGame(boardSize, playersQueue, ruleType, boardSetupType);
    }

    public void startGame(){
        this.board.initializeBoard();
        this.dice = Dice.getInstance();

        Cell dummyCell = this.board.getCells()[0];
        for(Player player : this.players){
            player.setCurrentCell(dummyCell);
            dummyCell.addPlayer(player);
        }
    }

    public void play(){
        this.board.displayBoard();
        while(this.players.size() != 1) {
            for(Player player : this.players){
                System.out.println("player: "+player+" currentPos: "+player.getCurrentCell().value);
            }

            // player turn
            Player currPlayer = this.players.remove();
            this.players.add(currPlayer);

            // current player gets the Dice
            currPlayer.setDice(this.dice);
            Dice dice = currPlayer.getDice();
            Dice.VALUE value = dice.rollDice();
            System.out.println("Player: " + currPlayer + " got " + value.number);
            int currPlayerPos = currPlayer.getCurrentCell().value;
            if (currPlayerPos == 0) {
                // current player hasn't entered the board yet.
                if (this.rule.canPlayerEnterTheBoard(currPlayer, value)) {
                    currPlayer.getCurrentCell().removePlayer(currPlayer);

                    Cell firstCell = this.board.getCells()[1];
                    currPlayer.setCurrentCell(firstCell);
                    firstCell.addPlayer(currPlayer);
                }
            } else {
                this.rule.movePlayerPosition(currPlayer, currPlayerPos + value.number, this.board);
            }

            if (this.rule.checkWin(currPlayer, this.board)) {
                System.out.println("Winner is player: " + currPlayer);
                System.out.println("Removing player : " + currPlayer + " from the board, game will continue!!");
                this.players.remove(currPlayer);
            }

            // current Player release the Dice
            currPlayer.setDice(null);
        }
        System.out.println("No players left on the board, Game is ended!!!");
    }
}
