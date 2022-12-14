package com.jin.mylibrary.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jin.mylibrary.R;
import com.jin.mylibrary.pickerview.adapter.ArrayWheelAdapter;
import com.jin.mylibrary.pickerview.lib.WheelView;

import java.util.ArrayList;

public class WheelDialog extends Dialog {
    private final Context mContext;
    private Button Comfirm;
    private TextView title;
    public  int curSelect = 0;
    public WheelDialog(@NonNull Context context, String curstr, ArrayList<String> datalist) {
        super(context, R.style.showDialog);
        this.mContext = context;
        this.getWindow().setWindowAnimations(R.style.AnimCenter);
        WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
        layoutParams.width = 200;
        layoutParams.height = 300;
        this.getWindow().setAttributes(layoutParams);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView(curstr,datalist);
    }

    //初始化
    public void initView(String curstr,ArrayList<String> datalist) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_avrlevel, null);
        WheelView wheelView = view.findViewById(R.id.wheel_view);
        Comfirm   =  view.findViewById(R.id.btn_confirm);
        title     =  view.findViewById(R.id.title);
        ImageView btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> this.dismiss());
        int defaultpos = 0;
        for(int i = 0;i < datalist.size();i++){
            if(curstr.equals(datalist.get(i))){
                defaultpos = i;
            }
        }
        curSelect = defaultpos;
        wheelView.setAdapter(new ArrayWheelAdapter<>(datalist));
        wheelView.setCyclic(false); //取消循环显示数据
        wheelView.setCurrentItem(defaultpos); //当前显示第一条
        wheelView.setIsOptions(true);
        wheelView.setGravity(Gravity.CENTER);
        wheelView.setOnItemSelectedListener(index -> curSelect = index);
        super.setContentView(view);
    }

    public void SetTitle(String title){
        this.title.setText(title);
    }

    public TextView GetTitleText(){
        return title;
    }
    public Button GetConfirmBtn(){
        return Comfirm;
    }

    //确定键监听器
    public void setOnSureListener(View.OnClickListener listener) {
        Comfirm.setOnClickListener(listener);
    }
}

