
[English Readme](https://github.com/larrySmile02/YueWifiConnect/blob/master/README_EN.md)

# YueWifiConnect
scan wifi , filter target wifi , connect target wifi automatically

### 如何获取指定的WiFi并且自动连接？
我搜索了Github好像还没有现场的解决方案，所以写了个Demo帮助有需要的开发者避免中间的小坑

### 相关博客
 https://blog.csdn.net/qq_40983782/article/details/100640309
### 如何使用
1. 先确定以及在Manifest文件中已经添加权限

```
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

2. 引入wifiscan module后,相关Activity implements ScanResultListener，然后会实现三个方法


```
   //返回扫描列表
     @Override
    public void resultSuc(final List<ScanResult> list, boolean isLastTime) {
        
    }
    
    //获取指定WiFi失败
      @Override
public void filterFailure() {
        
    }
    
    //理论上连接指定Wifi成功，实际上需要check 一下
     @Override
    public void connectedWifiCallback(final WifiInfo info) {
        
    }
```
3. onCreate 中初始化YueWifiHelper

```
 helper = new YueWifiHelper(this,this);
```
4. 调用WiFi扫描方法

 
```
 helper.startScan();
```

在Activity生命周期和第2步返回值中调用相关方法
```
    //返回扫描列表
      @Override
    public void resultSuc(final List<ScanResult> list, boolean isLastTime) {
         helper.filterAndConnectTargetWifi(list,HOTPOINT_TARGET,isLastTime); // HOTPOINT_TARGET是指定的WiFi名
    }
    
    //获取指定WiFi失败
      @Override
public void filterFailure() {
        //没有搜索到HOTPOINT_TARGET,可以调用 helper.startScan()继续扫描
    }
    
    //理论上连接指定Wifi成功，实际上需要check 一下
     @Override
    public void connectedWifiCallback(final WifiInfo info) {
    //check 是否成功连接到HOTPOINT_TARGET，如果是isConnect值为true
         final boolean isConnect = helper.isConnected(info);
    }
    
     @Override
    protected void onStop() {
        super.onStop();
        helper.stop(); //停止扫描
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.destroy(); //销毁相关资源
    }
```

### 打印Log
如果成功连接上HOTPOINT_TARGET会有以下log : 
1. WIFI_LIST: 1 :  wifimanager.startScan() index = 1
2. WIFI_LIST: 2 :  WifiBroadcastReceiver#onReceive currentIndex = 2 results.size = 63
3. WIFI_LIST: 3 filter, success
4. WIFI_LIST: 4 connect, tempConfig.SSID = "xxxx"
5. WIFI_LIST: 5 connected success,  wifi  = "xxxx"
