package pieces;

import chessutils.Color;
import chessutils.Move;
import chessutils.Position;

import java.util.Map;

public class Knight extends Piece {


    public Knight(Color color) {
        super(color);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean canMoveTo(Move move, Map<Position, Piece> chessBoard) {
        return ((Math.abs(move.fromY() - move.toY()) == 2 && Math.abs(move.fromX() - move.toX()) == 1) ||
                (Math.abs(move.fromX() - move.toX()) == 2 && Math.abs(move.fromY() - move.toY()) == 1));
    }
    @Override
    public String toString() {
        if(color==Color.BLACK)
            return "--KN--";
        return "  KN  ";
    }
}

