package com.jin.mylibrary.bluetooth;

import android.os.Bundle;
import com.jin.mylibrary.bluetooth.IResponse;

public interface IBluetoothService {
    void callBluetoothApi(int code, Bundle args, IResponse response);
}
