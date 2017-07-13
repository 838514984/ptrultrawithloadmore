package com.example.administrator.ptrultrawithloadmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.VH> {
    private List<String> mData;
    private Context context;
    public MyRecyclerviewAdapter(List<String> mData, Context context){
        this.mData=mData;
        this.context=context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,null);
        VH vh=new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setText(R.id.tv," the position is : "+position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public static class VH extends RecyclerView.ViewHolder{
        SparseArray sparseArray=new SparseArray();
        View itemView;
        public VH(View itemView) {
            super(itemView);
            this.itemView=itemView;
        }

        private View get(int id){
            if (sparseArray.get(id)==null){
                sparseArray.put(id,itemView.findViewById(id));
            }
            return (View) sparseArray.get(id);
        }

        public void setText(int id,String text){
            TextView tv= (TextView) get(id);
            tv.setText(text);
        }

    }


}
