package com.josh.util;

import com.josh.pieces.King;
import com.josh.pieces.Pawn;

import java.util.Comparator;

public class PieceClassComparator implements Comparator<Move> {
    //negative means first move is better than teh second.
//    positive means the second move is better thant the first
    @Override
    public int compare(Move o1, Move o2) {
        int score1 = 0;
        int score2 = 0;


        //if a capture of the kings path, then put that first. then king move, then pawn capture then rest of moves
        if (o1.getPiece() instanceof King)
            return Integer.MIN_VALUE;

        if (o2.getPiece() instanceof King) {
            return Integer.MAX_VALUE;
        }

        if (o1.getPiece() instanceof Pawn) {
            if (o1.isCapture() && (o1.getPiece().isUser() && o1.getToPosition().getRow() == 6 || (!o1.getPiece().isUser() && o1.getToPosition().getRow() == 5)))
                score2++;
        }
        if (o2.getPiece() instanceof Pawn) {
            if (o2.isCapture() && (o1.getPiece().isUser() && o2.getToPosition().getRow() == 6 || (!o2.getPiece().isUser() && o1.getToPosition().getRow() == 5)))
                score2++;
        }

        if ((o1.getToPosition().getRow() == o2.getToPosition().getRow()) && o1.getToPosition().getRow() == 6)
            return 0;
        if ((o1.getToPosition().getRow() == o2.getToPosition().getRow()) && o1.getToPosition().getRow() == 0)
            return 0;
        if (o1.getToPosition().getRow() == 6)
            return Integer.MAX_VALUE;
        if (o2.getToPosition().getRow() == 0)
            return Integer.MIN_VALUE;


        if (o1.isCapture())
            score1 += 2;
        if (o2.isCapture())
            score2 += 2;

        score1 += PieceClass.valueOf(o1.getPiece().getClass().getSimpleName()).getValue();
        score2 += PieceClass.valueOf(o2.getPiece().getClass().getSimpleName()).getValue();
        return score2 - score1;

    }

    private enum PieceClass {


        King(10),
        PawnCapture(8),
        Pawn(1),
        Bishop(2),
        Knight(2),
        Rook(2);

        private int value;

        PieceClass(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
