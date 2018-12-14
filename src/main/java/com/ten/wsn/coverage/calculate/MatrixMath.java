package com.ten.wsn.coverage.calculate;

import com.ten.wsn.coverage.vertex.Vertex;

/**
 * 计算覆盖率
 */
public class MatrixMath {

    public MatrixMath() {
    }

    /**
     * @param vSet        路由节点矩阵
     * @param flagSet     区块节点矩阵
     * @param flagConnect 覆盖矩阵
     * @param r            通信半径
     * @return
     */
    public int[][] run(Vertex[] vSet, Vertex[][] flagSet, int[][] flagConnect, int r) {
        //遍历每个路由节点
        for (Vertex aVSet : vSet) {
            //每个路由节点，遍历区块节点矩阵
            for (int j = 0; j < flagSet.length; j++) {
                for (int k = 0; k < flagSet.length; k++) {
                    //若连通，则置1
                    if (aVSet.isConnection(flagSet[j][k], r)) {
                        flagConnect[j][k] = 1;
                    }
                }
            }
        }

        return flagConnect;
    }

}
