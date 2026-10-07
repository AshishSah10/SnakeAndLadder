public class SnakeAndLadderGameController {
    SnakeAndLadderGame game;
    Player[] players;

    public SnakeAndLadderGame getGame() {
        return game;
    }

    public SnakeAndLadderGameController(int size, Player[] players, RuleFactory.RULETYPE type, BoardEntitySetupFactory.BOARD_ENTITY_SETUP_TYPE setupType){
        this.game = SnakeAndLadderGame.createGame(size, players, type, setupType);
    }
}
