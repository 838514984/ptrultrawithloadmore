package com.example.administrator.ptrultrawithloadmore;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

public class Main2Activity extends AppCompatActivity {
    PtrClassicFrameLayout ptrClassicFrameLayout;
    ListView listView;
    MyListAdapter adapter;
    private List<String> mdatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.ptr);
        listView = (ListView) findViewById(R.id.lv);
        initData(10);
        initListVIew();
        initPtr();
    }

    private void initPtr() {

        ptrClassicFrameLayout.setPtrHandler(new PtrHandler2() {
            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return PtrDefaultHandler2.checkContentCanBePulledUp(frame,content,footer)&&mdatas.size()>10;
            }

            @Override
            public void onLoadMoreBegin(final PtrFrameLayout frame) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mdatas.add("this is the new data");
                        adapter.notifyDataSetChanged();
                        frame.refreshComplete();
                    }
                },1000);

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,content,header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mdatas.add("this is the new data");
                        adapter.notifyDataSetChanged();
                        frame.refreshComplete();
                    }
                },1000);
            }
        });
    }

    private void initData(int itemnums) {
        for (int i = 0; i < itemnums; i++) {
            mdatas.add("this is the " + i + " position");
        }
    }

    private void initListVIew() {
        adapter = new MyListAdapter(mdatas, R.layout.item);
        listView.setAdapter(adapter);
    }
}
