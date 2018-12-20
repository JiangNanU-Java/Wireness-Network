package com.ten.wsn.connection;

import com.ten.wsn.connection.calculate.NRMCalculation;
import com.ten.wsn.connection.calculate.NRMValue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class WSNDispatcher {

    /**
     * 节点初始数量
     */
    private static final int VERTEX_INIT_NUMBER = 1;
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
    private static final int VERTEX_INIT_RADIUS = 1;
    /**
     * 节点通信半径递增量
     */
    private static final int VERTEX_INCREASE_RADIUS = 50;
    /**
     * 节点最大通信半径
     */
    private static final int VERTEX_MAX_RADIUS = 500;

    /**
     * 节点数目数组
     */
    private List<Integer> vertexNumbers;
    /**
     * 节点通信半径数组
     */
    private List<Integer> vertexRadius;

    /**
     * 初始化 节点数目数组 以及 节点通信半径数组
     */
    WSNDispatcher() {
        vertexNumbers = new ArrayList<>(64);
        vertexRadius = new ArrayList<>(16);

        for (int n = VERTEX_INIT_NUMBER; n <= VERTEX_MAX_NUMBER; n += VERTEX_INCREASE_NUMBER) {
            vertexNumbers.add(n);
        }


        for (int r = VERTEX_INIT_RADIUS; r <= VERTEX_MAX_RADIUS; r += VERTEX_INCREASE_RADIUS) {
            vertexRadius.add(r);
        }
    }

    private List<List<NRMValue>> lines;

    /**
     * 对每个R，令N递增，异步计算得到NRM结果集合
     */
    List<List<NRMValue>> startFixRadius() {
        lines = vertexRadius
                .parallelStream()
                .map(r -> vertexNumbers
                        .parallelStream()
                        .map(n -> calculateNRM(r, n))
                        .collect(Collectors.toList())
                ).collect(Collectors.toList());

        // Line数量
        showLineNum();
        // Line数据
        showLineValue();

        return lines;
    }

    /**
     * 对每个N，令R递增，异步计算得到NRM结果集合
     */
    List<List<NRMValue>> startFixNum() {
        lines = vertexNumbers
                .parallelStream()
                .map(n -> vertexRadius
                        .parallelStream()
                        .map(r -> calculateNRM(r, n))
                        .collect(Collectors.toList())
                ).collect(Collectors.toList());

        // Line数量
        showLineNum();
        // Line数据
        showLineValue();

        return lines;
    }

    /**
     * Calculate the NRM by r/n, use Async task
     *
     * @param r radius
     * @param n number
     */
    private NRMValue calculateNRM(Integer r, Integer n) {
        // 执行异步计算任务
        Future<NRMCalculation> future = CompletableFuture
                .supplyAsync(() -> new NRMCalculation(n, r).run());

        //（n,r,value）点对象
        NRMValue newValue = new NRMValue();

        // 获取异步计算结果
        try {
            // 添加（N,R,M）点到曲线line中
            newValue.record(n, r,
                    future.get(10000, TimeUnit.MILLISECONDS).getValue());
        } catch (InterruptedException | TimeoutException e) {
            System.out.println("计算运行超时！");
            e.printStackTrace();
        } catch (ExecutionException ex) {
            System.out.println("计算过程异常！");
            ex.printStackTrace();
        }

        return newValue;
    }

    /**
     * Print how many lines with reduce operate
     */
    private void showLineNum() {
        int count = lines.stream()
                .map(line -> 1)
                .reduce(0, (a, b) -> a + b);
        System.out.println("共包含:" + count + "条曲线");
    }

    /**
     * Join line value with interval ","
     */
    private void showLineValue() {
        lines.forEach(line -> {
            String result = line.stream()
                    .map(NRMValue::toString)
                    .collect(Collectors.joining(", "));
            System.out.println(result);
        });
    }

    public static int getVertexMaxNumber() {
        return VERTEX_MAX_NUMBER;
    }

    public static int getVertexMaxRediu() {
        return VERTEX_MAX_RADIUS;
    }
}
