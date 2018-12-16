package com.ten.wsn.connection;

import com.ten.wsn.connection.calculate.CalculationThread;
import com.ten.wsn.connection.calculate.NRMValue;
import com.ten.wsn.connection.frame.DataFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 无线传感器网络连通率分析
 */
public class WSNConnection {

    /**
     * 节点初始数量
     */
    private static final int VERTEX_INIT_NUMBER = 0;
    /**
     * 节点数目递增量
     */
    private static final int VERTEX_INCREASE_NUMBER = 20;
    /**
     * 节点最大数目
     */
    private static final int VERTEX_MAX_NUMBER = 100;
    /**
     * 节点初始通信半径
     */
    private static final int VERTEX_INIT_REDIU = 1;
    /**
     * 节点通信半径递增量
     */
    private static final int VERTEX_INCREASE_REDIU = 50;
    /**
     * 节点最大通信半径
     */
    private static final int VERTEX_MAX_REDIU = 500;

    /**
     * 节点数目数组
     */
    private static List<Integer> vertexNumbers;
    /**
     * 节点通信半径数组
     */
    private static List<Integer> vertexRedius;

    public static void main(String[] args) {
        init();
        runFixN();
    }

    /**
     * 初始化 节点数目数组 以及 节点通信半径数组
     */
    private static void init() {
        vertexNumbers = new ArrayList<>(64);
        vertexRedius = new ArrayList<>(16);

        for (int n = VERTEX_INIT_NUMBER; n <= VERTEX_MAX_NUMBER; n += VERTEX_INCREASE_NUMBER) {
            vertexNumbers.add(n);
        }


        for (int r = VERTEX_INIT_REDIU; r <= VERTEX_MAX_REDIU; r += VERTEX_INCREASE_REDIU) {
            vertexRedius.add(r);
        }
    }

    private static List<List<NRMValue>> lines;

    /**
     * 固定节点数量 N， 节点通信半径递增 R
     */
    private static void runFixN() {
        lines = vertexRedius.parallelStream().map(
                r -> {
                    //fix n 曲线对象

                    List<NRMValue> line = vertexNumbers.stream()
                            .map(
                                    n -> {
                                        //（n,r,value）点对象
                                        NRMValue newValue = new NRMValue();

                                        CalculationThread thread = new CalculationThread(n, r);

                                        //添加（N,R,M）点到曲线line中
                                        newValue.record(n, r, thread.getValue());

                                        return newValue;
                                    }
                            ).collect(Collectors.toList());


                    System.out.println("finish one n");

                    return line;
                }
        ).collect(Collectors.toList());

        System.out.println("finish all n ");

        lines.forEach(line -> {
            String result = line.stream().map(NRMValue::toString).collect(Collectors.joining(", "));
            System.out.println(result);
        });

        int count = lines.stream()
                .map(line -> 1)
                .reduce(0, (a, b) -> a + b);

        System.out.println("包含:" + count + "条曲线");

        // 绘制连通率图
        createDataFrame();
    }

    /**
     * 生成结果数据图
     */
    private static void createDataFrame() {
        EventQueue.invokeLater(() -> {
            DataFrame network = new DataFrame();
            network.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            network.setVisible(true);
        });
    }

    public static int getmaxRadiu() {
        return VERTEX_MAX_REDIU;
    }

    public static List<List<NRMValue>> getLines() {
        return lines;
    }
}

