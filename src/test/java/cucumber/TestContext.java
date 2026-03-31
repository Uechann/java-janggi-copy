package cucumber;

import domain.board.Board;
import domain.piece.Side;
import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private Board board;
    private Side currentTurn;
    private IllegalArgumentException lastException ;

    public void initBoard() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Side getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Side currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Exception getLastException() {
        return lastException;
    }

    public void setLastException(IllegalArgumentException lastException) {
        this.lastException = lastException;
    }

    public void clearException() {
        this.lastException = null;
    }
}
