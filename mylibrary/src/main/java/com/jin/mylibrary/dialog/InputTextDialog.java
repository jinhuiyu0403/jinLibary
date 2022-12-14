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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jin.mylibrary.R;
import com.jin.mylibrary.pickerview.adapter.ArrayWheelAdapter;
import com.jin.mylibrary.pickerview.lib.WheelView;

import java.util.ArrayList;

public class InputTextDialog extends Dialog {
    private final Context mContext;
    private Button Comfirm;
    private TextView title;
    private EditText edittext;
    public  int curSelect = 0;
    public InputTextDialog(@NonNull Context context, String curstr) {
        super(context, R.style.showDialog);
        this.mContext = context;
        this.getWindow().setWindowAnimations(R.style.AnimCenter);
        WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
        layoutParams.width = 200;
        layoutParams.height = 300;
        this.getWindow().setAttributes(layoutParams);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView(curstr);
    }

    public InputTextDialog(@NonNull Context context, String curstr,int type) {
        super(context, R.style.showDialog);
        this.mContext = context;
        this.getWindow().setWindowAnimations(R.style.AnimCenter);
        WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
        layoutParams.width = 200;
        layoutParams.height = 300;
        this.getWindow().setAttributes(layoutParams);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView(curstr,type);
    }

    //初始化
    public void initView(String curstr) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_inputtext, null);
        edittext  = view.findViewById(R.id.edit_context);
        Comfirm   =  view.findViewById(R.id.btn_confirm);
        title     =  view.findViewById(R.id.title);
        ImageView btnCancel = view.findViewById(R.id.btn_cancel);
        edittext.setText(curstr);
        btnCancel.setOnClickListener(v -> this.dismiss());
        super.setContentView(view);
    }

    public void initView(String curstr,int type) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_inputtext, null);
        edittext  = view.findViewById(R.id.edit_context);
        Comfirm   =  view.findViewById(R.id.btn_confirm);
        title     =  view.findViewById(R.id.title);
        ImageView btnCancel = view.findViewById(R.id.btn_cancel);
        edittext.setText(curstr);
        edittext.setInputType(type);
        btnCancel.setOnClickListener(v -> this.dismiss());
        super.setContentView(view);
    }

    public void SetTitle(String title){
        this.title.setText(title);
    }

    public TextView GetTitleText(){
        return title;
    }
    public EditText GetEditText(){
        return edittext;
    }
    public Button GetConfirmBtn(){
        return Comfirm;
    }

    //确定键监听器
    public void setOnSureListener(View.OnClickListener listener) {
        Comfirm.setOnClickListener(listener);
    }
}

