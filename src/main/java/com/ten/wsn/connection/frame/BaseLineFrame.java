package com.ten.wsn.connection.frame;

import com.ten.wsn.connection.calculate.NRMValue;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class BaseLineFrame extends JFrame implements LinesListener {
    /**
     * Canvas Object
     */
    BaseLineCanvas lineCanvas;

    /**
     * initialize Canvas Object
     */
    abstract void setLineCanvas();

    /**
     * put lines to lines
     */
    @Override
    public void pushLinesToListener(List<List<NRMValue>> lines) {
        this.lineCanvas.lines = lines;
    }

    abstract class BaseLineCanvas extends Canvas {
        /**
         * [[nrm, nrm...], [nrm, nrm...], [nrm, nrm...]...]
         */
        List<List<NRMValue>> lines;

        Color[] colors = new Color[]{
                Color.GREEN,
                Color.BLUE,
                Color.orange,
                Color.RED,
                Color.MAGENTA
        };

        /**
         * Cycle of colors array, use [num % COLORS_CYCLE] to avoid IndexOutException
         */
        final int COLORS_CYCLE = colors.length;

        /**
         * paint graphics
         */
        @Override
        public abstract void paint(Graphics g);
    }
}
