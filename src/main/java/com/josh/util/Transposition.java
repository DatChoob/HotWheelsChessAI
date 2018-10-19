package com.josh.util;

public class Transposition {

    private int alpha;
    private int beta;
    private int score;
    private int depth;

    public Transposition(int alpha, int beta, int score, int depth) {
        this.alpha = alpha;
        this.beta = beta;
        this.score = score;
        this.depth = depth;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
