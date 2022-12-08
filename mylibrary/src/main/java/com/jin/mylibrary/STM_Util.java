package com.jin.mylibrary;

import android.util.Log;

import java.util.ArrayList;

public class STM_Util {
    public static void memset(float[] data,float value,int length){
        for(int i = 0;i < length;i++){
            data[i] = value;
        }
    }

    public static void bubble_sort(float[] unsorted,int n)
    {
        float temp;
        int i = 0,j;
        for (i = 0; i < n; i++)
        {
            for (j = i; j < n; j++)
            {
                if (unsorted[i] > unsorted[j])
                {
                    temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }
    }

    public static float arm_mean_f32(float[] src,int length){
        float result = 0;
        for(int i = 0;i < length;i++){
            result += src[i] / length;
        }
        return result;
    }

    public static float arm_mean_f32(float[] src,int offset,int length){
        float result = 0;
        for(int i = offset;i < offset+length;i++){
            result += src[i] / length;
        }
        return result;
    }

    public static void IIR_Deal(float[] r_acc,float[] d_acc){
        float b1 = 0.9827947083f;float b2 = -1.965589417f;float b3 = 0.9827947083f;
        float a2 = -1.965293373f;float a3 = 0.9658854606f;
        try{

            //一次高通滤波
            d_acc[0] = b1 * r_acc[0];
            d_acc[1] = b1 * r_acc[1] + b2 * r_acc[0] - a2 * d_acc[0];
            for(int i =2;i < r_acc.length;i++){
                d_acc[i] = b1 * r_acc[i] + b2 * r_acc[i - 1] + b3 * r_acc[i -2] - a2 * d_acc[i - 1] - a3 * d_acc[i - 2];
            }
            for(int i = 0;i < r_acc.length;i++) {
                r_acc[i] = d_acc[i];
            }
            b1 = 0.0127873426f;
            b2 = 0.0255746851f;
            b3 = 0.0127873426f;
            a2 = -1.6556076929f;
            a3 = 0.7067570632f;
            //一次低通滤波
            d_acc[0] = b1 * r_acc[0];
            d_acc[1] = b1 * r_acc[1] + b2 * r_acc[0] - a2 * d_acc[0];
            for(int i =2;i < r_acc.length;i++){
                d_acc[i] = b1 * r_acc[i] + b2 * r_acc[i - 1] + b3 * r_acc[i -2] - a2 * d_acc[i - 1] - a3 * d_acc[i - 2];
            }
        }catch (Exception erre){
            Log.e("HP_Analaysis","Error! " + erre.toString());        //记录异常
        }
    }

    public static void IIR_Deal_A(float[] r_acc,float[] d_acc){
        float b1 = 0.01335920003f;float b2 = 0.02671840006f;float b3 = 0.01335920003f;
        float a2 = -1.647459981f;float a3 = 0.7008967812f;
        try{

            //一次高通滤波
            d_acc[0] = b1 * r_acc[0];
            d_acc[1] = b1 * r_acc[1] + b2 * r_acc[0] - a2 * d_acc[0];
            for(int i =2;i < r_acc.length;i++){
                d_acc[i] = b1 * r_acc[i] + b2 * r_acc[i - 1] + b3 * r_acc[i -2] - a2 * d_acc[i - 1] - a3 * d_acc[i - 2];
            }
        }catch (Exception erre){
            Log.e("HP_Analaysis","Error! " + erre.toString());        //记录异常
        }
    }

    public static float arm_abs_f32(float src){
        return Math.abs(src);
    }

    //线性拟合公式，返回值为斜率
    public static float Linearfit_Vib(float[]  y,int n){
        float sumx = 0.0f,sumy = 0.0f,dsumx = 0.0f,dsumy = 0.0f,sumxy = 0.0f,r = 0.0f;
        for(int i = 0;i < n;i++)
        {
            sumx =i + sumx;
            sumy = y[i] + sumy;
            dsumx = i*i + dsumx;
            dsumy = y[i] * y[i] + dsumy;
            sumxy = i * y[i] + sumxy;
        }
        r = (sumxy - sumx * sumy / n)/(dsumx-sumx*sumx/n);
        return r;
    }

    //根据缓存指针读取最近数姐
    public static void get_Data(float[] src,float[] tar,int Count,int Length){
        if(Count == 0){
            for(int i = 0;i < Length;i++)
            {
                tar[i] = src[i];
            }
        }else{
            for(int i = 0;i < Length - Count;i++)
            {
                tar[i] = src[Count + i];
            }
            for(int i = 0;i < Count;i++)
            {
                tar[Length-Count + i] = src[i];
            }
        }
    }

    //Butter 非零相位偏移,4HZ,Low Pass,长度128
    public static void ButterFourLP(float[] Input,float[] Output)
    {
        ArrayList<Float> src = new ArrayList<>();
        for (float v : Input) {
            src.add(v);
        }
        //LP_Analysis lpAnalysis = new LP_Analysis(src,4);
        for(int i = 0;i < Input.length;i++){
            Output[i] = Input[i];//lpAnalysis.getResult().get(i);
        }
    }

    //线性拟合公式,返回值 为斜率,坐标为0.1间隔
    public static float Linearfit(float[] y,int n,float a)
    {
        float[] x = {0.0f,0.1f,0.2f,0.3f,0.4f};
        float sumx = 0.0f,sumy = 0.0f,dsumx = 0.0f,dsumy = 0.0f,sumxy = 0.0f,r = 0.0f;
        int i = 0;
        for(i = 0;i < n;i++)
        {
            sumx =x[i] + sumx;
            sumy = y[i] + sumy;
            dsumx = x[i]* x[i] + dsumx;
            dsumy = y[i] * y[i] + dsumy;
            sumxy = x[i] * y[i] + sumxy;
        }
	    a = (n * sumxy - sumx * sumy)/(n*dsumx - sumx * sumx);
        r = (sumxy - sumx * sumy / n)/(float)(Math.sqrt((dsumx-sumx*sumx/n)*(dsumy - sumy*sumy/n)));
        r = (r >=0)?r:(-r);
        return r;
    }

}
