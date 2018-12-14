package com.ten.wsn.coverage.frame;

import com.ten.wsn.coverage.config.FrameSize;

import javax.swing.*;

public class DataFrame extends JFrame implements FrameSize {

    public DataFrame() {
        DataCanves canves = new DataCanves();
        this.setLocation(1000, 100);
        this.setSize(FrameSize.WIDTH + 2 * ORIGIN_X, FrameSize.HEIGHT + 2 * ORIGIN_Y);
        this.add(canves);
    }
}
