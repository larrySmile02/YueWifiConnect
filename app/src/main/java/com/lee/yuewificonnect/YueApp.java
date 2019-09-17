package com.lee.yuewificonnect;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class YueApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
           @Override
           public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
               Log.e("YUE","onCreate = "+activity.toString());
           }

           @Override
           public void onActivityStarted(Activity activity) {

           }

           @Override
           public void onActivityResumed(Activity activity) {

           }

           @Override
           public void onActivityPaused(Activity activity) {

           }

           @Override
           public void onActivityStopped(Activity activity) {

           }

           @Override
           public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

           }

           @Override
           public void onActivityDestroyed(Activity activity) {

           }
       });
    }
}
