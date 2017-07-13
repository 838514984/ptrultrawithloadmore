package com.example.administrator.ptrultrawithloadmore.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.administrator.ptrultrawithloadmore.application.SysApplication;


public class CompatUtil {
    @SuppressWarnings("deprecation")
    public static void setBackground(View v, Drawable drawable) {
        if (hasJellyBean()) {
            v.setBackground(drawable);
        }
        else {
            v.setBackgroundDrawable(drawable);
        }
    }
    
    public static void removeGlobalListener(ViewTreeObserver observer, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (!hasJellyBean()) {
            observer.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
        else {
            observer.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static Drawable getDrawable(int id){
        if(hasLollipop()){
            return SysApplication.getInstance().getResources().getDrawable(id, SysApplication.getInstance().getTheme());

        }else{
            return  SysApplication.getInstance().getResources().getDrawable(id);
        }


    }

    public static void enableStrictMode() {
        if (hasGingerbread()) {
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog();
            StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder().detectAll().penaltyLog();

            if (hasHoneycomb()) {
                threadPolicyBuilder.penaltyFlashScreen();
//              vmPolicyBuilder.setClassInstanceLimit(ImageGridActivity.class, 1).setClassInstanceLimit(ImageDetailActivity.class, 1);
            }
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }

    public static boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;
    }
    public static boolean hasLollipop(){
        return Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT;
    }
}
