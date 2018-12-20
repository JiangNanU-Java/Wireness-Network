package com.ten.wsn.connection;

import com.ten.wsn.connection.calculate.NRMValue;
import com.ten.wsn.connection.frame.BaseLineFrame;
import com.ten.wsn.connection.frame.NumFixedFrame;
import com.ten.wsn.connection.frame.RediusFixedFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * 无线传感器网络连通率分析
 */
public class WSNConnection {

    private static List<List<NRMValue>> numFixedLines;
    private static List<List<NRMValue>> rediusFixedLines;

    public static void main(String[] args) {
        System.out.println("WSN Connection Analyse Start...");

        // Asyn :fix Radius and increase Num
        Future<List<List<NRMValue>>> futureFixR = CompletableFuture
                .supplyAsync(() -> new WSNDispatcher().startFixRadius());

        // Asyn :fix Num and increase Radius
        Future<List<List<NRMValue>>> futureFixN = CompletableFuture
                .supplyAsync(() -> new WSNDispatcher().startFixRadius());

        // wait time 10s for calculation
        try {
            numFixedLines = (futureFixR.get(10000, TimeUnit.MILLISECONDS));
            rediusFixedLines = (futureFixN.get(10000, TimeUnit.MILLISECONDS));
        }
        // exception
        catch (InterruptedException e) {
            System.out.println("Calculation thread has interrupted !!");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("Calculation has exception !!!");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Calculation has time out !!!");
            e.printStackTrace();
        }

        // Event Queue :show frame panel of lines data
        showLineFrame(numFixedLines, NumFixedFrame.class);
        showLineFrame(rediusFixedLines, RediusFixedFrame.class);

    }

    /**
     * Dynamic create objcet of BaseLineFrame, use Optional to avoid NPE
     */
    private static void showLineFrame(List<List<NRMValue>> lines,
                                      Class<? extends BaseLineFrame> type) {
        EventQueue.invokeLater(() -> {
            Optional<BaseLineFrame> object = Optional.empty();
            try {
                object = Optional.ofNullable(type.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            // if object is not empty()
            object.ifPresent((frame) -> {
                frame.pushLinesToListener(lines);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            });
        });
    }

}

