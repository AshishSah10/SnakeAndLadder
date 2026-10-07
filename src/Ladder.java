public class Ladder extends BoardEntity{

    public Ladder(int length, Cell startingPos, Cell endingPos){
        super(length, startingPos, endingPos);
    }

    @Override
    String display() {
        return "LADDER: "+this.startingPos.value+"->"+this.endingPos.value;
    }
}
