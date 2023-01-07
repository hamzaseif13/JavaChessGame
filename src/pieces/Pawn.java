package pieces;

import chessutils.Color;
import chessutils.Move;
import chessutils.Position;

import java.util.Map;

public class Pawn extends Piece {

    private final int colorModifier;

    public Pawn(Color color) {
        super(color);
        colorModifier = color == Color.BLACK ? -1 : 1;
    }


    @Override
    public boolean canMoveTo(Move move, Map<Position, Piece> chessBoard) {
        return isValidMove(move) && isPathClear(move, chessBoard);
    }

    private boolean isValidMove(Move move) {
        // color modifier is 1 for white and -1 for black ,
        // so we can use it to check if the pawn is moving in the right direction according to its color
        boolean firstMove = (color == Color.WHITE && move.fromY() == 2) || (color == Color.BLACK && move.fromY() == 7);
        if ((move.toY() - move.fromY()) == (colorModifier) && Math.abs(move.toX() - move.fromX()) == 1) {
            return true;
        } else {
            boolean moveOneBlock = move.fromX() == move.toX() && (move.toY() - move.fromY()) == (colorModifier);
            if (firstMove) {
                return moveOneBlock || (move.toY() - move.fromY()) == (colorModifier * 2);
            } else {
                return moveOneBlock;
            }
        }

    }

    private boolean isPathClear(Move move, Map<Position, Piece> chessBoard) {
        Piece destinationPiece = chessBoard.get(Position.of(move.toX(), move.toY()));
        if ((move.toY() - move.fromY()) == (colorModifier) && Math.abs(move.toX() - move.fromX()) == 1) {
            return destinationPiece != null && destinationPiece.getColor() != color;
        } else if (destinationPiece != null) return false;
        else if (Math.abs(move.toY() - move.fromY()) == 2) {
            Piece inBetweenPiece = chessBoard.get(Position.of(move.toX(), move.toY() - colorModifier));
            return inBetweenPiece == null;
        }
        return true;
    }
    @Override
    public String toString() {
        if (color == Color.BLACK) return "--PN--";
        return "  PN  ";
    }

}
