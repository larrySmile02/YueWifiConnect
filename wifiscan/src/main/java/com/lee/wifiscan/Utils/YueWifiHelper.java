package com.lee.wifiscan.Utils;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lee.wifiscan.broadcast.WifiBroadcastReceiver;
import com.lee.wifiscan.delegate.WifiDelegate;
import com.lee.wifiscan.impl.WifiDelegateImpl;
import com.lee.wifiscan.listener.ScanResultListener;

import java.lang.ref.WeakReference;
import java.util.List;

public class YueWifiHelper {

    private WeakReference<AppCompatActivity> mContext;
    private WifiDelegate delegate;
    private WifiBroadcastReceiver receiver;
    private IntentFilter filter;
    private ScanResultListener listener;

    public YueWifiHelper(AppCompatActivity context, ScanResultListener listener){
        init(context,listener);
    }

    /**
     * 1. 初始化Delegate
     * 2.注册广播
     */
    public void init(AppCompatActivity context, ScanResultListener listener) {
        mContext = new WeakReference<>(context);
        delegate = new WifiDelegateImpl();
        receiver = new WifiBroadcastReceiver(delegate, listener);
        filter = WifiUtil.initFilter();
        if (mContext != null && mContext.get() != null) {
            mContext.get().registerReceiver(receiver, filter);
        }
        this.listener = listener;
    }

    /**
     * 3.开始扫描WiFi
     */
    public void startScan() {
        if (delegate != null) {
            delegate.wifiScan(mContext.get());
        }
    }


    /**
     * 4. 处理扫描结果，如果成功 获取目标WiFi,停止扫描；否则继续扫描直至满20次
     * 5. 目标WiFi获取成功，停止扫描，开始连接
     */
    public void filterAndConnectTargetWifi(List<ScanResult> list, String targetWifiName,boolean isLastTime) {
        WifiUtil.filterAndConnectTargetWifi(mContext.get(), delegate, list, targetWifiName,isLastTime,listener);
    }

    /**
     * 6. 确定目标WiFi连接成功
     */
    public boolean isConnected(WifiInfo info) {
        return WifiUtil.ensureConnectSuc(mContext.get(), info);

    }
    /**
     * 7. 停止扫描
     */
    public void stop() {
        delegate.stopScan();
    }
    /**
     * 8. 注销广播
     */
    public void destroy() {
        mContext.get().unregisterReceiver(receiver);
    }


}
