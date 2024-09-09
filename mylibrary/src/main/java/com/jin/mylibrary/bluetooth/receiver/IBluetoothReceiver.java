package com.jin.mylibrary.bluetooth.receiver;

import com.jin.mylibrary.bluetooth.receiver.listener.BluetoothReceiverListener;

/**
 * Created by dingjikerbo on 2016/11/25.
 */

public interface IBluetoothReceiver {

    void register(BluetoothReceiverListener listener);
}
