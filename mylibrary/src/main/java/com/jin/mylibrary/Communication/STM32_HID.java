package com.jin.mylibrary.Communication;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class STM32_HID {
    private static final String TAG = "STM32_HID";
    private static final String ACTION_USB_PERMISSION = "com.android.usb.USB_PERMISSION";
    private UsbManager manager; // USB管理器
    private UsbDevice mUsbDevice; // 找到的USB设备
    private UsbInterface mInterface;
    private UsbDeviceConnection mDeviceConnection;
    private final Context context;
    private UsbEndpoint epOut;
    private UsbEndpoint epIn;
    private final String deviceName;
    private boolean isUsbConnect = false;
    private boolean isNeedDebugOut = false;

    public STM32_HID(Context context, String name){
        this.context = context;
        deviceName = name;
        getTheTargetDevice();
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private void getTheTargetDevice(){
        // 获取USB设备
        manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        if (manager == null)
        {
            return;
        }
        else
        {
            if(isNeedDebugOut) Log.i(TAG, "usb设备：" + manager);
        }
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        if(isNeedDebugOut) Log.i(TAG, "usb设备：" + deviceList.size());
        for (UsbDevice device : deviceList.values()) {
            // 在这里添加处理设备的代码
            if(device.getProductName()!= null){
                if (device.getProductName().contains(deviceName)) {
                    mUsbDevice = device;
                    PendingIntent pendingIntent;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                        pendingIntent = PendingIntent.getActivity(context, 0, new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_IMMUTABLE);
                    } else {
                        pendingIntent = PendingIntent.getActivity(context, 0, new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_ONE_SHOT);
                    }
                    manager.requestPermission(mUsbDevice, pendingIntent);
                    if(isNeedDebugOut) Log.i(TAG, "找到设备");
                }
            }
        }
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        context.registerReceiver(mUSBReceiver,filter);
        findIntfAndEpt();
    }

    private class USBReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            // 这里可以拿到插入的USB设备对象
            UsbDevice usbDevice = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
            switch(intent.getAction()) {
                case UsbManager.ACTION_USB_DEVICE_ATTACHED: // 插入USB设备
                    Log.e(TAG, "USB插入");
                    getTheTargetDevice();
                    break;
                case UsbManager.ACTION_USB_DEVICE_DETACHED: // 拔出USB设备
                    Log.e(TAG, "USB拔出");
                    isUsbConnect = false;
                    break;
                case ACTION_USB_PERMISSION:
                    if(isNeedDebugOut) Log.e(TAG, "申请授权");
                    if(intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        //user choose YES for your previously popup window asking for grant perssion
                        // for this usb device
                        findIntfAndEpt();
                    } else {
                        //user choose NO for your previously popup window asking for grant perssion
                        // for this usb device
                        Toast.makeText(context,"请允许使用权限！",Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    USBReceiver mUSBReceiver = new USBReceiver();

    // 寻找接口和分配结点
    private void findIntfAndEpt()
    {
        if (mUsbDevice == null)
        {
            Log.i(TAG, "没有找到设备");
            return;
        }

        int i = 0;
        while (i < mUsbDevice.getInterfaceCount()) {
            // 获取设备接口，一般都是一个接口，你可以打印getInterfaceCount()方法查看接口的个数，在这个接口上有两个端点，OUT 和 IN
            UsbInterface intf = mUsbDevice.getInterface(i);
            Log.d(TAG, i + " " + intf);
            mInterface = intf;
            break;
        }

        if (mInterface != null)
        {
            UsbDeviceConnection connection;
            // 判断是否有权限
            if (manager.hasPermission(mUsbDevice))
            {
                // 打开设备，获取 UsbDeviceConnection 对象，连接设备，用于后面的通讯
                connection = manager.openDevice(mUsbDevice);
                if (connection == null){
                    return;
                }
                if (connection.claimInterface(mInterface, true)){
                    Log.i(TAG, "找到接口");
                    mDeviceConnection = connection;
                    isUsbConnect = true;
                    // 用UsbDeviceConnection 与 UsbInterface 进行端点设置和通讯
                    getEndpoint(mDeviceConnection, mInterface);
                }else{
                    connection.close();
                }
                RecvThread rThread = new RecvThread();
                rThread.start();
            }else{
                Log.i(TAG, "没有权限");
            }
        }else{
            Log.i(TAG, "没有找到接口");
        }
    }



    // 用UsbDeviceConnection 与 UsbInterface 进行端点设置和通讯
    private void getEndpoint(UsbDeviceConnection connection, UsbInterface intf)
    {
        if (intf.getEndpoint(1) != null)
        {
            epOut = intf.getEndpoint(1);
        }

        if (intf.getEndpoint(0) != null)
        {
            epIn = intf.getEndpoint(0);
        }
    }

    //发送数据
    public void SendStrToHID(String str){
        int ret = -100;
        byte[] bt = toByteArray(str);
        // 1,发送准备命令
        ret = mDeviceConnection.bulkTransfer(epOut, bt,
                bt.length, 500);
        if(ret > 0) {
            if(isNeedDebugOut) Log.i(TAG, "已经发送!" + str);
        }
        else {
            if(isNeedDebugOut) Log.i(TAG, "发送失败!");
        }
    }

    //接收监听函数
    public interface OnReceive{
        void onreceive(String string, byte[] bytes, int num);
    }
    private OnReceive onReceive;
    public void OnReceiveListener(OnReceive receive){
        this.onReceive = receive;
    }

    /**
     * 获取USB连接状态.
     *
     * @return 是否连接
     */
    public boolean getConnectState(){
        return isUsbConnect;
    }

    /**
     * 设置Debug 输出.
     *
     * @param value 是否输出
     */
    public void SetDebugOutput(boolean value){
        isNeedDebugOut = value;
    }

    //接收线程
    public class RecvThread extends Thread {
        byte[] Receiveytes = new byte[64];
        int ret = -100;
        @Override
        public void run() {
            super.run();
            while(true) {
                try {
                    ret = mDeviceConnection.bulkTransfer(epIn, Receiveytes,
                            Receiveytes.length, 1000);
                    if(ret > 0) {
                        if(isNeedDebugOut) Log.d(TAG,"Recv = " + ret);
                        onReceive.onreceive(new String(Receiveytes),Receiveytes,ret);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private byte[] toByteArray(String arg) {
        if (arg != null) {
            char[] NewArray = new char[1000];
            char[] array = arg.toCharArray();
            int length = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] != ' ') {
                    NewArray[length] = array[i];
                    length++;
                }
            }
            int EvenLength = (length % 2 == 0) ? length : length + 1;
            if (EvenLength != 0) {
                int[] data = new int[EvenLength];
                data[EvenLength - 1] = 0;
                for (int i = 0; i < length; i++) {
                    if (NewArray[i] >= '0' && NewArray[i] <= '9') {
                        data[i] = NewArray[i] - '0';
                    } else if (NewArray[i] >= 'a' && NewArray[i] <= 'f') {
                        data[i] = NewArray[i] - 'a' + 10;
                    } else if (NewArray[i] >= 'A' && NewArray[i] <= 'F') {
                        data[i] = NewArray[i] - 'A' + 10;
                    }
                }
                byte[] byteArray = new byte[length];
                for (int i = 0; i < length; i++) {
                    byteArray[i] = (byte) NewArray[i];
                }
                return byteArray;
            }
        }
        return new byte[]{};
    }
}
