import java.util.HashSet;
import java.util.Set;

public class Board {
    private final int size;
    private final Cell[] cells; // size+1
    private final Set<BoardEntity> boardEntities;
    private final BoardEntitySetupStrategy boardSetupStrategy;


    public Board(int size, BoardEntitySetupFactory.BOARD_ENTITY_SETUP_TYPE boardSetupType){
        this.size = size;
        this.cells = new Cell[size+1];
        this.boardEntities = new HashSet<>();
        this.boardSetupStrategy = BoardEntitySetupFactory.getInstance(boardSetupType);
    }

    public Cell[] getCells() {
        return cells;
    }

    public int getSize() {
        return size;
    }

    public Set<BoardEntity> getBoardEntities() {
        return boardEntities;
    }

    public void initializeBoard(){
        for(int i = 0; i <= this.size; i++){
            cells[i] = new Cell(i);
        }

        this.boardSetupStrategy.setupBoard(this);
    }


    public boolean addBoardEntity(BoardEntity boardEntity){
        return this.boardEntities.add(boardEntity);
    }

    public void displayBoard(){
        for(int i = 0; i < 100; i+=10){
            for(int j = 1; j <= 10; j++) {
                System.out.print("[" + cells[i+j] + "] ");
            }
            System.out.println();
        }
        System.out.println("-------------");
        for(int i = 0; i < 100; i+=10){
            for(int j = 1; j <= 10; j++) {
                if(cells[i+j].getBoardEntity() != null) {
                    System.out.print("[" + cells[i+j].getBoardEntity().display() + "] ");
                }
                else {
                    System.out.print("[" + cells[i + j] + "] ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
