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

    private static final int MAX_DEPTH = 3;


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
        long startTime = System.nanoTime();
        minimax(board);
        long finishTime = System.nanoTime();
        System.out.println("Time to generate AI moves(ms):  " + (finishTime - startTime) / 1000000.0);
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
                List<Move> moves = generateMoves(true, board);
                System.out.println("Potential Moves: " + moves.toString());
                String input = scanner.nextLine();
                if (StringUtils.isBlank(input)) continue;
                if (!board.isValidMove(input.toUpperCase().toCharArray())) continue;
                board.move(input.toUpperCase().toCharArray());
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
        List<Move> moves = new ArrayList<>();

        for (int rowIndex = 1; rowIndex <= board.board.length; rowIndex++) {
            Piece[] rows = board.board[rowIndex - 1];
            for (int columnIndex = 1; columnIndex <= rows.length; columnIndex++) {
                Piece piece = board.board[rowIndex - 1][columnIndex - 1];
                if (piece == null) continue;
                if (!isHumanTurn && !piece.isUser() || isHumanTurn && piece.isUser()) {
                    moves.addAll(piece.generateMoves(board, rowIndex - 1, columnIndex - 1));


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
        return moves;
    }


    //        MiniMax(Board)
    public void minimax(GameBoard board) {
        boolean isHumanTurn = false;
//        best.mv = [not yet defined]
        Move bestMove = new Move();
//        best.score = -9999
        bestMove.setScore(-9999);
//        For each legal move m
        for (Move move : generateMoves(isHumanTurn, board)) {
            GameBoard clonesBoard = board.clone();

            // make move m.mv on Board
            clonesBoard.move(move.toString().toCharArray());
//            m.score = MIN
            move.setScore(min(0, clonesBoard, !isHumanTurn));
//            if (m.score > best.score) then best = m
            if (move.getScore() > bestMove.getScore()) bestMove = move;
//            retract move m.mv on Board
//            board.move(move.reverse().toString().toCharArray());
            //an attempt for forcing garbage collection to free up memory
            clonesBoard = null;
        }
//        Make move best.mv
//        System.out.println("making move" + bestMove.toString());
        board.move(bestMove.toString().toCharArray());
    }

    private int min(int depth, GameBoard board, boolean isHumanTurn) {

//        MIN
//        if (game over)return EVAL - ENDING
        if (gameIsOver()) return -9999;
//else if (max depth)return EVAL
        else if (depth == MAX_DEPTH)
            return eval(board);
//else
        else {
            Move bestMove = new Move();
//        best.score = 9999
            bestMove.setScore(9999);
//        for each human legal move m.mv
            for (Move move : generateMoves(isHumanTurn, board)) {
                GameBoard clonesBoard = board.clone();
//            make move m.mv on Board
                clonesBoard.move(move.toString().toCharArray());
//            m.score = MAX
                move.setScore(max(depth + 1, clonesBoard, !isHumanTurn));
//            if (m.score < best.score) then best = m
                if (move.getScore() < bestMove.getScore()) bestMove = move;
//            retract move m.mv on Board
//                not needed since i'm cloning the board instead
//                board.move(move.reverse().toString().toCharArray());
                //an attempt for forcing garbage collection to free up memory
                clonesBoard = null;
            }
//        return best.score
            return bestMove.getScore();
        }
    }

    private int max(int depth, GameBoard board, boolean isHumanTurn) {

//        MIN
//        if (game over)return EVAL - ENDING
        if (gameIsOver()) return 9999;
//else if (max depth)return EVAL
        else if (depth == MAX_DEPTH)
            return eval(board);
//else
        else {
            Move bestMove = new Move();
//        best.score = -9999
            bestMove.setScore(-9999);
//        for each human legal move m.mv
            for (Move move : generateMoves(isHumanTurn, board)) {
                GameBoard clonesBoard = board.clone();

//            make move m.mv on Board
                clonesBoard.move(move.toString().toCharArray());
//            m.score = MIN
                move.setScore(min(depth + 1, clonesBoard, !isHumanTurn));
//            if (m.score > best.score) then best = m
                if (move.getScore() > bestMove.getScore()) bestMove = move;
//            retract move m.mv on Board
//                board.move(move.reverse().toString().toCharArray());
//an attempt for forcing garbage collection to free up memory
                clonesBoard = null;
            }
//        return best.score
            return bestMove.getScore();
        }
    }

    public int eval(GameBoard board) {
        int score = 0;
        for (Piece[] pieces : board.board) {
            for (Piece piece : pieces) {
                if (piece != null) {
                    if (piece.isUser()) {
                        score--;
                    } else score++;
                }
            }
        }
        System.out.println("Eval " + score);
        return score;
    }

}