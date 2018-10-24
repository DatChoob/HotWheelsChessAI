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

        if (o1.getPiece() instanceof Pawn &&

                (

                        (o1.getPiece().isUser() && o1.getFromPosition().getRow() == 1 && o1.getFromPosition().getColumn() == 1)
                                || (!o1.getPiece().isUser() && o1.getFromPosition().getRow() == 6 && o1.getFromPosition().getColumn() == 1)

                )
        )
            score1 += 100;

        if (o2.getPiece() instanceof Pawn &&

                (

                        (o2.getPiece().isUser() && o2.getFromPosition().getRow() == 1 && o2.getFromPosition().getColumn() == 1)
                                || (!o2.getPiece().isUser() && o2.getFromPosition().getRow() == 6 && o2.getFromPosition().getColumn() == 1)

                )
        )
            score2 += 100;


//        if (o2.getPiece() instanceof Pawn && !o2.getPiece().isUser() && (o2.getFromPosition().getRow() == 1 && o2.getFromPosition().getColumn() == 1 || o2.getFromPosition().getRow() == 6 && o2.getFromPosition().getColumn() == 1))
//            return Integer.MAX_VALUE - 1;


        if (o1.getPiece() instanceof Pawn) {
            if (o1.isCapture()
                    && ((o1.getPiece().isUser() && o1.getToPosition().getRow() == 6)
                    || (!o1.getPiece().isUser() && o1.getToPosition().getRow() == 5)))
                score1++;
        }
        if (o2.getPiece() instanceof Pawn) {
            if (o2.isCapture()
                    && ((o2.getPiece().isUser() && o2.getToPosition().getRow() == 6
                    || (!o2.getPiece().isUser() && o2.getToPosition().getRow() == 5))))
                score2++;
        }

        if ((o1.getToPosition().getRow() == o2.getToPosition().getRow()) && o1.getToPosition().getRow() == 6)
            return 0;
        if ((o1.getToPosition().getRow() == o2.getToPosition().getRow()) && o1.getToPosition().getRow() == 0)
            return 0;
        if (o1.getToPosition().getRow() == 7)
            score1 -= 100000;
        if (o2.getToPosition().getRow() == 0)
            score2 -= 100000;

//        if (o1.isCapture())
//            score1 += 2;
//        if (o2.isCapture())
//            score2 += 2;
        if (o1.getPiece().getClass().equals(o2.getPiece().getClass()) && !(o1.getPiece() instanceof Pawn)) {
            if (o1.isCapture())
                score1++;
            if (o2.isCapture())
                score2++;
        }

        score1 += PieceClass.valueOf(o1.getPiece().getClass().getSimpleName()).getValue();
        score2 += PieceClass.valueOf(o2.getPiece().getClass().getSimpleName()).getValue();
        return score2 - score1;

    }

    private enum PieceClass {


        King(10),
        PawnCapture(8),
        Pawn(3),
        Bishop(1),
        Knight(4),
        Rook(3);

        private int value;

        PieceClass(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
