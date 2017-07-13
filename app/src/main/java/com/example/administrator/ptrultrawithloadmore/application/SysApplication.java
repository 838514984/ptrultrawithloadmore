package com.example.administrator.ptrultrawithloadmore.application;

import android.app.Application;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class SysApplication extends Application {
    public static Application instance;

    public static Application getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
