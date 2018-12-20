package com.ten.wsn.connection.calculate;

/**
 * 邻接矩阵,可达矩阵 的矩阵计算数学方法
 */
class MatrixMathUtil {

    /**
     * n阶矩阵
     */
    private int n;
    /**
     * N阶可达矩阵集合
     */
    private int[][][] reachableMatrixSet;
    /**
     * 邻接矩阵
     */
    private int[][] adjacencymatrix;
    /**
     * N阶可达矩阵之和
     */
    private int[][] reachablematrix;

    static MatrixMathUtil getMatrixMathUtil(int n, int[][] adjacencyMatrix) {
        MatrixMathUtil util = new MatrixMathUtil();
        util.n = n;
        util.adjacencymatrix = adjacencyMatrix;
        util.reachablematrix = new int[n][n];
        util.reachableMatrixSet = new int[n][n][n];
        return util;
    }

    /**
     * 根据可达矩阵算法计算连通率
     *
     * @return 0:不连通  1:连通
     */
    int isMatrixConnected() {
        for (int i = 0; i < n; i++) {
            // 计算I阶可达矩阵
            reachableMatrixSet[i] = (i == 0) ? adjacencymatrix : mutiplyMatrix(reachableMatrixSet[i - 1], adjacencymatrix);

            for (int j = 0; j < n; j++) {
                reachablematrix[i][j] = 0;

                for (int k = 0; k < n; k++) {
                    // 计算[i][j]节点的N阶可达矩阵之和
                    reachablematrix[i][j] += reachableMatrixSet[k][i][j];
                }

                // N阶可达矩阵之和是否有零值 ? 不连通 : 连通
                if (reachablematrix[i][j] == 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    /**
     * [1] N阶可达矩阵 = 矩阵乘法[(N-1)阶可达矩阵 * 邻接矩阵]
     * [2] 可达矩阵：数值代表距离 -> 将正数设为1，代表连通即可(方便后续乘法运算)
     *
     * @param preReachMatrix  N-1阶可达矩阵
     * @param adjacencyMatrix 邻接矩阵
     * @return reachMatrix N阶可达矩阵
     */
    private int[][] mutiplyMatrix(int[][] preReachMatrix, int[][] adjacencyMatrix) {
        int length = adjacencyMatrix.length;

        int[][] reachMatrix = new int[length][length];

        // 矩阵乘法
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    reachMatrix[i][j] += preReachMatrix[i][k] * adjacencyMatrix[k][j];
                    if (reachMatrix[i][j] != 0) {
                        // [2]
                        reachMatrix[i][j] = 1;
                    }
                }
            }
        }

        return reachMatrix;
    }

}
