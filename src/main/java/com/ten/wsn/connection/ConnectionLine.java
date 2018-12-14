package com.ten.wsn.connection;

import java.util.ArrayList;

/**
 * N曲线的集合：N曲线本身为ArrayList类型
 */
public class ConnectionLine extends ArrayList<NRMValue> {
    //所有n的连通率曲线集合
    private static ArrayList<ConnectionLine> r_list = new ArrayList<ConnectionLine>();

    public ConnectionLine() {
        r_list.add(this);
    }

    public static ArrayList<ConnectionLine> getR_list() {
        return r_list;
    }
}
