package com.jin.mylibrary;

import android.util.Log;

import java.util.Locale;

/**
 * The type Turn vaild num.转换指定长度的有效数字
 */
public class TurnVaildNum {
    public static String Str(float data,int length){
        float res = 0.0f;
        String str = "0.0";
        if(length == 3){
            str = String.format(Locale.CHINA,"%1.2e",data);
        }else if(length == 4){
            str = String.format(Locale.CHINA,"%1.3e",data);
        }else if(length == 5){
            str = String.format(Locale.CHINA,"%1.4e",data);
        }else{
            str = String.format(Locale.CHINA,"%1.1e",data);
        }
        try{
            float valid = Float.parseFloat(str.substring(0,str.indexOf("e")));
            double dec   = Float.parseFloat(str.substring(str.indexOf("e")+1));
            res = valid * (float)Math.pow(10,dec);
            String obj = String.format(Locale.CHINA,"%f",res);
            int pos = 0;
            for(int i = obj.length() - 1;i > 0;i--){
                if(obj.charAt(i) != '0') {
                    pos = i + 1;
                    break;
                }
            }
            return obj.substring(0,pos);
        }catch (Exception e){
            Log.e("Error",e.toString());
            return "0.0";
        }
    }
}
