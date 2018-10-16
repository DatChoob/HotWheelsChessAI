package com.josh;

import com.josh.pieces.*;
import com.josh.util.BoardPosition;
import com.josh.util.Color;
import com.josh.util.Move;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


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
                    System.out.println(Color.CYAN_BACKGROUND);
                    System.out.println(Color.BLACK);
                } else {
                    moveComputer();
                    board.printBoard();
                    System.out.println(Color.CYAN_BACKGROUND);
                    System.out.println(Color.BLACK);
                }
                playerTurn = !playerTurn;
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
                break;
            }
        }
    }

    private boolean gameIsOver() {


        if (board.board[3][6] instanceof King && board.board[3][6].isUser()) {
            System.out.println("Human has won!");
            return true;
        }
        if (board.board[4][6] instanceof King && !board.board[4][6].isUser()) {
            System.out.println(Color.BLACK_BACKGROUND_BRIGHT + "Computer has won!");
            return true;
        }

        return false;
    }

    private void moveComputer() {
        long startTime =  System.nanoTime();
        List<Move> moves = generateMoves(false);
        long finishTime =  System.nanoTime();
        System.out.println("Time to generate AI moves(ms):  "+(finishTime-startTime)/1000000.0);
        if (moves.isEmpty())
            //find king, then force generate
            moves.addAll(forceGenerateKingMove(false));
        System.out.println("Potential Moves: " + moves.toString());
        System.out.println("Making move: " + moves.get(0)  +"\t" +BoardPosition.generateOpponentEquivalentMove(moves.get(0).toString()));
        board.move(moves.get(0).toString().toCharArray());
    }


    private List<Move> forceGenerateKingMove(boolean isHumanTurn) {
        for (int rowIndex = 1; rowIndex <= board.board.length; rowIndex++) {
            Piece[] rows = board.board[rowIndex - 1];
            for (int columnIndex = 1; columnIndex <= rows.length; columnIndex++) {
                Piece piece = board.board[rowIndex - 1][columnIndex - 1];
                if (piece instanceof King && piece.isUser() == isHumanTurn)
                    return ((King) piece).generateMoves(board, rowIndex, columnIndex, true);
            }
        }
        return new ArrayList<Move>();

    }


    private void movePlayer() {
        do {
            try {
                System.out.println("Move a piece");
                List<Move> moves=  generateMoves(true);
                if(moves.isEmpty())
                    moves.addAll(forceGenerateKingMove(true));
                System.out.println("Potential Moves: " +moves.toString());
                String input = scanner.nextLine();
                if (StringUtils.isBlank(input)) continue;
                if (!board.isValidMove(input.toCharArray())) continue;
                board.move(input.toCharArray());
                break;
            } catch (Exception e) {
                System.err.println("Error reached move player method");
                e.printStackTrace();
                System.err.println(e.getMessage());
                return;
            }
        } while (true);
    }


    public List<Move> generateMoves(boolean isHumanTurn) {
        List<Move> moves = new ArrayList<Move>();

        for (int rowIndex = 1; rowIndex <= board.board.length; rowIndex++) {
            Piece[] rows = board.board[rowIndex - 1];
            for (int columnIndex = 1; columnIndex <= rows.length; columnIndex++) {
                Piece piece = board.board[rowIndex - 1][columnIndex - 1];
                if (piece == null) continue;
                if (!isHumanTurn && !piece.isUser() || isHumanTurn && piece.isUser()) {
                    //Use commented out if you only want to generate moves for certain types of pieces
                    moves.addAll(piece.generateMoves(board, rowIndex - 1, columnIndex - 1));


//                    if (piece instanceof King) {
//                        moves.addAll(piece.generateMoves(board, rowIndex - 1, columnIndex - 1));
//                    } else if (piece instanceof Pawn) {
//                        moves.addAll(piece.generateMoves(board, rowIndex - 1, columnIndex - 1));
//                    } else if (piece instanceof Bishop) {
//                        moves.addAll(piece.generateMoves(board, rowIndex - 1, columnIndex - 1));
//                    } else if (piece instanceof Rook) {
//                        moves.addAll(piece.generateMoves(board, rowIndex - 1, columnIndex - 1));
//                    } else if (piece instanceof Knight) {
//                        moves.addAll(piece.generateMoves(board, rowIndex - 1, columnIndex - 1));
//                    }


                }
            }

        }
        return moves;
    }
}