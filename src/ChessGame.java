import chessutils.Color;
import chessutils.GameRules;
import chessutils.Move;
import chessutils.Position;
import io.GameInput;
import io.GameOutput;
import pieces.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private int moveCount = 0;
    private Color turn = Color.WHITE;

    private GameInput gameInput;
    private GameOutput gameOutput;
    private GameRules gameRules;

    public ChessGame(GameInput gameInput, GameOutput gameOutput, GameRules gameRules) {
        this.gameInput = gameInput;
        this.gameOutput = gameOutput;
        this.gameRules = gameRules;
    }

    String[][] board = {{"W-ROOK", "W-KNIGHT", "W-BISHOP", "W-QUEEN", "W-KING", "W-BISHOP", "W-KNIGHT", "W-ROOK"},
            {"W-PAWN", "W-PAWN", "W-PAWN", "W-PAWN", "W-PAWN", "W-PAWN", "W-PAWN", "W-PAWN"},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"  ", "  ", "  ", "  ", "  ", "", "", ""},
            {"B-PAWN", "B-PAWN", "B-PAWN", "B-PAWN", "B-PAWN", "B-PAWN", "B-PAWN", "B-PAWN"},
            {"B-ROOK", "B-KNIGHT", "B-BISHOP", "B-QUEEN", "B-KING", "B-BISHOP", "B-KNIGHT", "B-ROOK"}};
    private Map<Position, Piece> chessBoard;

    private void init() {
        chessBoard = new HashMap<>();
        for (int y = 8; y >= 1; y--) {
            for (int x = 1; x <= 8; x++) {
                chessBoard.put(Position.of(x, y), PieceFactory.createPiece(board[y - 1][x - 1]));
            }
        }
    }


    public void start() {
        init();
        gameRules.setChessBoard(chessBoard);
        while (moveCount++ < 100 && gameRules.doesRulesApply(turn)) {
            gameOutput.printBoard(chessBoard);
            gameOutput.printMessage("Player " + turn + " turn, move number " + moveCount / 2);
            String input = gameInput.getInput();
            while (!gameRules.isValidMove(input, turn)) {
                input = gameInput.getInput();
            }
            move(input);
            changeTurn();

        }
    }
    private void move(String input) {
        String from = input.substring(0, 2);
        String to = input.substring(3, 5);
        Piece sourcePiece = chessBoard.get(Position.of(from));
        chessBoard.put(Position.of(to), sourcePiece);
        chessBoard.put(Position.of(from), null);
    }


    private void changeTurn() {
        turn = turn == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
}
