package chessutils;


import io.GameOutput;
import pieces.King;
import pieces.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassicRules implements GameRules {
    private GameOutput gameOutput;
    private Map<Position, Piece> chessBoard;

    public ClassicRules(GameOutput gameOutput) {
        this.gameOutput = gameOutput;
    }

    @Override
    public boolean doesRulesApply(Color turn) {
        return !isCheckmate(turn);

    }

    @Override
    public void setChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public boolean isValidMove(String input, Color turn) {
        if (!input.matches("[a-h][1-8] [a-h][1-8]")) {
            gameOutput.printMessage("Incorrect input, Correct Format : 'b2 b3' ");
            return false;
        }
        String from = input.substring(0, 2);
        String to = input.substring(3, 5);
        Piece sourcePiece = chessBoard.get(Position.of(from));
        Piece targetPiece = chessBoard.get(Position.of(to));
        Move move = new Move(from, to);


        if (sourcePiece == null || sourcePiece.getColor() != turn) {
            gameOutput.printMessage("The piece you are trying to move a piece is not yours or there is " + "no piece there, Try again : ");
            return false;
        } else if (targetPiece != null && targetPiece.getColor() == turn) {
            gameOutput.printMessage("you cant move here because you have a piece there already, Try again : ");
            return false;
        }

        if (sourcePiece.canMoveTo(move, chessBoard)) {
            Map<Position, Piece> copyChessBoard = copyChessBoard(chessBoard);
            copyChessBoard.put(Position.of(to), sourcePiece);
            copyChessBoard.put(Position.of(from), null);
            if (isChecked(getKingPosition(turn, copyChessBoard), turn, copyChessBoard)) {
                gameOutput.printMessage("You cant move here because you will be in check, Try again : ");
                return false;
            }
            gameOutput.printMessage(String.format("Player %s moved %s from %s to %s%n", turn, sourcePiece, from, to));
            return true;
        }
        gameOutput.printMessage("Invalid move, Try again : ");
        return false;
    }

    private boolean isCheckmate(Color turn) {
        Position kingPosition = getKingPosition(turn, chessBoard);
        Piece king = chessBoard.get(kingPosition);
        if (!isChecked(kingPosition, turn, chessBoard)) {
            return false;
        }
        if (king.getPossibleMoves(kingPosition, chessBoard).stream().
                noneMatch(move -> isChecked(move, turn, chessBoard))) {
            return false;
        }

        Set<Map.Entry<Position, Piece>> defenderPieces = chessBoard.entrySet().stream().
                filter(entry -> entry.getValue() != null &&
                        entry.getValue().getColor() == turn &&
                        !(entry.getValue() instanceof King))
                .collect(Collectors.toSet());

        for (var set : defenderPieces) {
            List<Position> possibleMoves = set.getValue().getPossibleMoves(set.getKey(), chessBoard);
            for (Position toPosition : possibleMoves) {
                Map<Position, Piece> chessBoardCopy = copyChessBoard(chessBoard);
                Position fromPosition = set.getKey();
                Piece sourcePiece = chessBoardCopy.get(fromPosition);
                chessBoardCopy.put(toPosition, sourcePiece);
                chessBoardCopy.put(fromPosition, null);
                if (!isChecked(kingPosition, turn, chessBoardCopy)) {
                    return false;
                }
            }

        }
        gameOutput.printBoard(chessBoard);
        gameOutput.printMessage(String.format("Player %s is checkmate, Good game", turn));
        return true;
    }

    private Position getKingPosition(Color turn, Map<Position, Piece> chessBoard) {
        for (Map.Entry<Position, Piece> set : chessBoard.entrySet()) {
            if (set.getValue() != null && set.getValue().getColor() == turn && set.getValue() instanceof King) {
                return set.getKey();
            }
        }
        throw new IllegalArgumentException("No king found");
    }

    private Map<Position, Piece> copyChessBoard(Map<Position, Piece> chessBoard) {
        Map<Position, Piece> copy = new HashMap<>();
        for (var set : chessBoard.entrySet()) {
            copy.put(set.getKey(), set.getValue());
        }
        return copy;
    }

    private boolean isChecked(Position kingPosition, Color turn, Map<Position, Piece> chessBoard) {
        for (Map.Entry<Position, Piece> set : chessBoard.entrySet()) {
            if (set.getValue() != null && set.getValue().getColor() != turn) {
                Move move = new Move(set.getKey(), kingPosition);
                if (set.getValue().canMoveTo(move, chessBoard)) {
                    return true;
                }
            }
        }
        return false;
    }

}
