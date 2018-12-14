package com.ten.wsn.coverage.config;

/**
 * 画布尺寸信息
 */
public interface FrameSize {
    // 画布原点坐标
    int ORIGIN_X = 100;
    int ORIGIN_Y = 100;

    // 画布终点坐标
    int X_AXIS_X = 600;
    int X_AXIS_Y = ORIGIN_Y;

    int Y_AXIS_X = ORIGIN_X;
    int Y_AXIS_Y = 600;

    //画布尺寸
    int WIDTH = 500;
    int HEIGHT = 500;
}
