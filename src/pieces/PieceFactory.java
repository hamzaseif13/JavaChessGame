package pieces;

import chessutils.Color;

public class PieceFactory {
    private PieceFactory() {
    }

    public static Piece createPiece(String piece) {
        Color color = piece.startsWith("W") ? Color.WHITE : Color.BLACK;
        if(piece.contains("PAWN"))
            return new Pawn(color);
        else if(piece.contains("ROOK"))
            return new Rook(color);
        else if(piece.contains("KNIGHT"))
            return new Knight(color);
        else if(piece.contains("BISHOP"))
            return new Bishop(color);
        else if(piece.contains("QUEEN"))
            return new Queen(color);
        else if(piece.contains("KING"))
            return new King(color);
        return null;
    }
}
