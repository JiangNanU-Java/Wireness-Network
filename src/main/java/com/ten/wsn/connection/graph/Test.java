package com.ten.wsn.connection.graph;

import com.ten.wsn.connection.Vertex;
import com.ten.wsn.connection.VertexFactory;

public class Test {
    private static VertexFactory factory;
    private static Vertex[] vertices;
    private static final int size=10;
    private static final int radiu=200;

    private static Graph G;
    private static DFS dfs;

    public static void main(String[] args) {
        factory=new VertexFactory(size);
        vertices=new Vertex[size];
        vertices=factory.createVertexSet();

        G=new Graph(size);

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {/////////////
                if (vertices[i].isConnection(vertices[j], radiu)) {
                    G.addEdge(vertices[i], vertices[j]);
                }
            }
        }

        dfs=new DFS(G,vertices[0]);
    }

}
