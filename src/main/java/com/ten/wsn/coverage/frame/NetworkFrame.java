package com.ten.wsn.coverage.frame;

import com.ten.wsn.coverage.config.FrameSize;

import javax.swing.*;

/**
 * 创建V的网络图
 */
public class NetworkFrame extends JFrame implements FrameSize {

    public NetworkFrame(int i) {
        NetCanves canves = new NetCanves(i);
        this.setLocation(100, 100);
        this.setSize(FrameSize.WIDTH + 2 * ORIGIN_X, FrameSize.HEIGHT + 2 * ORIGIN_Y);
        this.add(canves);
    }
}
