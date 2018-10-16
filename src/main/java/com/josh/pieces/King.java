package com.josh.pieces;

import com.josh.GameBoard;
import com.josh.util.BoardPosition;
import com.josh.util.Move;

import java.util.ArrayList;
import java.util.List;

import static com.josh.util.BoardPosition.getBoardPositionZeroIndex;

public class King extends Piece {
    public King(boolean isUser) {
        super(isUser);
    }

    public List<Move> generateMoves(GameBoard board, int rowIndex, int columnIndex) {
        return generateMoves(board, rowIndex, columnIndex, false);
    }

    @Override
    public String toString() {
        if (isUser())
            return "c";
        else return "C";
    }


    public List<Move> generateMoves(GameBoard board, int rowIndex, int columnIndex, boolean force) {
        List<Move> moves = new ArrayList<Move>();
        if (isUser()) {
            if (rowIndex == 1 && columnIndex == 1 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.B2));
            } else if (rowIndex == 2 && columnIndex == 2 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.C3));
            } else if (rowIndex == 3 && columnIndex == 3 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.D4));
            } else if (rowIndex == 4 && columnIndex == 4 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.E4));
            } else if (rowIndex == 4 && columnIndex == 5 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.F4));
            } else if (rowIndex == 4 && columnIndex == 6 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.G4));
            }
        } else {
            if (rowIndex == 8 && columnIndex == 1 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.B7));
            } else if (rowIndex == 7 && columnIndex == 2 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.C6));
            } else if (rowIndex == 6 && columnIndex == 3 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.D5));
            } else if (rowIndex == 5 && columnIndex == 4 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.E5));
            } else if (rowIndex == 5 && columnIndex == 5 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.F5));
            } else if (rowIndex == 5 && columnIndex == 6 && (board.board[rowIndex - 1][columnIndex - 1] == null || force)) {
                moves.add(new Move(getBoardPositionZeroIndex(rowIndex, columnIndex), BoardPosition.G5));
            }
        }
        return moves;
    }


    public static int getScoreBasedOnPosition(GameBoard board) {
        //human
        if (board.board[0][0] instanceof King)
            return 1;
        else if (board.board[1][1] instanceof King)
            return 3;
        else if (board.board[2][2] instanceof King)
            return 5;
        else if (board.board[3][3] instanceof King)
            return 7;
        else if (board.board[3][4] instanceof King)
            return 9;
        else if (board.board[3][5] instanceof King)
            return 11;
        else if (board.board[3][6] instanceof King)
            return 999;

//ai
        else if (board.board[7][0] instanceof King)
            return 1;
        else if (board.board[6][1] instanceof King)
            return 3;
        else if (board.board[5][2] instanceof King)
            return 5;
        else if (board.board[4][3] instanceof King)
            return 7;
        else if (board.board[4][4] instanceof King)
            return 9;
        else if (board.board[4][5] instanceof King)
            return 11;
        else if (board.board[4][6] instanceof King)
            return 999;
        else
            return 0;
    }

}
