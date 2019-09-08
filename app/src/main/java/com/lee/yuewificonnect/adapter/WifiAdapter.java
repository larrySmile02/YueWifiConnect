package com.lee.yuewificonnect.adapter;

import android.net.wifi.ScanResult;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.lee.yuewificonnect.delegate.WifiListDelegate;

import java.util.List;

public class WifiAdapter extends ListDelegationAdapter<List<ScanResult>> {

    public WifiAdapter(){
        delegatesManager.addDelegate(new WifiListDelegate());
    }
}
