package com.josh.util;

import com.josh.pieces.Piece;

import java.util.Comparator;

public class PieceClassComparator implements Comparator<Move> {

    @Override
    public int compare(Move o1, Move o2) {
        return PieceClass.valueOf(o1.getPiece().getClass().getSimpleName()).getValue() - PieceClass.valueOf(o2.getPiece().getClass().getSimpleName()).getValue();
    }

    private enum PieceClass {


        King(5),
        Knight(10),
        Pawn(20),
        Bishop(40),
        Rook(30);

        private int value;

        PieceClass(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
