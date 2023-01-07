import chessutils.*;
import io.ConsoleInput;
import io.ConsoleOutput;
import io.GameInput;
import io.GameOutput;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        GameOutput gameOutput = new ConsoleOutput();
        GameInput gameInput = new ConsoleInput();
        GameRules gameRules = new ClassicRules(gameOutput);
        new ChessGame(gameInput,gameOutput,gameRules).start();

    }
}