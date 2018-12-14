package com.ten.wsn.connection;

/**
 * 画布尺寸信息
 */
public interface FrameSize {
    // 画布原点坐标
    static final int Origin_X = 100;
    static final int Origin_Y = 100;

    // 画布终点坐标
    static final int XAxis_X = 600;
    static final int XAxis_Y = Origin_Y;

    static final int YAxis_X = Origin_X;
    static final int YAxis_Y = 600;

    //画布尺寸
    static final int WIDTH = 500;
    static final int HEIGHT = 500;
}
