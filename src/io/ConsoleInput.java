package io;


import java.util.Scanner;

public class ConsoleInput implements GameInput {
    Scanner scanner ;
    public ConsoleInput(){
        scanner = new Scanner(System.in);
    }
    public String getInput() {
        return scanner.nextLine();
    }

}
