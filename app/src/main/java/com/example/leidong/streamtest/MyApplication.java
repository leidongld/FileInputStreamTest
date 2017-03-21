package com.example.leidong.streamtest;

import android.app.Application;
import android.content.Context;

/**
 * Created by leido on 2017/3/21.
 */

public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate(){
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }

}
