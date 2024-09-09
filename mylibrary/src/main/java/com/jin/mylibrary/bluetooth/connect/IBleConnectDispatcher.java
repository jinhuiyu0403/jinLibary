package com.jin.mylibrary.bluetooth.connect;

import com.jin.mylibrary.bluetooth.connect.request.BleRequest;

public interface IBleConnectDispatcher {

    void onRequestCompleted(BleRequest request);
}
