package com.josh.pieces;

import com.josh.GameBoard;

import java.util.ArrayList;
import java.util.List;

import static com.josh.util.BoardPosition.getBoardPositionZeroIndex;

public class Knight extends Piece {
    public Knight(boolean isUser) {
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


        /**
         *
         * +-1 +-2
         *
         * +-2 +-1
         *
         *
         *
         */
    }

    private void generateMovesForOpponent(GameBoard board, int rowIndex, int columnIndex, List<String> moves, String currentPosition) {

        //forward


        //        +1 -2
        if (rowIndex - 2 >= 0 && columnIndex + 1 < board.getWidth()) {
            addForwardMove(board.board[rowIndex - 2][columnIndex + 1], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - 2, columnIndex + 1).toString());
        }

//-1 -2
        if (rowIndex - 2 >= 0 && columnIndex - 1 >= 0) {
            addForwardMove(board.board[rowIndex - 2][columnIndex - 1], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - 2, columnIndex - 1).toString());
        }
//+2 -1
        if (rowIndex - 1 >= 0 && columnIndex + 2 < board.getWidth()) {
            addForwardMove(board.board[rowIndex - 1][columnIndex + 2], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - 1, columnIndex + 2).toString());
        }
//-2 -1
        if (rowIndex - 1 >= 0 && columnIndex - 2 >= 0) {
            addForwardMove(board.board[rowIndex - 1][columnIndex - 2], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - 1, columnIndex - 2).toString());
        }

        //backward
        //        +1 +2
        if (rowIndex + 2 < board.getHeight() && columnIndex + 1 < board.getWidth()) {
            addBackwardMove(board.board[rowIndex + 2][columnIndex + 1], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + 2, columnIndex + 1).toString());
        }

//-1 +2
        if (rowIndex + 2 < board.getHeight() && columnIndex - 1 >= 0) {
            addBackwardMove(board.board[rowIndex + 2][columnIndex - 1], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + 2, columnIndex - 1).toString());
        }
//+2 +1
        if (rowIndex + 1 < board.getHeight() && columnIndex + 2 < board.getWidth()) {
            addBackwardMove(board.board[rowIndex + 1][columnIndex + 2], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + 1, columnIndex + 2).toString());
        }
//-2 +1
        if (rowIndex + 1 < board.getHeight() && columnIndex - 2 >= 0) {
            addBackwardMove(board.board[rowIndex + 1][columnIndex - 2], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + 1, columnIndex - 2).toString());
        }





    }

    private void generateMovesForUser(GameBoard board, int rowIndex, int columnIndex, List<String> moves, String currentPosition) {
//forwards
//        +1 +2
        if (rowIndex + 2 < board.getHeight() && columnIndex + 1 < board.getWidth()) {
            addForwardMove(board.board[rowIndex + 2][columnIndex + 1], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + 2, columnIndex + 1).toString());
        }

//-1 +2
        if (rowIndex + 2 < board.getHeight() && columnIndex - 1 >= 0) {
            addForwardMove(board.board[rowIndex + 2][columnIndex - 1], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + 2, columnIndex - 1).toString());
        }
//+2 +1
        if (rowIndex + 1 < board.getHeight() && columnIndex + 2 < board.getWidth()) {
            addForwardMove(board.board[rowIndex + 1][columnIndex + 2], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + 1, columnIndex + 2).toString());
        }
//-2 +1
        if (rowIndex + 1 < board.getHeight() && columnIndex - 2 >= 0) {
            addForwardMove(board.board[rowIndex + 1][columnIndex - 2], moves, currentPosition, getBoardPositionZeroIndex(rowIndex + 1, columnIndex - 2).toString());
        }


        //backwards


        //        +1 -2
        if (rowIndex - 2 >= 0 && columnIndex + 1 < board.getWidth()) {
            addBackwardMove(board.board[rowIndex - 2][columnIndex + 1], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - 2, columnIndex + 1).toString());
        }

//-1 -2
        if (rowIndex - 2 >= 0 && columnIndex - 1 >= 0) {
            addBackwardMove(board.board[rowIndex - 2][columnIndex - 1], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - 2, columnIndex - 1).toString());
        }
//+2 -1
        if (rowIndex - 1 >= 0 && columnIndex + 2 < board.getWidth()) {
            addBackwardMove(board.board[rowIndex - 1][columnIndex + 2], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - 1, columnIndex + 2).toString());
        }
//-2 -1
        if (rowIndex - 1 >= 0 && columnIndex - 2 >= 0) {
            addBackwardMove(board.board[rowIndex - 1][columnIndex - 2], moves, currentPosition, getBoardPositionZeroIndex(rowIndex - 1, columnIndex - 2).toString());
        }
    }

    private void addForwardMove(Piece piece, List<String> moves, String fromPosition, String toPosition) {

        String move = fromPosition + toPosition;
        if (piece == null || isOpponentsPiece(piece)) {
            //if empty or opponent position, add
            moves.add(move);
        }
    }

    private void addBackwardMove(Piece piece, List<String> moves, String fromPosition, String toPosition) {
        String move = fromPosition + toPosition;
        if (isOpponentsPiece(piece)) {
            //if opponent position, add
            moves.add(move);
        }
    }

    @Override
    public String toString() {
        if (isUser())
            return "n";
        else return "N";
    }


}
