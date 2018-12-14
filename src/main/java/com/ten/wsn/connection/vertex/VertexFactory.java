package com.ten.wsn.connection.vertex;

/**
 * 创建Vertex随机节点的工厂
 */
public class VertexFactory {

    private int size;
    private Vertex[] vertices;

    public VertexFactory(int size) {
        this.size = size;
        this.vertices = new Vertex[size];
    }

    //返回一个大小为size的Vertex数组
    public Vertex[] createVertexSet() {
        for (int i = 0; i < size; i++) {
            Vertex v = new Vertex(i);
            vertices[i] = v;
        }
        return vertices;
    }
}
