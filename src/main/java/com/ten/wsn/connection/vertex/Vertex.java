package com.ten.wsn.connection.vertex;

import com.ten.wsn.connection.config.PositionInFrame;
import com.ten.wsn.connection.config.FrameSize;

import static com.ten.wsn.connection.config.FrameSize.HEIGHT;
import static com.ten.wsn.connection.config.FrameSize.ORIGIN_X;
import static com.ten.wsn.connection.config.FrameSize.ORIGIN_Y;

/**
 * Vertex in frame
 */
public class Vertex implements PositionInFrame {

    /**
     * frame position
     */
    private double x, y;

    /**
     * array identifier
     */
    private int id;

    Vertex() {
        this(0);
    }

    /**
     * Random position in frame
     */
    Vertex(int i) {
        this.id = i;
        this.x = Math.random() * FrameSize.WIDTH + ORIGIN_X;
        this.y = Math.random() * HEIGHT + ORIGIN_Y;
    }

    /**
     * Judge two vertex whether connected or not by "Euclidean distance"
     *
     * @param anotherV this and another
     * @param r        radius
     */
    public boolean isConnection(Vertex anotherV, int r) {
        return ((this.x - anotherV.x) * (this.x - anotherV.x) + ((this.y - anotherV.y) * (this.y - anotherV.y)))
                <= (r * r);
    }

    @Override
    public int getX() {
        return (int) x;
    }

    @Override
    public int getY() {
        return (int) y;
    }

}
