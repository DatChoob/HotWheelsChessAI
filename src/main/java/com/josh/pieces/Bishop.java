package com.josh.pieces;

import com.josh.GameBoard;
import com.josh.util.BoardPosition;
import com.josh.util.Move;

import java.util.ArrayList;
import java.util.List;

import static com.josh.util.BoardPosition.getBoardPositionZeroIndex;

public class Bishop extends Piece {
    public Bishop(boolean isUser) {
        super(isUser);
    }

    public List<Move> generateMoves(GameBoard board, int rowIndex, int columnIndex) {
        List<Move> moves = new ArrayList<Move>();
        BoardPosition currentPosition = getBoardPositionZeroIndex(rowIndex, columnIndex);
        if (isUser()) {
            generateMovesForUser(board, rowIndex, columnIndex, moves, currentPosition);
        }
        if (!isUser()) {
            generateMovesForOpponent(board, rowIndex, columnIndex, moves, currentPosition);
        }
        return moves;
    }

    private void generateMovesForOpponent(GameBoard board, int rowIndex, int columnIndex, List<Move> moves, BoardPosition currentPosition) {

        //forward left
        int forwardInc = 1;
        while (rowIndex - forwardInc >= 0 && columnIndex - forwardInc >= 0) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[rowIndex - forwardInc][columnIndex - forwardInc], board.board[rowIndex][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - forwardInc, columnIndex - forwardInc)))
                break;
            forwardInc++;
        }

        //forward right
        forwardInc = 1;
        while (rowIndex - forwardInc >= 0 && columnIndex + forwardInc < board.getWidth()) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[rowIndex - forwardInc][columnIndex + forwardInc], board.board[rowIndex][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - forwardInc, columnIndex + forwardInc)))
                break;
            forwardInc++;
        }

        //backward right
        int backwardInc = 1;

        while (rowIndex + backwardInc < board.getHeight() && columnIndex + backwardInc < board.getWidth()) {
            if (!addBackwardMoveAndDetermineIfContinue(board.board[rowIndex + backwardInc][columnIndex + backwardInc], board.board[rowIndex][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + backwardInc, columnIndex + backwardInc)))
                break;
            backwardInc++;
        }

        //backward left
        backwardInc = 1;
        while (rowIndex + backwardInc < board.getHeight() && columnIndex - backwardInc >= 0) {
            if (!addBackwardMoveAndDetermineIfContinue(board.board[rowIndex + backwardInc][columnIndex - backwardInc], board.board[rowIndex][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + backwardInc, columnIndex - backwardInc)))
                break;
            backwardInc++;
        }
    }

    private void generateMovesForUser(GameBoard board, int rowIndex, int columnIndex, List<Move> moves, BoardPosition currentPosition) {
        int forwardInc = 1;
        //forward right
        while (rowIndex + forwardInc < board.getHeight() && columnIndex + forwardInc < board.getWidth()) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[rowIndex + forwardInc][columnIndex + forwardInc], board.board[rowIndex][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + forwardInc, columnIndex + forwardInc)))
                break;
            forwardInc++;
        }


        //forward left
        forwardInc = 1;
        while (rowIndex + forwardInc < board.getHeight() && columnIndex - forwardInc >= 0) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[rowIndex + forwardInc][columnIndex - forwardInc], board.board[rowIndex][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + forwardInc, columnIndex - forwardInc)))
                break;
            forwardInc++;
        }


        //backwards left
        int backwardInc = 1;
        while (rowIndex - backwardInc >= 0 && columnIndex - backwardInc >= 0) {
            if (!addBackwardMoveAndDetermineIfContinue(board.board[rowIndex - backwardInc][columnIndex - backwardInc], board.board[rowIndex][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - backwardInc, columnIndex - backwardInc)))
                break;
            backwardInc++;
        }

        //backwards right
        backwardInc = 1;
        while (rowIndex - backwardInc >= 0 && columnIndex + backwardInc < board.getWidth()) {
            if (!addBackwardMoveAndDetermineIfContinue(board.board[rowIndex - backwardInc][columnIndex + backwardInc], board.board[rowIndex][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - backwardInc, columnIndex + backwardInc)))
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
    private boolean addForwardMoveAndDetermineIfContinue(Piece piece, Piece currentPiece, List<Move> moves, BoardPosition fromPosition, BoardPosition toPosition) {
        boolean willContinueLoop = false;
        if (piece == null) {
            //if empty position, add
            moves.add(new Move(fromPosition, toPosition, currentPiece, false));
            willContinueLoop = true;
        } else if (piece instanceof King) {
            willContinueLoop = true;
        } else if (isOpponentsPiece(piece)) {
            moves.add(new Move(fromPosition, toPosition, currentPiece, true));
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
    private boolean addBackwardMoveAndDetermineIfContinue(Piece piece, Piece currentPiece, List<Move> moves, BoardPosition fromPosition, BoardPosition toPosition) {
        boolean willContinueLoop = false;
        if (piece == null) {
            //continue
            willContinueLoop = true;
        } else if (isOpponentsPiece(piece) && !(piece instanceof King)) {
            moves.add(new Move(fromPosition, toPosition, currentPiece, true));
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
