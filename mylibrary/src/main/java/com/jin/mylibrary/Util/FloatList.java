package com.jin.mylibrary.Util;

import java.util.ArrayList;

public class FloatList extends ArrayList<Float> {
    private Integer MaxPos = null,MinPos = null;      //最大值和最小值位置
    public FloatList() {
        super();
    }

    /**
     * 获取数组中最大的数值
     *
     * @return 最大值
     */
    public float getMax(){
        int size = this.size();
        if(size() > 0){
            float max = this.get(0);
            MaxPos = 0;
            for(int i = 0;i < size;i++){
                if(max < this.get(i)){
                    max = this.get(i);
                    MaxPos = i;
                }
            }
            return max;
        }
        return 0;
    }

    /**
     * 获取数组中最大值的位置
     *
     * @return 最大值位置
     */
    public int getMaxPos(){
        if(MaxPos == null){
            getMax();
        }
        return MaxPos;
    }

    /**
     * 获取数组中最小的数值
     *
     * @return 最小值
     */
    public float getMin(){
        int size = this.size();
        if(size() > 0){
            float min = this.get(0);
            MinPos = 0;
            for(int i = 0;i < size;i++){
                if(min > this.get(i)){
                    min = this.get(i);
                    MinPos = i;
                }
            }
            return min;
        }
        return 0;
    }

    /**
     * 获取数组中最小值的位置
     *
     * @return 最小值位置
     */
    public int getMinPos(){
        if(MinPos == null){
            getMax();
        }
        if(MinPos == null){
            return 0;
        }
        return MinPos;
    }
}
