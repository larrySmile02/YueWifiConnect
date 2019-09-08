package com.lee.yuewificonnect.delegate;

import android.net.wifi.ScanResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.lee.yuewificonnect.R;

import java.util.List;

public class WifiListDelegate extends AdapterDelegate<List<ScanResult>> {

    @Override
    protected boolean isForViewType(@NonNull List<ScanResult> items, int position) {
        return items.get(position) instanceof ScanResult;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wifi,parent,false);
        WifiViewHolder viewHolder = new WifiViewHolder(view);
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull List<ScanResult> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        ((WifiViewHolder)holder).title.setText(items.get(position).SSID);
    }


    public class WifiViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public WifiViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_wifi_name);
        }
    }
}
