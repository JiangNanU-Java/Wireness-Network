package com.ten.wsn.coverage.vertex;

/**
 * 创建Vertex节点的工厂
 */
public class VertexFactory {
    private int size;
    private Vertex[] v_set;     //创建节点矩阵--一维
    private Vertex[][] flag_set;   //创建标记矩阵--二维

    public VertexFactory(int size) {
        this.size = size;
        this.v_set = new Vertex[size];
        this.flag_set = new Vertex[size][size];
    }

    //返回一个大小为size的Vertex数组
    public Vertex[] createVertexSet() {
        for (int i = 0; i < size; i++) {
            Vertex v = new Vertex(i);
            v_set[i] = v;
        }
        return v_set;
    }

    //创建标记节点的矩阵
    public Vertex[][] createFlagVertexMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Vertex v = new Vertex(i + 100, j + 100);   //每个节点的位置为(x.y) --> 每隔一个像素标记一个节点
                flag_set[i][j] = v;
            }
        }
        return flag_set;
    }

}
