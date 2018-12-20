package com.ten.wsn.connection.calculate;

import com.ten.wsn.connection.frame.NetworkFrame;
import com.ten.wsn.connection.vertex.Vertex;
import com.ten.wsn.connection.vertex.VertexFactory;
import com.ten.wsn.connection.vertex.VertexMatrix;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * 对一组（N,R）的主要运算，求（N,R,Value）的连通率
 */
public class NRMCalculation {

    private int n;
    private int r;
    private double mean;
    private int[] means;

    /**
     * FIXME 1000次重复 求平均连通率
     * 连通率：若所有节点都可连通，则连通；有一个节点不连通，则不连通
     */
    private static final int CIRCLE = 100;

    private VertexMatrix matrix;
    private Vertex[] vertices;

    /**
     * 矩阵计算工具
     */
    private MatrixMathUtil matrixMath;
    /**
     * 一阶邻接矩阵
     */
    private int[][] adjacencyMatrix;

    public NRMCalculation(int n, int r) {
        this.n = n;
        this.r = r;
        this.means = new int[CIRCLE];
    }

    /**
     * Calculate means by n/r
     */
    public NRMCalculation run() {
        for (int repeat = 0; repeat < CIRCLE; repeat++) {

            // 初始化矩阵
            matrix = new VertexMatrix(n);

            // 创建随机节点数组
            vertices = VertexFactory.createVertexSet(n);

            // 计算一阶邻接矩阵
            calculateMatrix();

            // 获取邻接矩阵
            adjacencyMatrix = matrix.getAdjacencymatrix();

            // 建立矩阵运算对象
            matrixMath = MatrixMathUtil.getMatrixMathUtil(n, adjacencyMatrix);

            // 计算连通率
            means[repeat] = matrixMath.isMatrixConnected();
        }
        mean = valueOfMean(means);

        System.out.println("节点数" + n + " 半径" + r + " 连通率：" + mean);

        return this;
    }

    /**
     * 节点随机分布图
     */
    private static void createVertexFrame(NRMCalculation obj) {
        EventQueue.invokeLater(() -> {
            NetworkFrame network = new NetworkFrame(obj);
            network.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            network.setVisible(true);
        });
    }

    /**
     * 计算一阶邻接矩阵
     */
    private void calculateMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vertices[i].isConnection(vertices[j], r)) {
                    matrix.hasConnection(i, j);
                }
            }
        }
    }

    /**
     * 计算连通率平均值
     */
    private double valueOfMean(int[] means) {
        double count = Arrays.stream(means)
                .reduce(0, (a, b) -> a + b);

        double sum = 0;
        for (int mean1 : means) {
            sum += mean1;
        }

        // FIXME 测试reduce操作
        System.out.println("连通率测试：reduse->" + count + ",foreack->" + sum);

        return sum / means.length;
    }

    public double getValue() {
        return mean;
    }

    public int getN() {
        return n;
    }

    public int getR() {
        return r;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public VertexMatrix getMatrix() {
        return matrix;
    }
}
