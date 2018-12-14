package com.ten.wsn.coverage;

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

    private Vertex[][] flag_matrix;    //划分节点矩阵
    private static Vertex[] vset;      //路由节点矩阵
    private int[][] flag_con; //判断节点矩阵

    private static double[] fugailv=new double[8];
    private static int count=0;
    private static ArrayList<int[][]> arrayList=new ArrayList<>();

    private MatrixMath matrix_math;

    public CalculationThread(int n, int r) {
        this.n = n; //路由节点个数
        this.r = r; //通信半径
        this.run();
    }

    private void run() {
        for (int repeat = 0; repeat < CIRCLE; repeat++) {

            //500*500
            this.createMatrix();

            if(r==25){
                createVertexs();
            }

            flag_con = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    flag_con[i][j] = 0;
                }
            }

            //☆计算覆盖率 20 r
            this.matrix_math = new MatrixMath();
            flag_con = matrix_math.run(vset, flag_matrix, flag_con, r);
            arrayList.add(flag_con);

            int one_count = 0;
            int zero_count = 0;
            for (int i = 0; i < flag_con.length; i++) {
                for (int j = 0; j < flag_con.length; j++) {
                    if (flag_con[i][j] == 1) {
                        one_count++;
                    } else {
                        zero_count++;
                    }
                }
            }

            double fugai=(double) one_count / (one_count + zero_count);
            fugailv[count++]=fugai;
            System.out.println("覆盖个数" + one_count + "未覆盖个数" + zero_count);
            System.out.println("覆盖率" + fugai);
        }
    }

    //创建区块节点矩阵
    private void createMatrix() {
        flag_matrix = new VertexFactory(SIZE).createFlagVertexMatrix();
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
