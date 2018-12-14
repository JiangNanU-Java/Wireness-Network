package com.ten.wsn.connection.calculate;

import com.ten.wsn.connection.MainTest;
import com.ten.wsn.connection.config.PositionInFrame;

import static com.ten.wsn.connection.config.FrameSize.*;

/**
 * 一条曲线上的点类型：（N,R,M）
 */
public class NRMValue implements PositionInFrame {
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

    @Override
    public int getX() {
        return (int) ((double) this.getN() / MainTest.getmaxRadiu() * WIDTH * 5 + ORIGIN_X);
    }

    @Override
    public int getY() {
        return (Y_AXIS_Y - (int) (this.getMean() * HEIGHT));
    }
}
