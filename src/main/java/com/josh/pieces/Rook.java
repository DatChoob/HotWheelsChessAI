package com.josh.pieces;

import com.josh.GameBoard;
import com.josh.util.BoardPosition;
import com.josh.util.Move;

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
        //moving backward
        for (int i = rowIndex + 1; i < board.getHeight(); i++) {

            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[i][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(i, columnIndex)))
                break;// we hit a non-empty space. so break

        }
        //moving forward
        for (int i = rowIndex - 1; i >= 0; i--) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[i][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(i, columnIndex)))
                break;// we hit a non-empty space. so break
        }

        //moving right
        for (int i = columnIndex + 1; i < board.getWidth(); i++) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[rowIndex][i], moves, currentPosition, getBoardPositionZeroIndex(rowIndex, i)))
                break;// we hit a non-empty space. so break
        }

        //moving left
        for (int i = columnIndex - 1; i >= 0; i--) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[rowIndex][i], moves, currentPosition, getBoardPositionZeroIndex(rowIndex, i)))
                break;// we hit a non-empty space. so break
        }
    }

    private void generateMovesForUser(GameBoard board, int rowIndex, int columnIndex, List<Move> moves, BoardPosition currentPosition) {
        //moving forward
        for (int i = rowIndex + 1; i < board.getHeight(); i++) {
            if (!addForwardMoveAndDetermineIfContinue(board.board[i][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(i, columnIndex)))
                break;// we hit a non-empty space. so break
        }

        //moving backwards
        for (int i = rowIndex - 1; i >= 0; i--) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[i][columnIndex], moves, currentPosition, getBoardPositionZeroIndex(i, columnIndex)))
                break;// we hit a non-empty space. so break
        }

        //moving right
        for (int i = columnIndex + 1; i < board.getWidth(); i++) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[rowIndex][i], moves, currentPosition, getBoardPositionZeroIndex(rowIndex, i)))
                break;// we hit a non-empty space. so break
        }

        //moving left
        for (int i = columnIndex - 1; i >= 0; i--) {
            if (!addLeftRightBackwardMoveAndDetermineIfContinue(board.board[rowIndex][i], moves, currentPosition, getBoardPositionZeroIndex(rowIndex, i)))
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
    private boolean addForwardMoveAndDetermineIfContinue(Piece piece, List<Move> moves, BoardPosition fromPosition, BoardPosition toPosition) {
        boolean willContinueLoop = false;
        if (piece == null) {
            //if empty position, add
            moves.add(new Move(fromPosition,toPosition));
            willContinueLoop = true;
        } else if (piece instanceof King) {
            willContinueLoop = true;
        } else if (isOpponentsPiece(piece)) {
            moves.add(new Move(fromPosition,toPosition));

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
    private boolean addLeftRightBackwardMoveAndDetermineIfContinue(Piece piece, List<Move> moves, BoardPosition fromPosition, BoardPosition toPosition) {
        boolean willContinueLoop = false;
        if (piece == null) {
            //continue
            willContinueLoop = true;
        } else if (piece instanceof King) {
            willContinueLoop = true;
        } else if (isOpponentsPiece(piece)) {
            moves.add(new Move(fromPosition,toPosition));
        }
        return willContinueLoop;
    }


}
