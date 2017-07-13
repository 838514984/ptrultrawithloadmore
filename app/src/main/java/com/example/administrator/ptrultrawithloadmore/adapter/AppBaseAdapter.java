package com.example.administrator.ptrultrawithloadmore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.administrator.ptrultrawithloadmore.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AppBaseAdapter<T> extends BaseAdapter {
    protected List<T> mData;
    

    int[] mLayoutResource ;
    
    public AppBaseAdapter(List<T> list, int layoutRecource) {
        this.mData = list;
        this.mLayoutResource = new int[1];
        mLayoutResource[0] =layoutRecource;
    }
    public AppBaseAdapter(List<T> list, int ... layoutRecources) {
        this.mData = list;
        this.mLayoutResource = layoutRecources;
    }

    @Override
    public T getItem(int position) {
        // TODO Auto-generated method stub
        return mData.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int viewType =getItemViewType(position);
        int itemResource = mLayoutResource[viewType];
        if (convertView == null && itemResource > 0) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(itemResource, parent, false);
        }
        ArrayList<Integer> ids= bindViewData(position, convertView, parent, viewType);
        if(CollectionUtils.isValid(ids)){
            for (Integer id:ids){
                ViewHolder.get(convertView,id).setOnClickListener(new Click(position,mData.get(position)));
            }
        }
        if(mOnItemClick != null ){
            convertView.setOnClickListener(new Click(position,mData.get(position)));
        }
        return convertView;
    }
    
    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @param viewType
     * @return
     */
    public abstract ArrayList<Integer> bindViewData(int position, View convertView, ViewGroup parent, int viewType);
    
    /**
     * 只填充数据
     */

    public List<T> getData() {
        return mData;
    }
    
    public void setData(List<T> list) {
        this.mData = list;
        notifyDataSetChanged();
    }
    public void addData(List<T> list){
        this.mData.addAll(list);
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        //从0开始小于getViewTypeCount；
        if(mLayoutResource.length==1){
            return 0;
        }

        //如果传递的layoutresource的长度大于1，必须重写getItemViewType方法
        return super.getItemViewType(position);
    }
    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return mLayoutResource.length;
    }


    private OnItemClick<T> mOnItemClick;

    public void setOnItemClickListener(OnItemClick<T> l) {
        this.mOnItemClick = l;
    }

    private class Click extends NoDoubleClickListener {
        int mPosition;

        T mCurModel;

        public Click(int position, T t) {
            this.mCurModel = t;
            this.mPosition = position;
        }

        @Override
        public void onClickInternal(View v) {
            if (mOnItemClick != null) {
                mOnItemClick.onItemClick(v, mPosition, mCurModel);
            }
        }
    }
    public interface OnItemClick<T> {
        void onItemClick(View v, int position, T item);
    }
    
}
