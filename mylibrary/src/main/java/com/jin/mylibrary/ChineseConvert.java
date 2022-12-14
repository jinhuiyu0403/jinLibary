package com.jin.mylibrary;

import java.nio.charset.StandardCharsets;

public class ChineseConvert {
    //中文字符串转换
    public static byte[] ToBytes(String str){
        try{
            return str.getBytes("gb2312");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static char[] ToChars(String str){
        try{
            byte[] tmpchar = str.getBytes("gb2312");
            char[] tmp = new char[tmpchar.length];
            for(int i = 0;i < tmpchar.length;i++){
                tmp[i] = (char)(tmpchar[i] & 0xFF);
            }
            return tmp;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
