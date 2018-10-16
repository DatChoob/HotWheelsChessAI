package com.josh.pieces;

import com.josh.GameBoard;

import java.util.ArrayList;
import java.util.List;

import static com.josh.util.BoardPosition.getBoardPositionZeroIndex;

public class Bishop extends Piece {
    public Bishop(boolean isUser) {
        super(isUser);
    }

    public List<String> generateMoves(GameBoard board, int rowIndex, int columnIndex) {
        List<String> moves = new ArrayList<String>();
        String currentPosition = getBoardPositionZeroIndex(rowIndex, columnIndex).toString();
        if (isUser()) {
            generateMovesForUser(board, rowIndex, columnIndex, moves, currentPosition);
        }
        if (!isUser()) {
            generateMovesForOpponent(board, rowIndex, columnIndex, moves, currentPosition);
        }
        return moves;
    }

    private void generateMovesForOpponent(GameBoard board, int rowIndex, int columnIndex, List<String> moves, String currentPosition) {

        //forward left
        int forwardInc = 1;
        while (rowIndex - forwardInc >= 0 && columnIndex - forwardInc >= 0) {
            if (!addBackwardMoveAndDetermineIfContinue(board.board[rowIndex - forwardInc][columnIndex - forwardInc], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - forwardInc, columnIndex - forwardInc).toString()))
                break;
            forwardInc++;
        }

        //forward right
        forwardInc = 1;
        while (rowIndex - forwardInc >= 0 && columnIndex + forwardInc < board.getWidth()) {
            if (!addBackwardMoveAndDetermineIfContinue(board.board[rowIndex - forwardInc][columnIndex + forwardInc], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - forwardInc, columnIndex + forwardInc).toString()))
                break;
            forwardInc++;
        }

        //backward right
        int backwardInc = 1;

        while (rowIndex + backwardInc < board.getHeight() && columnIndex + backwardInc < board.getWidth()) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[rowIndex + backwardInc][columnIndex + backwardInc], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + backwardInc, columnIndex + backwardInc).toString()))
                break;
            backwardInc++;
        }

        //backward left
        backwardInc=1;
        while (rowIndex + backwardInc < board.getHeight() && columnIndex - backwardInc >= 0) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[rowIndex + backwardInc][columnIndex - backwardInc], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + backwardInc, columnIndex - backwardInc).toString()))
                break;
            backwardInc++;
        }
    }

    private void generateMovesForUser(GameBoard board, int rowIndex, int columnIndex, List<String> moves, String currentPosition) {
        int forwardInc = 1;
        //forward right
        while (rowIndex + forwardInc < board.getHeight() && columnIndex + forwardInc < board.getWidth()) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[rowIndex + forwardInc][columnIndex + forwardInc], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + forwardInc, columnIndex + forwardInc).toString()))
                break;
            forwardInc++;
        }

        //forward left
        forwardInc=1;
        while (rowIndex + forwardInc < board.getHeight() && columnIndex - forwardInc >= 0) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[rowIndex + forwardInc][columnIndex - forwardInc], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + forwardInc, columnIndex - forwardInc).toString()))
                break;
            forwardInc++;
        }


        //backwards left
        int backwardInc = 1;
        while (rowIndex - backwardInc >= 0 && columnIndex - backwardInc >= 0) {
            if (!addBackwardMoveAndDetermineIfContinue(board.board[rowIndex - backwardInc][columnIndex - backwardInc], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - backwardInc, columnIndex - backwardInc).toString()))
                break;
            backwardInc++;
        }

        //backwards right
        backwardInc = 1;
        while (rowIndex - backwardInc >= 0 && columnIndex + backwardInc < board.getWidth()) {
            if (!addBackwardMoveAndDetermineIfContinue(board.board[rowIndex - backwardInc][columnIndex + backwardInc], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - backwardInc, columnIndex + backwardInc).toString()))
                break;
            backwardInc++;
        }

    }


    /**
     * @param piece
     * @param moves
     * @param fromPosition
     * @param toPosition
     * @return true if we should continue checking pieces
     */
    private boolean addForwardMoveAndDetermineIfContinue(Piece piece, List<String> moves, String fromPosition, String toPosition) {
        boolean willContinueLoop = false;
        String move = fromPosition + toPosition;
        if (piece == null) {
            //if empty position, add
            moves.add(move);
            willContinueLoop = true;
        } else if (isOpponentsPiece(piece)) {
            moves.add(move);
        }
        return willContinueLoop;
    }

    /**
     * @param piece
     * @param moves
     * @param fromPosition
     * @param toPosition
     * @return true if we should continue checking pieces
     */
    private boolean addBackwardMoveAndDetermineIfContinue(Piece piece, List<String> moves, String fromPosition, String toPosition) {
        String move = fromPosition + toPosition;
        boolean willContinueLoop = false;
        if (piece == null) {
            //continue
            willContinueLoop = true;
        } else if (isOpponentsPiece(piece)) {
            moves.add(move);
        }
        return willContinueLoop;
    }

    @Override
    public String toString() {
        if (isUser())
            return "b";
        else return "B";
    }
}
