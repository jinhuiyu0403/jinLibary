package com.jin.mylibrary.bluetooth.search;

import android.os.Bundle;

import com.jin.mylibrary.bluetooth.connect.response.BleGeneralResponse;
import com.jin.mylibrary.bluetooth.search.response.BluetoothSearchResponse;

import static com.jin.mylibrary.bluetooth.Constants.DEVICE_FOUND;
import static com.jin.mylibrary.bluetooth.Constants.EXTRA_SEARCH_RESULT;
import static com.jin.mylibrary.bluetooth.Constants.SEARCH_CANCEL;
import static com.jin.mylibrary.bluetooth.Constants.SEARCH_START;
import static com.jin.mylibrary.bluetooth.Constants.SEARCH_STOP;

/**
 * Created by dingjikerbo on 2016/8/28.
 */
public class BluetoothSearchManager {

    public static void search(SearchRequest request, final BleGeneralResponse response) {
        BluetoothSearchRequest requestWrapper = new BluetoothSearchRequest(request);
        BluetoothSearchHelper.getInstance().startSearch(requestWrapper, new BluetoothSearchResponse() {
            @Override
            public void onSearchStarted() {
                response.onResponse(SEARCH_START, null);
            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_SEARCH_RESULT, device);
                response.onResponse(DEVICE_FOUND, bundle);
            }

            @Override
            public void onSearchStopped() {
                response.onResponse(SEARCH_STOP, null);
            }

            @Override
            public void onSearchCanceled() {
                response.onResponse(SEARCH_CANCEL, null);
            }
        });
    }

    public static void stopSearch() {
        BluetoothSearchHelper.getInstance().stopSearch();
    }
}
