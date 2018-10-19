package com.josh;

import com.josh.pieces.*;
import com.josh.util.*;

import java.util.*;


public class Game {
    GameBoard board;
    private boolean playerTurn;
    private Scanner scanner = new Scanner(System.in);

    private static final int MAX_DEPTH = 20;
    private static final int MAX_TIME = 4900;
    private Map<String, Transposition> table = new HashMap<>();


    public Game(GameBoard board, boolean initialMoveIsPlayer) {
        this.board = board;
        this.playerTurn = initialMoveIsPlayer;
    }

    public void play() {
        int turn = 0;
        while (!gameIsOver(board)) {
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
        if (userWon(board)) {
            System.out.println("Human has won!");

        } else if (aiWon(board)) {
            System.out.println(Color.BLACK_BACKGROUND_BRIGHT + "Computer has won!");
        }
        System.out.println("turns " + turn);
    }


    public void interativeDeepeningMinimax(GameBoard board, boolean isHumanTurn) {
        long startTime = System.currentTimeMillis();
        long endTime = 0;
        Move bestMove = null;
        int depth = 1;
        table.clear();
        while ((endTime = System.currentTimeMillis()) - startTime <= MAX_TIME) {
            Move move = minimax(board, isHumanTurn, startTime, depth);
            bestMove = move;

            System.out.println("Finished trying depth " + depth + " Considered move " + move.toString()+ " " +move.getScore());
            System.out.println("elapsed time: " + (endTime - startTime));

            if (depth == MAX_DEPTH)
                break;
            depth++;
        }
        System.out.println("Finished finding a move. Elapsed time: " + (endTime - startTime));
        System.out.println("Move made: " + bestMove.toString() + "  " + BoardPosition.generateOpponentEquivalentMove(bestMove.toString()));
        System.out.println("Best Move Score: " + bestMove.getScore());
        board.move(bestMove);
    }

    //        MiniMax(Board)
    public Move minimax(GameBoard board, boolean isHumanTurn, long startTime, int maxDepth) {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        GameBoard clonesBoard;
        Move bestMove = new Move();
        if (!isHumanTurn) {
            bestMove.setScore(Integer.MIN_VALUE);
            for (Move move : generateMoves(isHumanTurn, board)) {
                clonesBoard = board.clone();
                clonesBoard.move(move);
                move.setScore(min(0, clonesBoard, !isHumanTurn, alpha, beta, startTime, maxDepth));
                if (move.getScore() > bestMove.getScore()) bestMove = move;
                //an attempt for forcing garbage collection to free up memory
                clonesBoard = null;

                beta = Math.min(beta, bestMove.getScore());
                if (beta <= alpha)
                    break;
            }
        } else {
            bestMove.setScore(Integer.MAX_VALUE);

            for (Move move : generateMoves(isHumanTurn, board)) {
                clonesBoard = board.clone();
                clonesBoard.move(move);
                move.setScore(max(0, clonesBoard, !isHumanTurn, alpha, beta, startTime, maxDepth));
                if (move.getScore() < bestMove.getScore()) bestMove = move;
                //an attempt for forcing garbage collection to free up memory
                clonesBoard = null;

                alpha = Math.max(alpha, bestMove.getScore());
                if (beta <= alpha)
                    break;
            }

        }



        return bestMove;
    }

    private int min(int depth, GameBoard board, boolean isHumanTurn, int alpha, int beta, long startTime, int maxDepth) {

        if (gameIsOver(board)) return -10000;
        else if (depth == maxDepth)
            return eval(board);
        else if (System.currentTimeMillis() - startTime > MAX_TIME) {
//            System.out.println("MAX TIME REACHED");
            return Integer.MIN_VALUE + 1;
        } else {
            Transposition entry = table.get(board.boardTo1DString());
            if (entry != null && entry.getDepth() >= depth) {
                alpha = Math.max(alpha, entry.getScore());
                if (beta <= alpha) {
                    return entry.getScore();
                }
            }

            Move bestMove = new Move();
            bestMove.setScore(Integer.MAX_VALUE);
            for (Move move : generateMoves(isHumanTurn, board)) {
                GameBoard clonesBoard = board.clone();
                clonesBoard.move(move);
                move.setScore(max(depth + 1, clonesBoard, !isHumanTurn, alpha, beta, startTime, maxDepth));
                if (move.getScore() < bestMove.getScore()) bestMove = move;
                //an attempt for forcing garbage collection to free up memory
                clonesBoard = null;

                alpha = Math.max(alpha, bestMove.getScore());
                if (beta <= alpha)
                    break;
            }

            Transposition transposition = new Transposition(alpha, beta, bestMove.getScore(), depth);
            table.put(board.boardTo1DString(), transposition);
            return bestMove.getScore();
        }
    }

    private int max(int depth, GameBoard board, boolean isHumanTurn, int alpha, int beta, long startTime, int maxDepth) {

        if (gameIsOver(board)) return 10000;
        else if (depth == maxDepth)
            return eval(board);
        else if (System.currentTimeMillis() - startTime > MAX_TIME) {
//            System.out.println("MAX TIME REACHED");
            return Integer.MAX_VALUE - 1;
        } else {

            Transposition entry = table.get(board.boardTo1DString());
            if (entry != null && entry.getDepth() >= depth) {
                beta = Math.min(beta, entry.getScore());
                if (beta <= alpha) {
                    return entry.getScore();
                }
            }
            Move bestMove = new Move();
            bestMove.setScore(Integer.MIN_VALUE);
            for (Move move : generateMoves(isHumanTurn, board)) {

                GameBoard clonesBoard = board.clone();
                clonesBoard.move(move);
                move.setScore(min(depth + 1, clonesBoard, !isHumanTurn, alpha, beta, startTime, maxDepth));
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
//                        score++;
                        if (piece instanceof Rook)
                            score -= 50;
                        else if (piece instanceof Pawn)
                            score -= 10;
                        else if (piece instanceof Knight)
                            score -= 30;
                        else if (piece instanceof Bishop)
                            score -= 30;
                        else if (piece instanceof King)
                            score -= King.getScoreBasedOnPosition(board,true);
                    } else {
//                        score++;
                        if (piece instanceof Rook)
                            score += 50;
                        else if (piece instanceof Pawn)
                            score += 10;
                        else if (piece instanceof Knight)
                            score += 30;
                        else if (piece instanceof Bishop)
                            score += 30;
                        else if (piece instanceof King)
                            score += King.getScoreBasedOnPosition(board,false);
                    }
                }
            }
        }
//        //System.out.println("Eval " + score);
        return score;
    }


    public boolean userWon() {
        return userWon(board);
    }

    public boolean aiWon() {
        return aiWon(board);
    }

    private boolean userWon(GameBoard board) {
        return board.board[3][6] instanceof King && board.board[3][6].isUser();

    }

    private boolean aiWon(GameBoard board) {
        return board.board[4][6] instanceof King && !board.board[4][6].isUser();
    }

    private boolean gameIsOver(GameBoard board) {
        return userWon(board) || aiWon(board);
    }

    private void moveComputer() {
        long startTime = System.nanoTime();
        interativeDeepeningMinimax(board, false);
        long finishTime = System.nanoTime();
        //System.out.println("Time to complete AI move(ms):  " + (finishTime - startTime) / 1000000.0);
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
//                List<Move> moves = generateMoves(true, board);
//                //System.out.println("Potential Moves: " + moves.toString());
//                String input = scanner.nextLine();
//                if (StringUtils.isBlank(input)) continue;
//                if (!board.isValidMove(input.toUpperCase().toCharArray(), moves)) continue;
//                board.move(new Move(input.toUpperCase()));
                interativeDeepeningMinimax(board, true);
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
//                        moves.addAll(piece.generateMoves(board, rowIndex, columnIndex));
//                    } else if (piece instanceof Pawn) {
//                        moves.addAll(piece.generateMoves(board, rowIndex, columnIndex));
//                    } else if (piece instanceof Bishop) {
//                        moves.addAll(piece.generateMoves(board, rowIndex, columnIndex));
//                    } else if (piece instanceof Rook) {
//                        moves.addAll(piece.generateMoves(board, rowIndex, columnIndex));
//                    } else if (piece instanceof Knight) {
//                        moves.addAll(piece.generateMoves(board, rowIndex, columnIndex));
//                    }
                }
            }
        }

        if (moves.isEmpty())
            //find king, then force generate
            moves.addAll(forceGenerateKingMove(isHumanTurn));

        moves.sort(new PieceClassComparator());

//        //System.out.println("Generating moves took "+ (System.nanoTime()-startTime)/1000000.0 + "ms to generate " + moves.size() + "moves");
        return moves;
    }


}