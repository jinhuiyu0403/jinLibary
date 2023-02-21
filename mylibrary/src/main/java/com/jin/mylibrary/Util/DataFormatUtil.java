package com.jin.mylibrary.Util;

import java.io.UnsupportedEncodingException;

/**
 * 数据类型转换工具.
 */
public class DataFormatUtil {

    /**
     * 将浮点数转换为字节.可用于文件存储
     *
     * @param f the f
     * @return the byte [ ]
     */
    public static byte[] float2byte(float f) {
        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }
        return dest;
    }

    /**
     * 字节转换为浮点.
     *
     * @param b     字符数组
     * @param index 数组开始的序号，一般设为0
     * @return 浮点数据
     */
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    /**
     * 将16进制字符串转换为byte[]
     */
    public static byte[] hexString2ByteArray(String bs) {
        if (bs == null) {
            return null;
        }
        bs = bs.replace(" ", "");
        int bsLength = bs.length();
        if (bsLength % 2 != 0) {
            bs = "0"+bs;
            bsLength = bs.length();
        }
        byte[] cs = new byte[bsLength / 2];
        String st;
        for (int i = 0; i < bsLength; i = i + 2) {
            st = bs.substring(i, i + 2);
            try {
                cs[i / 2] = (byte) Integer.parseInt(st, 16);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return cs;
    }

    //byte数组转String
    public static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        int length = sb.length();
        if (length == 1||length == 0){
            return sb.toString();
        }
        if (length%2==1){
            sb.insert(length-1," ");
            length= length-1;
        }
        for (int i = length;i>0;i=i-2){
            sb.insert(i," ");
        }
        return sb.toString();
    }

    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "gbk");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    //String字符串的互转
    public static String changeHexString(boolean isChangeHex,String string){
        if (string == null||string.isEmpty()){
            return "";
        }
        if (isChangeHex) {
            try {
                return bytesToHexString(string.getBytes("GBK"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "";
        }else {
            return hexStringToString(string);
        }
    }
}
