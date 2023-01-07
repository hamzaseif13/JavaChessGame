package chessutils;

import pieces.Piece;

import java.util.Map;

public interface GameRules {
    boolean doesRulesApply( Color turn);
    boolean isValidMove(String input,Color turn);

    void setChessBoard(Map<Position, Piece> chessBoard);

}
