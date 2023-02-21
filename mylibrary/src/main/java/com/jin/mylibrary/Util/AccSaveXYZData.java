package com.jin.mylibrary.Util;

public class AccSaveXYZData {


   private float[] xBuffer = new float[256];
    private float[] yBuffer = new float[256];
    private float[] zBuffer = new float[256];
    private float[] accX    = new float[256];
    private float[] accY    = new float[256];
    private float[]  accZ    = new float[256];
    private Integer[] pwrBuffer = new Integer[20];
    private float[]  uaBuffer = new float[20];
    private float[]   ucBuffer = new float[20];
    private float[]    iaBuffer = new float[20];
    private float[]    icBuffer = new float[20];
    private float[]   pfBuffer = new float[20];
    private float[]   hzBuffer = new float[20];
    private float[]   spdBuffer = new float[20];
    private int[]  dirBuffer = new int[20];
    private int[]   backBuffer = new int[20];

    private float[] accListX = new float[256];
    private float[] accListY = new float[256];
    private float[] accListZ = new float[256];
    private String time;
    public float[] getxBuffer() {
        return xBuffer;
    }

    public void setxBuffer(float[] xBuffer) {
        this.xBuffer = xBuffer;
    }

    public float[] getyBuffer() {
        return yBuffer;
    }

    public void setyBuffer(float[] yBuffer) {
        this.yBuffer = yBuffer;
    }

    public float[] getzBuffer() {
        return zBuffer;
    }

    public void setzBuffer(float[] zBuffer) {
        this.zBuffer = zBuffer;
    }

    public float[] getAccX() {
        return accX;
    }

    public void setAccX(float[] accX) {
        this.accX = accX;
    }

    public float[] getAccY() {
        return accY;
    }

    public void setAccY(float[] accY) {
        this.accY = accY;
    }

    public float[] getAccZ() {
        return accZ;
    }

    public void setAccZ(float[] accZ) {
        this.accZ = accZ;
    }


    public Integer[] getPwrBuffer() {
        return pwrBuffer;
    }

    public void setPwrBuffer(Integer[] pwrBuffer) {
        this.pwrBuffer = pwrBuffer;
    }

    public float[] getUaBuffer() {
        return uaBuffer;
    }

    public void setUaBuffer(float[] uaBuffer) {
        this.uaBuffer = uaBuffer;
    }

    public float[] getUcBuffer() {
        return ucBuffer;
    }

    public void setUcBuffer(float[] ucBuffer) {
        this.ucBuffer = ucBuffer;
    }

    public float[] getIaBuffer() {
        return iaBuffer;
    }

    public void setIaBuffer(float[] iaBuffer) {
        this.iaBuffer = iaBuffer;
    }

    public float[] getIcBuffer() {
        return icBuffer;
    }

    public void setIcBuffer(float[] icBuffer) {
        this.icBuffer = icBuffer;
    }

    public float[] getPfBuffer() {
        return pfBuffer;
    }

    public void setPfBuffer(float[] pfBuffer) {
        this.pfBuffer = pfBuffer;
    }

    public float[] getHzBuffer() {
        return hzBuffer;
    }

    public void setHzBuffer(float[] hzBuffer) {
        this.hzBuffer = hzBuffer;
    }

    public float[] getSpdBuffer() {
        return spdBuffer;
    }

    public void setSpdBuffer(float[] spdBuffer) {
        this.spdBuffer = spdBuffer;
    }

    public int[] getDirBuffer() {
        return dirBuffer;
    }

    public void setDirBuffer(int[] dirBuffer) {
        this.dirBuffer = dirBuffer;
    }

    public int[] getBackBuffer() {
        return backBuffer;
    }

    public void setBackBuffer(int[] backBuffer) {
        this.backBuffer = backBuffer;
    }

    public float[] getAccListX() {
        return accListX;
    }

    public void setAccListX(float[] accListX) {
        this.accListX = accListX;
    }

    public float[] getAccListY() {
        return accListY;
    }

    public void setAccListY(float[] accListY) {
        this.accListY = accListY;
    }

    public float[] getAccListZ() {
        return accListZ;
    }

    public void setAccListZ(float[] accListZ) {
        this.accListZ = accListZ;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
