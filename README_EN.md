# YueWifiConnect
scan wifi , filter target wifi , connect target wifi automatically

### How to target wifi and connect target wifi automatically？
WifiManager.scan -> get broadcast notify -> wifimanager.updateNetwork or wifimanager.addNetwork

### How to use this library
1. add permissions in Manifest

```
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

2. copy wifiscan module to your project,concerned Activity implements ScanResultListener，then three method will override


```
   //callback ScanResult list
     @Override
    public void resultSuc(final List<ScanResult> list, boolean isLastTime) {
        
    }
    
    //fail to filter target wifi
      @Override
public void filterFailure() {
        
    }
    
    // Connect to target wifi successfully, callback current WifiInfo
     @Override
    public void connectedWifiCallback(final WifiInfo info) {
        
    }
```
3. Activity # onCreate initial YueWifiHelper

```
 helper = new YueWifiHelper(this,this);
```
4. scan wifi

 
```
 helper.startScan();
```

then use these methods
```

      @Override
    public void resultSuc(final List<ScanResult> list, boolean isLastTime) {
         helper.filterAndConnectTargetWifi(list,HOTPOINT_TARGET,isLastTime); // HOTPOINT_TARGET : target wifi name
    }
    
    //获取指定WiFi失败
      @Override
public void filterFailure() {
        //fail to get HOTPOINT_TARGET,use helper.startScan() to continue scanning if you like
    }
    

     @Override
    public void connectedWifiCallback(final WifiInfo info) {
    //make sure have connected to HOTPOINT_TARGET，if everything goes well isConnect = true
         final boolean isConnect = helper.isConnected(info);
    }
    
     @Override
    protected void onStop() {
        super.onStop();
        helper.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.destroy();
    }
```

### 打印Log
if every step works well you will output logs :
1. WIFI_LIST: 1 :  wifimanager.startScan() index = 1
2. WIFI_LIST: 2 :  WifiBroadcastReceiver#onReceive currentIndex = 2 results.size = 63
3. WIFI_LIST: 3 filter, success
4. WIFI_LIST: 4 connect, tempConfig.SSID = "xxxx"
5. WIFI_LIST: 5 connected success,  wifi  = "xxxx"
