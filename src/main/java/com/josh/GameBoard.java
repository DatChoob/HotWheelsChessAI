package com.josh;

import com.josh.pieces.*;
import com.josh.util.Move;

import java.util.Arrays;
import java.util.List;

import static com.josh.util.BoardPosition.columnCharToIndex;

public class GameBoard implements Cloneable {
    //row/column

    private int width = 7;
    private int height = 8;
    public Piece[][] board = new Piece[height][width];


    public GameBoard() {
    }

    public void init() {

        //create kings
        board[0][0] = new King(true);
        board[7][0] = new King(false);

        board[1][1] = new Pawn(true);
        board[2][2] = new Pawn(true);
        board[6][1] = new Pawn(false);
        board[5][2] = new Pawn(false);


        board[2][3] = new Bishop(true);
        board[2][4] = new Bishop(true);
        board[5][3] = new Bishop(false);
        board[5][4] = new Bishop(false);

        board[3][0] = new Knight(true);
        board[3][1] = new Knight(true);
        board[4][0] = new Knight(false);
        board[4][1] = new Knight(false);


        board[2][0] = new Rook(true);
        board[2][1] = new Rook(true);
        board[5][0] = new Rook(false);
        board[5][1] = new Rook(false);

        board[3][3] = new Pawn(true);
        board[3][4] = new Pawn(true);
        board[3][5] = new Pawn(true);
        board[3][6] = new Pawn(true);

        board[4][3] = new Pawn(false);
        board[4][4] = new Pawn(false);
        board[4][5] = new Pawn(false);
        board[4][6] = new Pawn(false);
        printBoard();
    }

    public void printBoard() {
        for (int i = board.length - 1; i >= 0; i--) {
            System.out.print((i + 1) + "\t");
            for (Piece column : board[i]) {
                if (column == null)
                    System.out.print("- ");
                else System.out.print(column.toString() + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("\tA B C D E F G");
    }

    public boolean isValidMove(char[] input, List<Move> possibleMoves) {
        try {
            if (input.length != 4) {
                System.out.println("Length must be four");
                return false;
            }

            //converts Letter to int for indexing
            int fromCol = columnCharToIndex(input[0]);
            //subtract 1 because we are storing 0 index
            int fromRow = Integer.parseInt(String.valueOf(input[1])) - 1;
            if (fromRow < 0 || fromRow > 7) {
                System.out.println("Invalid From row number");
                return false;

            }
            //converts Letter to int for indexing
            int toCol = columnCharToIndex(input[2]);
            //subtract 1 because we are storing 0 index
            int toRow = Integer.parseInt(String.valueOf(input[3])) - 1;
            if (fromCol == toCol && fromRow == toRow) {
                System.out.println("You cannot move piece to its same location");
                return false;

            }
            if (toRow < 0 || toRow > 7) {
                System.out.println("Invalid To row number");
                return false;

            }
            if (board[fromRow][fromCol] == null) {
                System.out.println(input[0] + "" + input[1] + " Not a peice");
                return false;
            }

            if (board[toRow][toCol] != null && board[toRow][toCol].isUser()) {
                System.out.println(input[2] + "" + input[3] + " Has a friendly piece on it");
                return false;
            }

            if (possibleMoves.stream().noneMatch(item -> item.toString().equals(String.valueOf(input)))) {
                System.out.println(String.valueOf(input) + " not a legal move");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Invalid input " + String.valueOf(input) + " " + e.getMessage());
            return false;
        }
        return true;
    }

    public void move(Move move) {
//        char[] input = move.toCharArray();
        int fromCol = move.getFromPosition().getColumn();
        int fromRow = move.getFromPosition().getRow();
        int toCol = move.getToPosition().getColumn();
        int toRow = move.getToPosition().getRow();

        Piece tmp = board[fromRow][fromCol];
        board[toRow][toCol] = tmp;
        board[fromRow][fromCol] = null;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    // 8   C - - - - - - (COMPUTER)
    // 7   - P - - - - -
    // 6   R R P B B - -
    // 5   N N - P P P P
    // 4   n n - p p p p
    // 3   r r p b b - -
    // 2   - p - - - - -
    // 1   c - - - - - -  (HUMAN)


    @Override
    protected GameBoard clone() {
        GameBoard clonedBoard = new GameBoard();
        clonedBoard.board = Arrays.stream(this.board).map(el -> el.clone()).toArray($ -> this.board.clone());
        return clonedBoard;
    }
}
