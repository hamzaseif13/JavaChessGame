package pieces;

import chessutils.Color;
import chessutils.Move;
import chessutils.Position;

import java.util.Map;

public class Bishop extends Piece {


    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMoveTo(Move move, Map<Position, Piece> chessBoard) {
        boolean isValidMove = Math.abs(move.fromX()- move.toX()) == Math.abs(move.fromY() - move.toY());
        return isValidMove && isPathClear(move, chessBoard);
    }



    private boolean isPathClear(Move move, Map<Position, Piece> chessBoard) {
        return EmptyPathValidator.validateDiagonal(move, chessBoard);
    }


    @Override
    public String toString() {
        if (this.color == Color.BLACK)
            return "--BP--";
        return "  BP  ";
    }
}
