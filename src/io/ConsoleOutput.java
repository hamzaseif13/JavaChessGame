package io;

import chessutils.Color;
import chessutils.Position;
import pieces.Piece;

import java.util.List;
import java.util.Map;

public class ConsoleOutput implements GameOutput {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printBoard(Map<Position, Piece> chessBoard) {
        System.out.println();
        for (int j = 8; j > 0; j--) {
            System.out.print(j + " ");
            for (int i = 1; i <= 8; i++) {
                Piece piece = chessBoard.get(Position.of(i, j));
                if (piece == null) System.out.print("      |");
                else if (piece.getColor() == Color.BLACK) {
                    System.out.print(piece.toString() + "|");
                } else {
                    System.out.print(piece.toString() + "|");
                }
            }
            System.out.println("\n  --------------------------------------------------------");
        }
        List<String> strings = List.of("AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH");
        System.out.print("    ");
        for (String cr : strings) {
            System.out.print(cr + "     ");
        }
        System.out.println();
    }

}

