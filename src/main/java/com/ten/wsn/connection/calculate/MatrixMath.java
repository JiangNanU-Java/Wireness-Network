package com.ten.wsn.connection.calculate;

public class MatrixMath extends Matrix {
    private int n;

    public MatrixMath(int n, int r, int[][] aMatrix, int[][][] aMatrixs, int[][] sumMatrix) {
        this.n = n;
        this.r = r;
        this.aMatrix = aMatrix;
        this.aMatrixs = aMatrixs;
        this.sumMatrix = sumMatrix;
    }

    //计算1——(N-1)阶邻接矩阵的连通率
    public int calculatePro() {

        sumMatrix = new int[n][n];
        aMatrixs = new int[n + 1][n][n];

        //初始化sum矩阵
        for (int i = 0; i < sumMatrix.length; i++) {
            for (int j = 0; j < sumMatrix.length; j++) {
                sumMatrix[i][j] = 0;
            }
        }

//        //打印邻接矩阵
//        for (int i = 0; i < aMatrix.length; i++) {
//            for (int j = 0; j < aMatrix.length; j++) {
//                System.out.print(aMatrix[i][j]);
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//        System.out.println();

        //计算1——N-1阶 可达矩阵的值
        aMatrixs[0] = aMatrix;
        for (int i = 1; i <= n; i++) {
            aMatrixs[i] = this.mutiplyMatrix(aMatrixs[i - 1], aMatrix);
        }

        //计算1——N-1阶 可达矩阵之和
        for (int i = 0; i < sumMatrix.length; i++) {
            for (int j = 0; j < sumMatrix.length; j++) {
                for (int k = 0; k < aMatrixs.length; k++) {
                    sumMatrix[i][j] += aMatrixs[k][i][j];
                }
            }
        }

//        //打印可达矩阵
//        for (int i = 0; i < sumMatrix.length; i++) {
//            for (int j = 0; j < sumMatrix.length; j++) {
//                System.out.print(sumMatrix[i][j]+"   ");
//            }
//            System.out.println();
//        }

        boolean flag = true;

        //计算上三角连通量
        for (int i = 0; i < sumMatrix.length; i++) {
            for (int j = 0; j < sumMatrix.length; j++) {
                if (sumMatrix[i][j] == 0) {
                    flag = false;
                    break;
                }
            }
        }

        //System.out.println("节点数" + n + " 半径" + r + " 连通量" + sum_connect);

        //计算连通率
        if (flag) {
            return 1;
        } else return 0;
    }


    //n阶可达矩阵的乘法 上三角矩阵
    public int[][] mutiplyMatrix(int[][] pre_matrix, int[][] a_matrix) {
        int[][] c_matrix = new int[pre_matrix.length][pre_matrix.length];

        //初始化n阶可达矩阵
        for (int i = 0; i < c_matrix.length; i++) {
            for (int j = 0; j < c_matrix.length; j++) {
                c_matrix[i][j] = 0;
            }
        }

        //(n-1)*1=n 矩阵乘法 上三角矩阵
        for (int i = 0; i < pre_matrix.length; i++) {
            for (int j = 0; j < a_matrix.length; j++) {
                for (int k = 0; k < c_matrix.length; k++) {
                    c_matrix[i][j] += pre_matrix[i][k] * a_matrix[k][j];
                    if (c_matrix[i][j] != 0) {
                        c_matrix[i][j] = 1;
                    }
                }
            }
        }

        return c_matrix;
    }
}
