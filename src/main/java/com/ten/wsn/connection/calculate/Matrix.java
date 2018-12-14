package com.ten.wsn.connection.calculate;

public abstract class Matrix {
    int n;
    int r;
    int[][][] a_matrixs;    //n阶可达矩阵集合 n=1,2,3...
    int[][] a_matrix;       //邻接矩阵
    int[][] sum_matrix;     //可达矩阵
}
