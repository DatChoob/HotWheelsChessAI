package com.josh.pieces;

import com.josh.GameBoard;
import com.josh.util.BoardPosition;

import java.util.ArrayList;
import java.util.List;

import static com.josh.util.BoardPosition.indexToColumnChar;

public class King extends Piece{
    public King(boolean isUser) {
        super(isUser);
    }

    public List<String> generateMoves(GameBoard board, int rowIndex, int columnIndex) {
       return generateMoves(board,rowIndex,columnIndex,false);
    }

    @Override
    public String toString() {
        if(isUser())
            return "c";
        else return "C";
    }


    public List<String> generateMoves(GameBoard board, int rowIndex, int columnIndex, boolean force){
        List<String> moves =  new ArrayList<String>();
        if(isUser()){
            if (rowIndex == 1 && columnIndex == 1 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.B2);
            } else if (rowIndex == 2 && columnIndex == 2 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.C3);
            } else if (rowIndex == 3 && columnIndex == 3 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.D4);
            } else if (rowIndex == 4 && columnIndex == 4 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.E4);
            } else if (rowIndex == 4 && columnIndex == 5 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.F4);
            } else if (rowIndex == 4 && columnIndex == 6 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.G4);
            }
        }else{
            if (rowIndex == 8 && columnIndex == 1 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.B7);
            } else if (rowIndex == 7 && columnIndex == 2 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.C6);
            } else if (rowIndex == 6 && columnIndex == 3 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.D5);
            } else if (rowIndex == 5 && columnIndex == 4 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.E5);
            } else if (rowIndex == 5 && columnIndex == 5 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.F5);
            } else if (rowIndex == 5 && columnIndex == 6 && (board.board[rowIndex-1][columnIndex-1] == null || force)) {
                moves.add(indexToColumnChar(columnIndex) + rowIndex + BoardPosition.G5);
            }
        }
        return moves;
    }
}
