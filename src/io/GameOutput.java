package io;

import chessutils.Position;
import pieces.Piece;

import java.util.Map;

public interface GameOutput {
    public void printMessage(String message);
    public void printBoard(Map<Position, Piece> chessBoard);
}
