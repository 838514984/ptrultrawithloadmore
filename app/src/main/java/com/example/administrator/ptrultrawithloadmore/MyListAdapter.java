package com.example.administrator.ptrultrawithloadmore;

import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.ptrultrawithloadmore.adapter.AppBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class MyListAdapter extends AppBaseAdapter<String>{
    private List<String> mdatas;
    public MyListAdapter(List<String> list, int layoutRecource) {
        super(list, layoutRecource);
        this.mdatas=list;
    }

    @Override
    public ArrayList<Integer> bindViewData(int position, View convertView, ViewGroup parent, int viewType) {
        TextView tv= (TextView) convertView.findViewById(R.id.tv);
        tv.setText(mdatas.get(position));
        return null;
    }

}
