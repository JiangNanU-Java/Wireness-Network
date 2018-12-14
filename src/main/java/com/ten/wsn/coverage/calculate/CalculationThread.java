package com.ten.wsn.coverage.calculate;

import com.ten.wsn.coverage.vertex.Vertex;
import com.ten.wsn.coverage.vertex.VertexFactory;

import java.util.ArrayList;

/**
 * 对一组（N,R）的主要运算
 * 求（N,R,Value）的覆盖率
 */
public class CalculationThread {

    private static int n;
    private int r;
    private static final int CIRCLE = 1; //重复次数
    private static final int SIZE = 500; //划分矩阵的横纵划分值

    private Vertex[][] flagMatrix;    //划分节点矩阵
    private static Vertex[] vset;      //路由节点矩阵
    private int[][] flagCon; //判断节点矩阵

    private static double[] fugailv = new double[8];
    private static int count = 0;
    private static ArrayList<int[][]> arrayList = new ArrayList<>();

    private MatrixMath matrixMath;

    public CalculationThread(int n, int r) {
        CalculationThread.n = n; //路由节点个数
        this.r = r; //通信半径
        this.run();
    }

    private void run() {
        for (int repeat = 0; repeat < CIRCLE; repeat++) {

            //500*500
            this.createMatrix();

            if (r == 25) {
                createVertexs();
            }

            flagCon = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    flagCon[i][j] = 0;
                }
            }

            //☆计算覆盖率 20 r
            this.matrixMath = new MatrixMath();
            flagCon = matrixMath.run(vset, flagMatrix, flagCon, r);
            arrayList.add(flagCon);

            int oneCount = 0;
            int zeroCount = 0;
            for (int[] aFlagCon : flagCon) {
                for (int j = 0; j < flagCon.length; j++) {
                    if (aFlagCon[j] == 1) {
                        oneCount++;
                    } else {
                        zeroCount++;
                    }
                }
            }

            double fugai = (double) oneCount / (oneCount + zeroCount);
            fugailv[count++] = fugai;
            System.out.println("覆盖个数" + oneCount + "未覆盖个数" + zeroCount);
            System.out.println("覆盖率" + fugai);
        }
    }

    //创建区块节点矩阵
    private void createMatrix() {
        flagMatrix = new VertexFactory(SIZE).createFlagVertexMatrix();
    }

    //创建路由节点的集合
    private static void createVertexs() {
        vset = new VertexFactory(n).createVertexSet();
    }

    public static Vertex[] getVset() {
        return vset;
    }

    public static double[] getFugailv() {
        return fugailv;
    }

    public static ArrayList<int[][]> getArrayList() {
        return arrayList;
    }
}
