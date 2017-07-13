package com.example.administrator.ptrultrawithloadmore;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class PTRHeader extends FrameLayout implements PtrUIHandler {
    private ImageView mRotateImg;
    private AnimationDrawable animationDrawable;
    private Context context;

    public PTRHeader(@NonNull Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        mRotateImg = new ImageView(context);
        mRotateImg.setImageDrawable(getResources().getDrawable(R.drawable.animlist));
        animationDrawable = (AnimationDrawable) mRotateImg.getDrawable();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRotateImg.setLayoutParams(layoutParams);
        this.addView(mRotateImg);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        Log.e("xxx", "onUIReset");
        animationDrawable.stop();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        Log.e("xxx", "onUIRefreshPrepare");

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        Log.e("xxx", "onUIRefreshBegin");
        mRotateImg.setImageDrawable(animationDrawable);
        animationDrawable.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        Log.e("xxx", "onUIRefreshComplete");
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        Log.e("xxx", "onUIPositionChange");
        float currentPercent = ptrIndicator.getCurrentPercent();
        if (currentPercent>=1.0)
            return;
        int currentPic = (int) (currentPercent * 15)+1;

        int resid = context.getResources().getIdentifier("refresh_loading_" + currentPic, "drawable", context.getPackageName());
        Log.e("xxx", "currentPercent: " + currentPercent + " currentPic: " + currentPic+" resid: "+resid);
        mRotateImg.setImageResource(resid);

    }
}
