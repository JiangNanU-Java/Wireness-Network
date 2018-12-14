package com.ten.wsn.connection;

/**
 * Vertex 随机分布节点
 */
public class Vertex implements PositionInFrame, FrameSize {
    private double x, y;
    private int id;

    //随机生成位置
    public Vertex(int i) {
        this.id = i;
        this.x = (double) (Math.random() * WIDTH) + Origin_X;
        this.y = (double) (Math.random() * HEIGHT) + Origin_Y;
    }

    //判断通信距离为R时，两点间是否连通
    public boolean isConnection(Vertex another_v, int R) {
        //计算欧氏距离 直角三角形定理
        if (((this.x - another_v.x) * (this.x - another_v.x) + (this.y - another_v.y) * (this.y - another_v.y)) <= (R * R)) {
            return true;
        }
        return false;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int ID() {
        return this.id;
    }
}
