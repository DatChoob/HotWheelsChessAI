package com.josh;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        start game
        Scanner scanner = new Scanner(System.in);

        Boolean playerStarts = null;
        do {
            System.out.println("Do you, the human, want to go first? Y/N");
            String input = scanner.nextLine();

            if (StringUtils.trimToEmpty(input).equalsIgnoreCase("Y")) {
                playerStarts = true;
            } else if (StringUtils.trimToEmpty(input).equalsIgnoreCase("N")) {
                playerStarts = false;
            }
        } while (playerStarts == null);


        GameBoard board = new GameBoard();


        Game controller=  new Game(board,playerStarts);
        controller.play();

    }
}
