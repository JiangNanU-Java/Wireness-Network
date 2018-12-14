package com.ten.wsn.connection.frame;

import com.ten.wsn.connection.config.FrameSize;

import javax.swing.*;

public class DataFrame extends JFrame {

    public DataFrame() {
        DataCanves canves = new DataCanves();
        this.setLocation(1000, 100);
        this.setSize(FrameSize.WIDTH + 2 * FrameSize.ORIGIN_X, FrameSize.HEIGHT + 2 * FrameSize.ORIGIN_Y);
        this.add(canves);
    }
}
