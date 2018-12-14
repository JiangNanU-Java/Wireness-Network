package com.ten.wsn.connection.vertex;

/**
 * 创建无向图的邻接矩阵
 */
public class VertexMatrix {

    private int[][] aMatrix;

    //创建并初始化一阶邻接矩阵
    public VertexMatrix(int size) {
        this.aMatrix = new int[size][size];
        //初始化为0
        for (int i = 0; i < aMatrix.length; i++) {
            for (int j = 0; j < aMatrix.length; j++) {
                aMatrix[i][j] = 0;
            }
        }
    }

    //将连通的边置为1
    public void hasConnection(int i, int j) {
        aMatrix[i][j] = 1;
    }

    //打印邻接矩阵
    public void show() {
        for (int i = 0; i < aMatrix.length; i++) {
            for (int j = 0; j < aMatrix.length; j++) {
                System.out.print(aMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] getaMatrix() {
        return aMatrix;
    }
}
