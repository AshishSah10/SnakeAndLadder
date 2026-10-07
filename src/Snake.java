public class Snake extends BoardEntity{

    public Snake(int length, Cell startingPos, Cell endingPos){
        super(length, startingPos, endingPos);
    }

    @Override
    String display() {
        return "SNAKE: "+this.startingPos.value+"->"+this.endingPos.value;
    }
}
