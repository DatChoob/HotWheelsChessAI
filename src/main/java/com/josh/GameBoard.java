package com.josh;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidParameterException;

public class GameBoard {
    //row/column
    String[][] board = new String[8][7];


    public GameBoard() {

        init();
    }

    private void init() {

        //create kings
        board[0][0] = "C";
        board[7][0] = "c";

        board[1][1] = "P";
        board[2][2] = "P";
        board[6][1] = "p";
        board[5][2] = "p";


        board[2][3] = "B";
        board[2][4] = "B";
        board[5][3] = "B";
        board[5][4] = "B";


        board[2][0] = "R";
        board[2][1] = "R";
        board[5][0] = "r";
        board[5][1] = "r";

        board[3][3] = "P";
        board[3][4] = "P";
        board[3][5] = "P";
        board[3][6] = "P";

        board[4][3] = "P";
        board[4][4] = "P";
        board[4][5] = "P";
        board[4][6] = "P";


        printBoard();

    }

    public void printBoard() {

        for (String[] row : board) {
            for (String column : row) {
                if (StringUtils.isBlank(column))
                    System.out.print("- ");
                else System.out.print(column + " ");
            }
            System.out.println();
        }
    }

    public void isValidMove(char[] input) throws Exception {
        try {
            if (input.length != 4)
                throw new InvalidParameterException("Length must be four");

            int fromCol = toColumnIndex(input[0]);
            int fromRow = Integer.parseInt(String.valueOf(input[1]));
            if (fromRow < 0 || fromRow > 7)
                throw new InvalidParameterException("Invalid From row number");
            int toCol = toColumnIndex(input[2]);
            int toRow = Integer.parseInt(String.valueOf(input[3]));
            if (fromCol == toCol && fromRow == toRow) {
                throw new InvalidParameterException("You cannot move piece to its same location");
            }
            if (toRow < 0 || toRow > 7) {
                throw new InvalidParameterException("Invalid To row number");
            }
            if (StringUtils.isBlank(board[fromRow][fromCol])) {
                throw new InvalidParameterException(input[0] + "" + input[1] + " Not a peice");
            }
            if (StringUtils.isNotBlank(board[toRow][toCol])) {
                throw new InvalidParameterException(input[3] + "" + input[4] + " Has a friendly piece on it");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void move(char[] input) {
        int fromCol = toColumnIndex(input[0]);
        int fromRow = Integer.parseInt(String.valueOf(input[1]));
        int toCol = toColumnIndex(input[2]);
        int toRow = Integer.parseInt(String.valueOf(input[3]));

        System.out.println("Moving piece " + board[fromRow][fromCol]);
        String tmp = board[fromRow][fromCol];
        board[toRow][toCol] = tmp;
        board[fromRow][fromCol] = null;
    }

    private int toColumnIndex(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            default:
                throw new InvalidParameterException("not a valid column");
        }

    }

// 0   C - - - - - - (COMPUTER)
// 1   - P - - - - -
// 2   R R P B B - -
// 3   N N - P P P P
// 4   n n - p p p p
// 5   r r p b b - -
// 6   - p - - - - -
// 7   c - - - - - -  (HUMAN)

}
