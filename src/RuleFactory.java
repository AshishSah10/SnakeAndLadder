public class RuleFactory {

    public enum RULETYPE{
        STANDARD
    }

    public static Rule instance;

    public static Rule getInstance(RULETYPE type){
        return new StandardRule();
    }
}
