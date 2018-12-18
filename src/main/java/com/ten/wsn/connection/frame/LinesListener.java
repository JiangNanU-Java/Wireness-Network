package com.ten.wsn.connection.frame;

import com.ten.wsn.connection.calculate.NRMValue;

import java.util.List;

/**
 * push {@literal List<List<NRMValue>>} data to listener
 */
public interface LinesListener {

    void pushLinesToListener(List<List<NRMValue>> lines);

}
