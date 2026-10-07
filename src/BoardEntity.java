public abstract class BoardEntity {
    final Cell startingPos;
    final Cell endingPos;
    final int length;

    protected BoardEntity(int length, Cell startingPos, Cell endingPos){
        this.length = length;
        this.startingPos = startingPos;
        this.endingPos = endingPos;
    }

    abstract String display();

    @Override
    public String toString(){
        return startingPos+""+endingPos;
    }

    @Override
    public int hashCode(){
        return startingPos.value;
    }

    @Override
    public boolean equals(Object obj){
        if(!obj.getClass().equals(this.getClass())){
            return false;
        }

        BoardEntity boardEntity = (BoardEntity) obj;
        return boardEntity.startingPos == this.startingPos || boardEntity.endingPos == this.endingPos;
    }

    public Cell getStaringPos(){
        return this.startingPos;
    }

    public Cell getEndingPos(){
        return this.endingPos;
    }

    public int getLength(){
        return this.length;
    }
}
