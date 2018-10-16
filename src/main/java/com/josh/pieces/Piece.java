package com.josh.pieces;

import com.josh.GameBoard;
import com.josh.util.Move;

import java.util.List;

public abstract class Piece {

    private boolean isUser;

    public Piece(boolean isUser) {
        this.isUser = isUser;
    }

    public boolean isUser() {
        return isUser;
    }

    abstract public List<Move> generateMoves(GameBoard board, int rowIndex, int columnIndex);

    public boolean isOpponentsPiece(Piece piece){
        return piece != null && (piece.isUser() != this.isUser);

    }
}
