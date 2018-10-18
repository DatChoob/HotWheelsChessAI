package com.josh;

import com.josh.pieces.*;
import com.josh.util.BoardPosition;
import com.josh.util.Color;
import com.josh.util.Move;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


public class Game {
    GameBoard board;
    private boolean playerTurn;
    private Scanner scanner = new Scanner(System.in);

    private static final int MAX_DEPTH = 7;


    public Game(GameBoard board, boolean initialMoveIsPlayer) {
        this.board = board;
        this.playerTurn = initialMoveIsPlayer;
    }

    public void play() {
        int turn = 0;
        while (!gameIsOver()) {
            try {
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
                turn++;
                playerTurn = !playerTurn;
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
                break;
            }
        }
        System.out.println("turns " + turn);
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
        long startTime = System.nanoTime();
        minimax(board, false);
        long finishTime = System.nanoTime();
        System.out.println("Time to complete AI move(ms):  " + (finishTime - startTime) / 1000000.0);
    }


    private List<Move> forceGenerateKingMove(boolean isHumanTurn) {
        for (int rowIndex = 0; rowIndex < board.board.length; rowIndex++) {
            Piece[] rows = board.board[rowIndex];
            for (int columnIndex = 0; columnIndex < rows.length; columnIndex++) {
                Piece piece = board.board[rowIndex][columnIndex];
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
                List<Move> moves = generateMoves(true, board);
                System.out.println("Potential Moves: " + moves.toString());
                String input = scanner.nextLine();
                if (StringUtils.isBlank(input)) continue;
                if (!board.isValidMove(input.toUpperCase().toCharArray(), moves)) continue;
                board.move(new Move(input.toUpperCase()));
                break;
            } catch (NoSuchElementException nsee) {
                //only thrown if we terminate program when it's expecting an input, ignore this
                return;
            } catch (Exception e) {
                System.err.println("Error reached move player method");
                e.printStackTrace();
                System.err.println(e.getMessage());
                return;
            }
        } while (true);
    }


    public List<Move> generateMoves(boolean isHumanTurn, GameBoard board) {
        long startTime = System.nanoTime();
        List<Move> moves = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < board.board.length; rowIndex++) {
            Piece[] rows = board.board[rowIndex];
            for (int columnIndex = 0; columnIndex < rows.length; columnIndex++) {
                Piece piece = board.board[rowIndex][columnIndex];
                if (piece == null) continue;
                if (!isHumanTurn && !piece.isUser() || isHumanTurn && piece.isUser()) {
                    moves.addAll(piece.generateMoves(board, rowIndex, columnIndex));
//                    Use commented out if you only want to generate moves for certain types of pieces
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

        if (moves.isEmpty())
            //find king, then force generate
            moves.addAll(forceGenerateKingMove(isHumanTurn));

//        System.out.println("Generating moves took "+ (System.nanoTime()-startTime)/1000000.0 + "ms to generate " + moves.size() + "moves");
        return moves;
    }


    //        MiniMax(Board)
    public void minimax(GameBoard board, boolean isHumanTurn) {
        int alpha = -99999;
        int beta = 99999;
        Move bestMove = new Move();
        bestMove.setScore(-9999);
        for (Move move : generateMoves(isHumanTurn, board)) {
            GameBoard clonesBoard = board.clone();
            clonesBoard.move(move);
            move.setScore(min(0, clonesBoard, !isHumanTurn, alpha, beta));
            if (move.getScore() > bestMove.getScore()) bestMove = move;
            //an attempt for forcing garbage collection to free up memory
            clonesBoard = null;

            beta = Math.min(beta, bestMove.getScore());
            if (beta <= alpha)
                break;
        }
        System.out.println("Move made: " + bestMove.toString() + "  " + BoardPosition.generateOpponentEquivalentMove(bestMove.toString()));
        System.out.println("Best Move Score: " + bestMove.getScore());
        board.move(bestMove);
    }

    private int min(int depth, GameBoard board, boolean isHumanTurn, int alpha, int beta) {

        if (gameIsOver()) return -9999;
        else if (depth == MAX_DEPTH)
            return eval(board);
        else {
            Move bestMove = new Move();
            bestMove.setScore(9999);
            for (Move move : generateMoves(isHumanTurn, board)) {
                GameBoard clonesBoard = board.clone();
                clonesBoard.move(move);
                move.setScore(max(depth + 1, clonesBoard, !isHumanTurn, alpha, beta));
                if (move.getScore() < bestMove.getScore()) bestMove = move;
                //an attempt for forcing garbage collection to free up memory
                clonesBoard = null;

                alpha = Math.max(alpha, bestMove.getScore());
                if (beta <= alpha)
                    break;
            }
            return bestMove.getScore();
        }
    }

    private int max(int depth, GameBoard board, boolean isHumanTurn, int alpha, int beta) {

        if (gameIsOver()) return 9999;
        else if (depth == MAX_DEPTH)
            return eval(board);
        else {
            Move bestMove = new Move();
            bestMove.setScore(-9999);
            for (Move move : generateMoves(isHumanTurn, board)) {
                GameBoard clonesBoard = board.clone();
                clonesBoard.move(move);
                move.setScore(min(depth + 1, clonesBoard, !isHumanTurn, alpha, beta));
                if (move.getScore() > bestMove.getScore()) bestMove = move;
                //an attempt for forcing garbage collection to free up memory
                clonesBoard = null;

                beta = Math.min(beta, bestMove.getScore());
                if (beta <= alpha)
                    break;
            }
            return bestMove.getScore();
        }
    }

    public int eval(GameBoard board) {
        int score = 0;
        for (Piece[] pieces : board.board) {
            for (Piece piece : pieces) {
                if (piece != null) {
                    if (piece.isUser()) {
                        if (piece instanceof Rook)
                            score -= 15;
                        else if (piece instanceof Pawn)
                            score -= 2;
                        else if (piece instanceof Knight)
                            score -= 8;
                        else if (piece instanceof Bishop)
                            score -= 10;
                        else if (piece instanceof King)
                            score -= King.getScoreBasedOnPosition(board);
                    } else {
                        if (piece instanceof Rook)
                            score += 15;
                        else if (piece instanceof Pawn)
                            score += 2;
                        else if (piece instanceof Knight)
                            score += 8;
                        else if (piece instanceof Bishop)
                            score += 10;
                        else if (piece instanceof King)
                            score += King.getScoreBasedOnPosition(board);
                    }
                }
            }
        }
//        System.out.println("Eval " + score);
        return score;
    }


}