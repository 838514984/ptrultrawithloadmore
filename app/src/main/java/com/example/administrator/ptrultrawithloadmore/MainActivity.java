package com.example.administrator.ptrultrawithloadmore;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.ptrultrawithloadmore.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

public class MainActivity extends AppCompatActivity {
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecyclerView recyclerView;
   // private MyRecyclerviewAdapter myRecyclerviewAdapter;
    private BaseRecyclerViewAdapter<String> myRecyclerviewAdapter;
    private List<String> mdatas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData(11);
        initPTR();
        initRecyclerView();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }
    private void initData(int itemnums){
        for (int i=0;i<itemnums;i++){
            mdatas.add("this is the "+i+" position");
        }
    }
    private void initRecyclerView(){
        recyclerView= (RecyclerView) findViewById(R.id.rl_mainactivity);
        //myRecyclerviewAdapter =new MyRecyclerviewAdapter(mdatas,this);
        myRecyclerviewAdapter= new BaseRecyclerViewAdapter<String>(mdatas,R.layout.item) {
            @Override
            public ArrayList<Integer> bindDataToView(VH viewHolder, int position, String model) {
                viewHolder.setText(R.id.tv,model);
                return null;
            }
        };
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.ptr_rotate_arrow);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);

        ImageView imageView2=new ImageView(this);
        imageView2.setImageResource(R.drawable.ptr_rotate_arrow);
        ViewGroup.LayoutParams layoutParams2=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView2.setLayoutParams(layoutParams2);
        myRecyclerviewAdapter.addHeader(imageView);
        myRecyclerviewAdapter.addHeader(imageView2);
        myRecyclerviewAdapter.addFooter(imageView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myRecyclerviewAdapter);

    }
    private void initPTR(){
        ptrClassicFrameLayout= (PtrClassicFrameLayout) findViewById(R.id.ptr);
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
        PTRHeader ptrHeader=new PTRHeader(this);
        ptrClassicFrameLayout.setHeaderView(ptrHeader);
        ptrClassicFrameLayout.addPtrUIHandler(ptrHeader);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler2() {
            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return PtrDefaultHandler2.checkContentCanBePulledUp(frame,content,footer);
            }

            @Override
            public void onLoadMoreBegin(final PtrFrameLayout frame) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
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
                        frame.refreshComplete();
                    }
                },1000);
            }
        });
    }
}


