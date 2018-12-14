package com.ten.wsn.connection.graph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @name Bag
 * @Description this class is
 * @anthor wang shihao
 * @date 2018/3/19
 */
public class Bag<Vertex> implements Iterable<Vertex> {
    private ArrayList<Vertex> node_list;
    /**
     * @name 构造函数
     * @Description 创建一个空背包
     */
    public Bag() {
        node_list=new ArrayList<Vertex>();
    }
    /**
     * @name 添加一个元素
     * @Description 添加新节点的ID值
     */
    public void add(Vertex node){
        node_list.add(node);
    }
    /**
     * @name 迭代器
     * @Description
     */
    @Override
    public Iterator<Vertex> iterator() {
        return null;
    }
    public int getSize(){
        return this.node_list.size();
    }
}
