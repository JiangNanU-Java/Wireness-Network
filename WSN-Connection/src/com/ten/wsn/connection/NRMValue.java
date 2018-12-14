package com.ten.wsn.connection;

/**
 * 一条曲线上的点类型：（N,R,M）
 */
public class NRMValue implements PositionInFrame, FrameSize {
    private int n;
    private int r;
    private double mean;

    public void record(int n, int r, double mean) {
        this.n = n;
        this.r = r;
        this.mean = mean;
    }

    public int getN() {
        return n;
    }

    public int getR() {
        return r;
    }

    public double getMean() {
        return mean;
    }

    public int getX() {
        return (int) ((double)this.getN() / MainTest.getMax_radiu() * WIDTH * 5+ Origin_X);
    }

    public int getY() {
        return (YAxis_Y - (int) (this.getMean() * HEIGHT));
    }
}
