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

                default -> instance = new StandardBoardEntitySetupStrategy();
            }
        }
        return instance;
    }
}
