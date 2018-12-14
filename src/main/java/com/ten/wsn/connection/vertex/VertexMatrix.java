package com.ten.wsn.connection.vertex;

/**
 * 创建无向图的邻接矩阵
 */
public class VertexMatrix {
    private int[][] A_matrix;

    //创建并初始化一阶邻接矩阵
    public VertexMatrix(int size) {
        this.A_matrix = new int[size][size];
        //初始化为0
        for (int i = 0; i < A_matrix.length; i++) {
            for (int j = 0; j < A_matrix.length; j++) {
                A_matrix[i][j] = 0;
            }
        }
    }

    //将连通的边置为1
    public void hasConnection(int i, int j) {
        A_matrix[i][j] = 1;
    }

    //打印邻接矩阵
    public void show() {
        for (int i = 0; i < A_matrix.length; i++) {
            for (int j = 0; j < A_matrix.length; j++) {
                System.out.print(A_matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] getA_matrix() {
        return A_matrix;
    }
}
