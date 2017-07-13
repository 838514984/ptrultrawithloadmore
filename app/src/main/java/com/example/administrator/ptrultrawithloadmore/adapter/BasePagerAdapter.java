package com.example.administrator.ptrultrawithloadmore.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

public  class BasePagerAdapter extends FragmentStatePagerAdapter {
    private Activity mContext;
    ArrayList<Class<? extends  Fragment>> mListClass = new ArrayList<>();
    ArrayList<String> mTitles = new ArrayList<>();
    public HashMap<Integer, Fragment> mFragments =new HashMap<>();
    public BasePagerAdapter(FragmentManager fm, ViewPager viewPager, ArrayList<Class<? extends  Fragment>> list, ArrayList<String> titles) {
        super(fm);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Fragment f= mFragments.get(i);
                if(f !=null){
                    f.setUserVisibleHint(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        Context c =  viewPager.getContext();
        if(c instanceof Activity){

            mContext = (Activity)c;
        }
        this.mListClass = list;
        this.mTitles = titles;
    }
    public Fragment getFragentByPosition(int position){
        return mFragments.get(position);
    }
    
    @Override
    public Fragment getItem(int position) {
        Bundle b=mContext.getIntent().getExtras();
        if(b ==null){
            b = new Bundle();
        }
        b.putInt("position", position);
        Fragment fragment = Fragment.instantiate(mContext, mListClass.get(position).getName(),b);
        mFragments.put(position,fragment);
        return fragment;
    }
    
    @Override
    public int getCount() {
        return mListClass.size();
    }
    
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
    
    public void setPageTitle(String title, int position) {
        mTitles.set(position, title);
    }
}