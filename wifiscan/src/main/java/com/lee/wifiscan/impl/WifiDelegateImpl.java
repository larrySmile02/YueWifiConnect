package com.lee.wifiscan.impl;

import android.Manifest;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lee.wifiscan.delegate.WifiDelegate;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class WifiDelegateImpl implements WifiDelegate {

    private final int DELAYT_TIME = 2000; //每秒扫描一次
    private final int TOTAL_TIMES = 20;//最多扫描20次

    private RxPermissions rxPermissions;
    private ScheduledExecutorService pool;
    private volatile int index; //当前扫描的次数


    @Override
    public void wifiScan(final AppCompatActivity mActivity) {
        index = 0;
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(mActivity);
            pool = Executors.newScheduledThreadPool(1);
        }
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).
                subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            final WifiManager wifimanager = (WifiManager) mActivity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                            pool.scheduleAtFixedRate(new Runnable() {
                                @Override
                                public void run() {
                                    if (index < TOTAL_TIMES) {

                                        wifimanager.startScan();
                                        Log.i("WIFI_LIST", "1 :  wifimanager.startScan()" + " index = " + index);
                                        index++;
                                    }
                                }
                            }, 0, DELAYT_TIME, TimeUnit.MILLISECONDS);

                        } else {
                            Log.i("WIFI_LIST", "1 :  no permission");
                        }

                    }
                });


    }

    @Override
    public List<ScanResult> getWifiScanResult(Context context) {
        return ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getScanResults();
    }

    @Override
    public int getCurrentIndex() {
        return index;
    }

    @Override
    public void stopScan() {
        if (pool != null) {
            pool.shutdown();
        }
    }
}
