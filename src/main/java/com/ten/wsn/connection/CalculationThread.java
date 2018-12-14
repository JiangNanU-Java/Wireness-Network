package com.ten.wsn.connection;

import javax.swing.*;
import java.awt.*;

/**
 * 对一组（N,R）的主要运算
 * 求（N,R,Value）的连通率
 */
public class CalculationThread extends Matrix {

    private int n;
    private int r;
    private double mean;
    private int[] means;    //1000次重复 连通率
    private static final int circle = 1;

    private VertexMatrix matrix;
    private VertexFactory factory;
    private Vertex[] vset;

    private MatrixMath matrix_math;

    public CalculationThread(int n, int r) {
        this.n = n;
        this.r = r;
        this.means = new int[circle];
        this.run();
    }

    private void run() {
        for (int repeat = 0; repeat < circle; repeat++) {

            //创建邻接矩阵
            this.createMatrix();
            //创建随机节点
            this.createVertexs();
            //计算一阶连通矩阵的连通性
            this.calculateMatrix();

            if ((repeat == 0 && n == 20 && r == 175)) {
                //绘制节点分布图:绘制两个代表图
                createVertexFrame(this);
                //打印邻接矩阵
                //matrix.show();
            }

            this.a_matrix = matrix.getA_matrix();  //获取邻接矩阵

            //建立矩阵运算对象
            this.matrix_math = new MatrixMath(n, r, a_matrix, a_matrixs, sum_matrix);
            //计算连通率
            means[repeat] = matrix_math.calculatePro();
        }
        mean = valueOfMean(means);
        System.out.println("节点数" + n + " 半径" + r + " 连通率：" + mean);
    }

    //节点随机分布图

    private static void createVertexFrame(CalculationThread obj) {
        EventQueue.invokeLater(() -> {
            NetworkFrame network = new NetworkFrame(obj);
            network.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            network.setVisible(true);
        });
    }

    //创建并初始化一阶邻接矩阵
    private void createMatrix() {
        matrix = new VertexMatrix(n);
    }

    //创建节点的集合
    private void createVertexs() {
        factory = new VertexFactory(n);
        vset = factory.createVertexSet();
    }

    //计算一阶邻接矩阵
    private void calculateMatrix() {
        //遍历上三角数组
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vset[i].isConnection(vset[j], r)) {
                    matrix.hasConnection(i, j);
                }
            }
        }
    }

    //计算连通率平均值
    private double valueOfMean(int[] means) {
        double sum = 0;
        for (int i = 0; i < means.length; i++) {
            sum += means[i];
        }
        return sum / means.length;
    }

    //获取连通率平均值
    public double getValue() {
        return mean;
    }

    public int getN() {
        return n;
    }

    public int getR() {
        return r;
    }

    public Vertex[] getVset() {
        return vset;
    }

    public VertexMatrix getMatrix() {
        return matrix;
    }
}
