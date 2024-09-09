package com.jin.mylibrary.bluetooth.connect.listener;

import com.jin.mylibrary.bluetooth.receiver.listener.BluetoothClientListener;

/**
 * Created by dingjikerbo on 2016/11/26.
 */

public abstract class BluetoothStateListener extends BluetoothClientListener {

    public abstract void onBluetoothStateChanged(boolean openOrClosed);

    @Override
    public void onSyncInvoke(Object...args) {
        boolean openOrClosed = (boolean) args[0];
        onBluetoothStateChanged(openOrClosed);
    }
}
