package com.ten.wsn.coverage.config;

/**
 * 画布尺寸信息
 */
public interface FrameSize {
    // 画布原点坐标
    int Origin_X = 100;
    int Origin_Y = 100;

    // 画布终点坐标
    int XAxis_X = 600;
    int XAxis_Y = Origin_Y;

    int YAxis_X = Origin_X;
    int YAxis_Y = 600;

    //画布尺寸
    int WIDTH = 500;
    int HEIGHT = 500;
}
