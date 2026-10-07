public class Player {
    String name;
    static int uniqueId = 1; // auto increment
    int id;
    private Dice dice;

    private Cell currentCell;

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Player(String name){
        this.name = name;
        this.id = uniqueId;
        uniqueId++;
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
