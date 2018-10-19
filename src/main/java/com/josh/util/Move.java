package com.josh.util;


import com.josh.pieces.Piece;

public class Move {
    private BoardPosition fromPosition;
    private BoardPosition toPosition;
    private int score;
    private Piece piece;
    private boolean isCapture;

    public Move() {
    }

    public Move(String move) {
        char[] input = move.toCharArray();
        this.fromPosition = BoardPosition.getBoardPositionZeroIndex(Character.getNumericValue(input[1]) - 1, BoardPosition.columnCharToIndex(input[0]));
        this.toPosition = BoardPosition.getBoardPositionZeroIndex(Character.getNumericValue(input[3]) - 1, BoardPosition.columnCharToIndex(input[2]));
    }

    public Move(BoardPosition fromPosition, BoardPosition toPosition, Piece piece, boolean isCapture) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        this.piece = piece;
        this.isCapture = isCapture;
    }

    public BoardPosition getFromPosition() {
        return fromPosition;
    }

    public void setFromPosition(BoardPosition fromPosition) {
        this.fromPosition = fromPosition;
    }

    public BoardPosition getToPosition() {
        return toPosition;
    }

    public void setToPosition(BoardPosition toPosition) {
        this.toPosition = toPosition;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public Piece getPiece() {
        return piece;
    }

    public boolean isCapture() {
        return isCapture;
    }


    @Override
    public String toString() {
        return fromPosition.toString() + toPosition.toString();
    }
}
