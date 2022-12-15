package com.jin.mylibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class BitmapUtil {
    public static void SaveViewToFile(Context context,View view, String fileName){
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            //压缩bitmap到输出流中
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos);
            fos.close();
        } catch (Exception e) {
            Log.e("ddd", e.getMessage());
        }finally {
            if(bitmap!=null) {
                bitmap.recycle();
            }
        }
    }

    public static Bitmap drawVLine(Bitmap bmp,float x1,float x2,int color){
        // 得到图片的长和宽
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        // 创建目标灰度图像
        Bitmap bmpGray = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        //在中心画十字
        int y1 = height / 8 , y2 = height * 7 /8 ;
        Canvas canvas = new Canvas(bmpGray);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        canvas.drawBitmap(bmp,0,0,paint);
        canvas.drawLine(x1,y1,x2,y2,paint);
        return bmpGray;
    }
}
