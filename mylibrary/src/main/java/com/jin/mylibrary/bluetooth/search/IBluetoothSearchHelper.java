package com.jin.mylibrary.bluetooth.search;

import com.jin.mylibrary.bluetooth.search.response.BluetoothSearchResponse;

/**
 * Created by dingjikerbo on 2016/8/28.
 */
public interface IBluetoothSearchHelper {

    void startSearch(BluetoothSearchRequest request, BluetoothSearchResponse response);

    void stopSearch();
}