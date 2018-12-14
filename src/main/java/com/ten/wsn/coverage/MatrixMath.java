package com.ten.wsn.coverage;

/**
 * 计算覆盖率
 *
 */
public class MatrixMath {

    public MatrixMath(){
    }
    /**
     * @param v_set 路由节点矩阵
     * @param flag_set 区块节点矩阵
     * @param flag_connect 覆盖矩阵
     * @param R 通信半径
     * @return
     */
    public int[][] run(Vertex[] v_set,Vertex[][] flag_set,int[][] flag_connect,int R){
        //遍历每个路由节点
        for (int i = 0; i < v_set.length; i++) {
            //每个路由节点，遍历区块节点矩阵
            for (int j = 0; j < flag_set.length; j++) {
                for (int k = 0; k < flag_set.length; k++) {
                    //若连通，则置1
                    if (v_set[i].isConnection(flag_set[j][k],R)){
                        flag_connect[j][k]=1;
                    }
                }
            }
        }

        return flag_connect;
    }

}
