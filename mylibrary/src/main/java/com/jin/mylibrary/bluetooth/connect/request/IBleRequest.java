package com.jin.mylibrary.bluetooth.connect.request;

import com.jin.mylibrary.bluetooth.connect.IBleConnectDispatcher;

/**
 * Created by dingjikerbo on 16/8/25.
 */
public interface IBleRequest {

    void process(IBleConnectDispatcher dispatcher);

    void cancel();
}
