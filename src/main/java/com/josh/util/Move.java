package com.josh.util;

public class Move {
    private BoardPosition fromPosition;
    private BoardPosition toPosition;
    private int score;

    public Move() {
    }

    public Move(BoardPosition fromPosition, BoardPosition toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
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

    @Override
    public String toString() {
        return fromPosition.toString() + toPosition.toString();
    }
}