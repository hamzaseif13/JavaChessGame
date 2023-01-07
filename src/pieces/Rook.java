package pieces;

import chessutils.Color;
import chessutils.Move;
import chessutils.Position;

import java.util.Map;

public class Rook extends Piece {


    Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMoveTo(Move move, Map<Position, Piece> chessBoard) {
        boolean isValidMove = ( move.fromX() == move.toX() || move.toY() == move.fromY() );
        return  isValidMove && isPathClear(move, chessBoard);
    }



    private boolean isPathClear(Move move, Map<Position, Piece> chessBoard) {
        return EmptyPathValidator.horizontalAndVerticalValidator(move, chessBoard);
    }

    @Override
    public String toString() {
        if (color == Color.BLACK)
            return "--RK--";
        return "  RK  ";
    }
}
