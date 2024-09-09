package com.jin.mylibrary.bluetooth.connect.response;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

import com.jin.mylibrary.bluetooth.IResponse;


public abstract class BluetoothResponse implements Handler.Callback, IResponse {

    private static final int MSG_RESPONSE = 1;

    protected abstract void onAsyncResponse(int code, Bundle data);

    private Handler mHandler;

    protected BluetoothResponse() {
        if (Looper.myLooper() == null) {
            throw new RuntimeException();
        }
        mHandler = new Handler(Looper.myLooper(), this);
    }

    @Override
    public void onResponse(int code, Bundle data)  {
        mHandler.obtainMessage(MSG_RESPONSE, code, 0, data).sendToTarget();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_RESPONSE:
                onAsyncResponse(msg.arg1, (Bundle) msg.obj);
                break;
        }
        return true;
    }
}
