package com.jin.mylibrary.bluetooth.receiver;

import com.jin.mylibrary.bluetooth.receiver.listener.BluetoothReceiverListener;

import java.util.List;

/**
 * Created by dingjikerbo on 16/11/26.
 */

public interface IReceiverDispatcher {

    List<BluetoothReceiverListener> getListeners(Class<?> clazz);
}
