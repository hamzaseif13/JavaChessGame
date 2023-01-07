package pieces;

import chessutils.Color;
import chessutils.Move;
import chessutils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Piece implements Cloneable {

    protected final Color color;

    Piece(Color color) {
        this.color = color;

    }

    public Color getColor() {
        return color;
    }

    public abstract boolean canMoveTo(Move move, Map<Position, Piece> chessBoard);

    public List<Position> getPossibleMoves(Position startPosition, Map<Position, Piece> chessBoard) {
        List<Position> possibleMoves = new ArrayList<>();
        for (int j = 1; j <= 8; j++) {
            for (int i = 1; i <= 8; i++) {

                Piece sourcePiece = chessBoard.get(startPosition);
                Piece destinationPiece = chessBoard.get(Position.of(i, j));
                if (sourcePiece.canMoveTo(new Move(startPosition, Position.of(i, j)), chessBoard) &&
                        (destinationPiece == null || destinationPiece.getColor() != sourcePiece.getColor())) {
                    possibleMoves.add(Position.of(i, j));
                }
            }
        }

        return possibleMoves;
    }
}
