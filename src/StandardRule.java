public class StandardRule implements Rule{
    // For standard rule, player can enter the board if they got 1 or
    // 6, and they will start from cell-> 1 in their next turn.


    @Override
    public boolean canPlayerEnterTheBoard(Player player, Dice.VALUE value) {
        if(value == Dice.VALUE.SIX || value == Dice.VALUE.ONE){
            return true;
        }
        System.out.println("Try to pop-up 1 or 6 next time.");
        return false;
    }

    @Override
    public void movePlayerPosition(Player player, int newPos, Board board) {
        int maxCellNo = board.getSize();
        if(maxCellNo >= newPos){
            Cell currCell = player.getCurrentCell();
            currCell.removePlayer(player);

            Cell newCell = board.getCells()[newPos];
            player.setCurrentCell(newCell);
            newCell.addPlayer(player);
            BoardEntity boardEntity = newCell.getBoardEntity();
            if(boardEntity != null){
                System.out.println("player: "+player+" is taking "+boardEntity.display());
                newPos = boardEntity.getEndingPos().value;
                movePlayerPosition(player, newPos, board);
            }
        }
        else{
            System.out.println("Sorry!! cannot move further.");
        }
    }

    @Override
    public boolean checkWin(Player player, Board board) {
        return player.getCurrentCell().value == board.getSize();
    }

}
