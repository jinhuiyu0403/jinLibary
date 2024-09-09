package com.jin.mylibrary.bluetooth.search.response;

import com.jin.mylibrary.bluetooth.search.SearchResult;

public interface BluetoothSearchResponse {
    void onSearchStarted();

    void onDeviceFounded(SearchResult device);

    void onSearchStopped();

    void onSearchCanceled();
}
