package com.josh;

import com.josh.util.Color;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Game {
    GameBoard board;
    boolean playerTurn;
    private Scanner scanner = new Scanner(System.in);


    public Game(GameBoard board, boolean initialMoveIsPlayer) {
        this.board = board;
        this.playerTurn = initialMoveIsPlayer;
    }

    public void play() {
        while (true) {
            try {
                if (gameIsOver()) {
                    break;
                }

                if (playerTurn) {
                    movePlayer();
                    board.printBoard();
                } else {
                    moveComputer();
                    board.printBoard();
                }
                playerTurn = !playerTurn;
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    private boolean gameIsOver() {


        if (StringUtils.equals(board.board[3][6], "C")) {
            System.out.println(Color.BLACK_BACKGROUND_BRIGHT + "Computer has won!");
            return true;
        }
        if (StringUtils.equals(board.board[4][6], "c")) {
            System.out.println("Human has won!");
            return true;
        }

        return false;
    }

    private void moveComputer() {
    }

    private void movePlayer() {
        do {
            try {
                System.out.println("Move a piece");
                String input = scanner.nextLine();
                board.isValidMove(input.toCharArray());
                board.move(input.toCharArray());
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }


}
