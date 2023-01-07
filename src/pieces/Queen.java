package pieces;

import chessutils.Color;
import chessutils.Move;
import chessutils.Position;

import java.util.Map;

public class Queen extends Piece {


    Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMoveTo(Move move, Map<Position, Piece> chessBoard) {
        boolean isValidMove=(move.fromX() == move.toX() || move.toY() == move.fromY())
                || (Math.abs(move.fromX() - move.toX()) == Math.abs(move.fromY() - move.toY()));
       return isValidMove && isPathClear(move, chessBoard);
    }


    private boolean isPathClear(Move move, Map<Position, Piece> chessBoard) {

        if (move.fromX() == move.toX() || move.toY() == move.fromY()) {
           return EmptyPathValidator.horizontalAndVerticalValidator(move, chessBoard);
        }
        else {
          return EmptyPathValidator.validateDiagonal(move, chessBoard);
        }

    }


    @Override
    public String toString() {
        if (color == Color.BLACK)
            return "--QN--";
        return "  QN  ";
    }
}
