package com.ten.wsn.connection.vertex;

import com.ten.wsn.connection.config.PositionInFrame;
import com.ten.wsn.connection.config.FrameSize;

import static com.ten.wsn.connection.config.FrameSize.HEIGHT;
import static com.ten.wsn.connection.config.FrameSize.ORIGIN_X;
import static com.ten.wsn.connection.config.FrameSize.ORIGIN_Y;

/**
 * Vertex 随机分布节点
 */
public class Vertex implements PositionInFrame {
    private double x, y;
    private int id;

    //随机生成位置
    public Vertex(int i) {
        this.id = i;
        this.x = (double) (Math.random() * FrameSize.WIDTH) + ORIGIN_X;
        this.y = (double) (Math.random() * HEIGHT) + ORIGIN_Y;
    }

    //判断通信距离为R时，两点间是否连通
    public boolean isConnection(Vertex anotherV, int r) {
        //计算欧氏距离 直角三角形定理
        return ((this.x - anotherV.x) * (this.x - anotherV.x) + (this.y - anotherV.y) * (this.y - anotherV.y)) <= (r * r);
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
