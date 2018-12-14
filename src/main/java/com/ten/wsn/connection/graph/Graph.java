package com.ten.wsn.connection.graph;

import com.ten.wsn.connection.Vertex;
/**
 * @name Graph
 * @Description this class is
 * @anthor wang shihao
 * @date 2018/3/19
 */
public class Graph {
    private final int V;
    private int E;
    private Bag<Vertex>[] adj;
    /**
     * @name 构造函数
     * @Description 生成图的邻接表数组
     */
    public Graph(int V) {
        this.V = V;
        this.E=0;
        adj=(Bag<Vertex>[])new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Vertex>();
        }
    }

    public void addEdge(Vertex v1,Vertex v2){
        adj[v1.ID()].add(v2);
        adj[v2.ID()].add(v1);
        E++;
    }

        public Bag<Vertex> adj(Vertex v) {
        return adj[v.ID()];
    }
    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }
}
