package com.ten.wsn.connection.graph;

import com.ten.wsn.connection.Vertex;

public class DFS {
    private boolean[] marked;
    private int[] edgeTo;
    private final Vertex s;

    public DFS(Graph G,Vertex s) {
        marked=new boolean[G.getV()];
        edgeTo=new int[G.getV()];
        this.s=s;
        dfs(G,s);
    }

    private void dfs(Graph G, Vertex v) {
        marked[v.ID()]=true;
        System.out.println(G.adj(v));
        for (Vertex w:G.adj(v)) {
            if (!marked[w.ID()]){
                edgeTo[w.ID()]=v.ID();
                dfs(G,w);
            }
        }
    }
}
