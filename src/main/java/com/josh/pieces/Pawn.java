package com.josh.pieces;

import com.josh.GameBoard;

import java.util.ArrayList;
import java.util.List;

import static com.josh.util.BoardPosition.getBoardPositionZeroIndex;

public class Pawn extends Piece {

    public Pawn(boolean isUser) {
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
        if (rowIndex != board.getHeight()) {
            //moving forward
            if (board.board[rowIndex - 1][columnIndex] == null) {
                moves.add(currentPosition + getBoardPositionZeroIndex(rowIndex - 1, columnIndex).toString());
            }
            //forward right
            if (columnIndex + 1 != board.getWidth())
                if (isOpponentsPiece(board.board[rowIndex - 1][columnIndex + 1])) {
                    moves.add(currentPosition + getBoardPositionZeroIndex(rowIndex - 1, columnIndex + 1).toString());
                }
            //forward left
            if (columnIndex != 0)
                if (isOpponentsPiece(board.board[rowIndex - 1][columnIndex - 1])) {
                    moves.add(currentPosition + getBoardPositionZeroIndex(rowIndex - 1, columnIndex - 1).toString());
                }
        }
    }

    private void generateMovesForUser(GameBoard board, int rowIndex, int columnIndex, List<String> moves, String currentPosition) {
        if (rowIndex != board.getHeight()) {
            //moving forward if empty space
            if (board.board[rowIndex + 1][columnIndex] == null) {
                moves.add(currentPosition + getBoardPositionZeroIndex(rowIndex + 1, columnIndex).toString());
            }
            //forward right
            if (columnIndex + 1 != board.getWidth())
                if (isOpponentsPiece(board.board[rowIndex + 1][columnIndex + 1])) {
                    moves.add(currentPosition + getBoardPositionZeroIndex(rowIndex + 1, columnIndex + 1).toString());
                }
            //forward left
            if (columnIndex != 0)
                if (isOpponentsPiece(board.board[rowIndex + 1][columnIndex - 1])) {
                    moves.add(currentPosition + getBoardPositionZeroIndex(rowIndex + 1, columnIndex - 1).toString());
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
