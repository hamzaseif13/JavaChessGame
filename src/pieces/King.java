package pieces;

import chessutils.Color;
import chessutils.Move;
import chessutils.Position;

import java.util.Map;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }


    @Override
    public boolean canMoveTo(Move move, Map<Position, Piece> chessBoard) {
        if (Math.abs(move.fromX() - move.toX()) == 1 && move.fromY() == move.toY()) return true;
        if (Math.abs(move.fromY() - move.toY()) == 1 && move.fromX() == move.toX()) return true;
        return (Math.abs(move.fromX() - move.toX()) == Math.abs(move.fromY() - move.toY()) && Math.abs(move.fromY() - move.toY()) == 1);
    }

    @Override
    public String toString() {
        if (color == Color.BLACK)
            return "--KG--";
        return "  KG  ";
    }
}
