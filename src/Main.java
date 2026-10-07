
public class Main {
    public static void main(String[] args) {
        System.out.println("!!! WELCOME TO SNAKE & LADDER GAME!!!");
        SnakeAndLadderGameController slgc = new SnakeAndLadderGameController(100, new Player[]{new Player("player1"), new Player("player2")}, RuleFactory.RULETYPE.STANDARD, BoardEntitySetupFactory.BOARD_ENTITY_SETUP_TYPE.STANDARD);
        SnakeAndLadderGame game = slgc.getGame();

        game.startGame();
        game.play();
    }
}

