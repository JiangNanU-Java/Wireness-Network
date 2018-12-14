package com.ten.wsn.connection;

import com.ten.wsn.connection.calculate.CalculationThread;
import com.ten.wsn.connection.calculate.NRMValue;
import com.ten.wsn.connection.frame.DataFrame;
import com.ten.wsn.connection.line.ConnectionLine;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 */
public class MainTest {

    private static int vertex_size;
    private static int[] connection_radiu;
    private static int vertex_change;
    private static int max_size;
    private static int max_radiu;

    public static void main(String[] args) {
        init();
        runFixn();
        //run_fixR();
    }

    //初始化节点个数和通信半径
    private static void init() {
        vertex_size = 0;  //节点数量
        connection_radiu = new int[]{25, 75, 125, 175, 225}; //通信半径从0开始变化
        vertex_change = 2;      //r变化率为0.02 (frame长宽为500)
        max_size = 100;
        max_radiu = 500;
    }

    //fix n 循环
    private static void runFixn() {
        for (int r : connection_radiu) {
            //fix n 曲线对象
            ConnectionLine line = new ConnectionLine();

            for (int n = vertex_size; n <= max_size + 1; n = n + vertex_change) {
                //（n,r,value）点对象
                NRMValue newValue = new NRMValue();

                CalculationThread thread = new CalculationThread(n, r);

                //添加（N,R,M）点到曲线line中
                newValue.record(n, r, thread.getValue());
                line.add(newValue);
            }

            System.out.println("finish one n");
        }

        System.out.println("finish all n");
        //绘制连通率图
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

    public static int getmaxRadiu() {
        return max_radiu;
    }
}

