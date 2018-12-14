package com.ten.wsn.connection;

/**
 * 创建Vertex随机节点的工厂
 */
public class VertexFactory {
    private int size;
    private Vertex[] v_set;

    public VertexFactory(int size) {
        this.size = size;
        this.v_set = new Vertex[size];
    }

    //返回一个大小为size的Vertex数组
    public Vertex[] createVertexSet() {
        for (int i = 0; i < size; i++) {
            Vertex v = new Vertex(i);
            v_set[i] = v;
        }
        return v_set;
    }
}
