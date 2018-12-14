package com.ten.wsn.connection;

public class MatrixMath extends Matrix {
    private int n;

    public MatrixMath(int n, int r, int[][] a_matrix, int[][][] a_matrixs, int[][] sum_matrix) {
        this.n = n;
        this.r = r;
        this.a_matrix = a_matrix;
        this.a_matrixs = a_matrixs;
        this.sum_matrix = sum_matrix;
    }

    //计算1——(N-1)阶邻接矩阵的连通率
    public int calculatePro() {

        sum_matrix = new int[n][n];
        a_matrixs = new int[n + 1][n][n];

        //初始化sum矩阵
        for (int i = 0; i < sum_matrix.length; i++) {
            for (int j = 0; j < sum_matrix.length; j++) {
                sum_matrix[i][j] = 0;
            }
        }

//        //打印邻接矩阵
//        for (int i = 0; i < a_matrix.length; i++) {
//            for (int j = 0; j < a_matrix.length; j++) {
//                System.out.print(a_matrix[i][j]);
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//        System.out.println();

        //计算1——N-1阶 可达矩阵的值
        a_matrixs[0] = a_matrix;
        for (int i = 1; i <= n; i++) {
            a_matrixs[i] = this.mutiplyMatrix(a_matrixs[i - 1], a_matrix);
        }

        //计算1——N-1阶 可达矩阵之和
        for (int i = 0; i < sum_matrix.length; i++) {
            for (int j = 0; j < sum_matrix.length; j++) {
                for (int k = 0; k < a_matrixs.length; k++) {
                    sum_matrix[i][j] += a_matrixs[k][i][j];
                }
            }
        }

//        //打印可达矩阵
//        for (int i = 0; i < sum_matrix.length; i++) {
//            for (int j = 0; j < sum_matrix.length; j++) {
//                System.out.print(sum_matrix[i][j]+"   ");
//            }
//            System.out.println();
//        }

        boolean flag = true;

        //计算上三角连通量
        for (int i = 0; i < sum_matrix.length; i++) {
            for (int j = 0; j < sum_matrix.length; j++) {
                if (sum_matrix[i][j] == 0) {
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
