package com.ten.wsn.connection.frame;

import com.ten.wsn.connection.calculate.CalculationThread;
import com.ten.wsn.connection.config.FrameSize;

import javax.swing.*;

/**
 * 创建V的网络图
 */
public class NetworkFrame extends JFrame {
    private NetCanves canves;

    public NetworkFrame(CalculationThread obj) {
        canves = new NetCanves(obj);
        this.setLocation(100, 100);
        this.setSize(FrameSize.WIDTH + 2 * FrameSize.ORIGIN_X, FrameSize.HEIGHT + 2 * FrameSize.ORIGIN_Y);
        this.add(canves);
    }
}
