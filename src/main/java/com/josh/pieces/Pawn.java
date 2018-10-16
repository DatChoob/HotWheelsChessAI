package com.josh.pieces;

import com.josh.GameBoard;
import com.josh.util.BoardPosition;
import com.josh.util.Move;

import java.util.ArrayList;
import java.util.List;

import static com.josh.util.BoardPosition.getBoardPositionZeroIndex;

public class Pawn extends Piece {

    public Pawn(boolean isUser) {
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
        if (rowIndex != board.getHeight()) {
            //moving forward
            if (rowIndex - 1 >= 0)
                if (board.board[rowIndex - 1][columnIndex] == null) {
                    moves.add(new Move(currentPosition, getBoardPositionZeroIndex(rowIndex - 1, columnIndex)));
                }
            //forward right
            if (rowIndex - 1 >= 0 && columnIndex + 1 < board.getWidth())
                if (isOpponentsPiece(board.board[rowIndex - 1][columnIndex + 1]) && !(board.board[rowIndex - 1][columnIndex + 1] instanceof King)) {
                    moves.add(new Move(currentPosition, getBoardPositionZeroIndex(rowIndex - 1, columnIndex + 1)));
                }
            //forward left
            if (rowIndex - 1 >= 0 && columnIndex - 1 >= 0)
                if (isOpponentsPiece(board.board[rowIndex - 1][columnIndex - 1]) && !(board.board[rowIndex - 1][columnIndex - 1] instanceof King)) {
                    moves.add(new Move(currentPosition, getBoardPositionZeroIndex(rowIndex - 1, columnIndex - 1)));
                }
        }
    }

    private void generateMovesForUser(GameBoard board, int rowIndex, int columnIndex, List<Move> moves, BoardPosition currentPosition) {
        if (rowIndex != board.getHeight()) {
            //moving forward if empty space
            if (rowIndex + 1 < board.getHeight())
                if (board.board[rowIndex + 1][columnIndex] == null) {
                    moves.add(new Move(currentPosition, getBoardPositionZeroIndex(rowIndex + 1, columnIndex)));
                }
            //forward right
            if (rowIndex + 1 < board.getHeight() && columnIndex + 1 < board.getWidth())
                if (isOpponentsPiece(board.board[rowIndex + 1][columnIndex + 1]) && !(board.board[rowIndex + 1][columnIndex + 1] instanceof King)) {
                    moves.add(new Move(currentPosition, getBoardPositionZeroIndex(rowIndex + 1, columnIndex + 1)));
                }
            //forward left
            if (rowIndex + 1 < board.getHeight() && columnIndex - 1 >= 0)
                if (isOpponentsPiece(board.board[rowIndex + 1][columnIndex - 1]) && !(board.board[rowIndex + 1][columnIndex - 1] instanceof King)) {
                    moves.add(new Move(currentPosition, getBoardPositionZeroIndex(rowIndex + 1, columnIndex - 1)));
                }
        }
    }

    @Override
    public String toString() {
        if (isUser())
            return "p";
        else return "P";
    }
}
