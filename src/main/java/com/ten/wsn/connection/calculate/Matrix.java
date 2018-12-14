package com.ten.wsn.connection.calculate;

public abstract class Matrix {
    int n;
    int r;
    int[][][] aMatrixs;    //n阶可达矩阵集合 n=1,2,3...
    int[][] aMatrix;       //邻接矩阵
    int[][] sumMatrix;     //可达矩阵
}
