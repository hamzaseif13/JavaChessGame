package pieces;

import chessutils.Move;
import chessutils.Position;

import java.util.Map;

public class EmptyPathValidator {
        static boolean validateDiagonal(Move move, Map<Position, Piece> chessBoard) {
            int rowDirection = Integer.signum(move.toY() - move.fromY());
            int colDirection = Integer.signum(move.toX() - move.fromX());
            int row = move.fromY() + rowDirection;
            int col = move.fromX() + colDirection;
            while (row != move.toY() && col != move.toX()) {
                if (chessBoard.get(Position.of(col, row)) != null) {
                    return false;
                }
                row += rowDirection;
                col += colDirection;
            }
            return true;
        }
        static boolean horizontalAndVerticalValidator(Move move, Map<Position, Piece> chessBoard) {
            if (move.fromX() == move.toX()) {
                int rowDirection = Integer.signum(move.toY() - move.fromY());
                int row = move.fromY() + rowDirection;
                while (row != move.toY()) {
                    if (chessBoard.get(Position.of(move.fromX(), row)) != null) {
                        return false;
                    }
                    row += rowDirection;
                }
            } else {
                int colDirection = Integer.signum(move.toX() - move.fromX());
                int col = move.fromX() + colDirection;
                while (col != move.toX()) {
                    if (chessBoard.get(Position.of(col, move.fromY())) != null) {
                        return false;
                    }
                    col += colDirection;
                }
            }
            return true;
        }
}
