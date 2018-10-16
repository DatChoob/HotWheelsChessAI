package com.josh.pieces;

import com.josh.GameBoard;

import static com.josh.util.BoardPosition.getBoardPositionZeroIndex;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(boolean isUser) {
        super(isUser);
    }


    @Override
    public String toString() {
        if (isUser())
            return "r";
        else return "R";
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
        //moving backward
        for (int i = rowIndex + 1; i < board.getHeight(); i++) {

            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[i][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(i, columnIndex).toString()))
                break;// we hit a non-empty space. so break

        }
        //moving forward
        for (int i = rowIndex - 1; i >= 0; i--) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[i][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(i, columnIndex).toString()))
                break;// we hit a non-empty space. so break
        }

        //moving right
        for (int i = columnIndex + 1; i < board.getHeight(); i++) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[rowIndex][i], moves, currentPosition, getBoardPositionZeroIndex(rowIndex, i).toString()))
                break;// we hit a non-empty space. so break
        }

        //moving left
        for (int i = columnIndex - 1; i >= 0; i--) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[rowIndex][i], moves, currentPosition, getBoardPositionZeroIndex(rowIndex, i).toString()))
                break;// we hit a non-empty space. so break
        }
    }

    private void generateMovesForUser(GameBoard board, int rowIndex, int columnIndex, List<String> moves, String currentPosition) {
        //moving forward
        for (int i = rowIndex + 1; i < board.getHeight(); i++) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[i][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(i, columnIndex).toString()))
                break;// we hit a non-empty space. so break
        }

        //moving backwards
        for (int i = rowIndex - 1; i >= 0; i--) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[i][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(i, columnIndex).toString()))
                break;// we hit a non-empty space. so break
        }

        //moving right
        for (int i = columnIndex + 1; i < board.getHeight(); i++) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[rowIndex][i], moves, currentPosition, getBoardPositionZeroIndex(rowIndex, i).toString()))
                break;// we hit a non-empty space. so break
        }

        //moving left
        for (int i = columnIndex - 1; i >= 0; i--) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[rowIndex][i], moves, currentPosition, getBoardPositionZeroIndex(rowIndex, i).toString()))
                break;// we hit a non-empty space. so break
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
    private boolean addLeftRightBackwardMoveAndDetermineIfContinue(Piece piece, List<String> moves, String fromPosition, String toPosition) {
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


}
