import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class Cell {
    int value;
    private Set<Player> players = new HashSet<>(); // it can be empty
    private final AtomicReference<BoardEntity> boardEntity = new AtomicReference<>();
    boolean hasStartingOfBoardEntity;
    boolean hasEndingOfBoardEntity;

    public Cell(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
    public void removePlayer(Player player){
        players.remove(player);
    }

    public boolean setBoardEntity(BoardEntity boardEntity){
        if(!this.boardEntity.compareAndSet(null, boardEntity)){
            System.out.println("boardEntity is already set");
            return false;
        }
        System.out.println("boardEntity is set to "+boardEntity.display()+" at cell["+this.value+"]");
        return true;
    }

    public BoardEntity getBoardEntity(){
        return this.boardEntity.get();
    }

    public boolean canPlaceBoardEntity(){
        if(this.hasStartingOfBoardEntity || this.hasEndingOfBoardEntity){
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return this.value+"";
    }

    public boolean isPlayersEmpty() {
        return this.players.isEmpty();
    }
}
