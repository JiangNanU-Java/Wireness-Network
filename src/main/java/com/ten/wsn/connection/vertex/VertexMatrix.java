package com.ten.wsn.connection.vertex;

import java.util.Arrays;

/**
 * 创建无向图的邻接矩阵
 */
public class VertexMatrix {

    private int[][] adjacencymatrix;

    /**
     * 创建并初始化一阶邻接矩阵
     */
    public VertexMatrix(int size) {
        this.adjacencymatrix = new int[size][size];
        for (int i = 0; i < adjacencymatrix.length; i++) {
            for (int j = 0; j < adjacencymatrix.length; j++) {
                adjacencymatrix[i][j] = 0;
            }
        }
    }

    /**
     * 将连通的边置为1
     */
    public void hasConnection(int i, int j) {
        adjacencymatrix[i][j] = 1;
    }

    /**
     * 打印邻接矩阵
     */
    public void show() {
        Arrays.stream(adjacencymatrix).forEach(line -> {
            Arrays.stream(line).forEach(System.out::print);
            System.out.println();
        });
    }

    public int[][] getAdjacencymatrix() {
        return adjacencymatrix;
    }
}
