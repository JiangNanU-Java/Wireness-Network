package com.ten.wsn.coverage;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 */
public class MainTest {

    private static int vertex_size; //n=20
    private static int[] connection_radiu; //五级功率

    public static void main(String[] args) throws InterruptedException {
        init();
        run_fixN();
        run_Paint();
    }

    private static void run_Paint() throws InterruptedException {
        //绘制连通率图
        for (int i = 0; i < connection_radiu.length; i++) {
            createVertexFrame(i);
            Thread.sleep(1000);
        }
        createDataFrame();
    }

    //生成结果数据图
    private static void createDataFrame() {
        EventQueue.invokeLater(() -> {
            DataFrame network = new DataFrame();
            network.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            network.setVisible(true);
        });
    }

    //初始化节点个数和通信半径
    private static void init() {
        vertex_size = 20;  //节点数量
        connection_radiu = new int[]{25,75,125,175,225,275,325,375}; //五级通信半径 .05 .15 .25 .35 .45
    }

    //fix n 循环
    private static void run_fixN() {
        for (int r : connection_radiu) {
            new CalculationThread(vertex_size, r);
        }
    }

    //节点随机分布图
    private static void createVertexFrame(int i) {
        EventQueue.invokeLater(() -> {
            NetworkFrame network = new NetworkFrame(i);
            network.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            network.setVisible(true);
        });
    }
}

