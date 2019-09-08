package com.lee.wifiscan.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleWifiBean implements Parcelable {

    private String wifiName;
    private String level;
//    private String state;  //已连接  正在连接  未连接 三种状态
    private String capabilities;//加密方式

    public SimpleWifiBean(){

    }

    protected SimpleWifiBean(Parcel in) {
        wifiName = in.readString();
        level = in.readString();
//        state = in.readString();
        capabilities = in.readString();
    }

    public static final Creator<SimpleWifiBean> CREATOR = new Creator<SimpleWifiBean>() {
        @Override
        public SimpleWifiBean createFromParcel(Parcel in) {
            return new SimpleWifiBean(in);
        }

        @Override
        public SimpleWifiBean[] newArray(int size) {
            return new SimpleWifiBean[size];
        }
    };

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wifiName);
        dest.writeString(level);
//        dest.writeString(state);
        dest.writeString(capabilities);
    }
}
