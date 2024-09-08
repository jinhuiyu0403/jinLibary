package com.jin.mylibrary;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestTools {
    //ef8e06ec32ebbaee86954f9156fc9c1d

    //https://github.com/jinhuiyu0403/jinLibary 上release
    //https://jitpack.io/ 上输入上面的地址
    //https://jitpack.io/#jinhuiyu0403/jinLibary
    public float result;
    public TestTools(float a, float b){
        result = a + b * b;
    }
    //gson与实体类互转
//    private void SaveData(){
//        ArrayList<CreatePara> list = new ArrayList<>();
//        builder = new GsonBuilder();
//        gson = builder.create();
//        list.add(new CreatePara(1,2,3,4));
//        list.add(new CreatePara(2,4,5,6));
//        String jsontext = gson.toJson(list);
//        List<CreatePara> newlist = new ArrayList<>();
//        @SuppressWarnings("rawtypes") Type typ1 = new TypeToken<List<CreatePara>>(){}.getType();
//        newlist = gson.fromJson(jsontext,typ1);
//        Log.d("Test",jsontext);
//    }

    private String testStr1 = "{\"list\":[{\"name\":\"检验日期\",\"value\":\"10.30\"},{\"name\":\"使用单位名称\",\"value\":\"大连\"},{\"name\":\"设备代码\",\"value\":\"210012\"},{\"name\":\"设备类别\",\"value\":\"厂车\"},{\"name\":\"设备品种\",\"value\":\"手刹\"},{\"name\":\"检验机构名称\",\"value\":\"恒亚\"},{\"name\":\"检验人员\",\"value\":\"顾\"},{\"name\":\"最大手操纵力\",\"value\":\"4.4N\"},{\"name\":\"文件链接\",\"value\":\"http:\\/\\/cdn.ln-hy.com\\/dlhy_20221103181121_c92c8c7ddf888bdd2bba424a6a66eb25.doc\"}]}";
    private String testStr2 = "[{\"name\":\"检验日期\",\"value\":\"10.30\"},{\"name\":\"使用单位名称\",\"value\":\"大连\"},{\"name\":\"设备代码\",\"value\":\"210012\"},{\"name\":\"设备类别\",\"value\":\"厂车\"},{\"name\":\"设备品种\",\"value\":\"手刹\"},{\"name\":\"检验机构名称\",\"value\":\"恒亚\"},{\"name\":\"检验人员\",\"value\":\"顾\"},{\"name\":\"最大手操纵力\",\"value\":\"4.4N\"},{\"name\":\"文件链接\",\"value\":\"http:\\/\\/cdn.ln-hy.com\\/dlhy_20221103181121_c92c8c7ddf888bdd2bba424a6a66eb25.doc\"}]";
    private String[] GetRecvJson(String testStr){
        String[] recv;
        try{
            if(testStr.contains("list")){
                JSONObject jsonObject = new JSONObject(testStr);
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                recv = new String[jsonArray.length()];
                for(int i = 0;i < jsonArray.length();i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    recv[i] = jsonObject1.getString("value");
                }
            }else{
                JSONArray jsonArray = new JSONArray(testStr);
                recv = new String[jsonArray.length()];
                for(int i = 0;i < jsonArray.length();i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    recv[i] = jsonObject1.getString("value");
                }
            }
            return recv;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
