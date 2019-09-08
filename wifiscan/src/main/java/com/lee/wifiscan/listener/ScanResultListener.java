package com.lee.wifiscan.listener;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;

import java.util.List;

public interface ScanResultListener {
    void resultSuc(List<ScanResult> list, boolean isLastTime);
    void filterFailure();
    void connectedWifiCallback(WifiInfo info);
}
