package com.explous.explous.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explous.explous.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/30.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Map<String, Object>> datas;
    private Context context;

    public RecyclerAdapter(Context context, List datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup group, final int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_explous, group, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        Map<String, Object> temp = datas.get(i);
        holder.title.setText(temp.get("Title").toString());
        Integer type = Integer.parseInt(temp.get("Type").toString());
        List<Map<String, Object>> data = (ArrayList)temp.get("Datas");
        FolderRecyclerAdapter adapter;
        switch(type) {
            case 0:
                holder.child.setLayoutManager(new GridLayoutManager(context, 2));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                adapter = new FolderRecyclerAdapter(context, data);
                holder.child.setAdapter(adapter);
                break;
            case 1:
                holder.child.setLayoutManager(new LinearLayoutManager(context));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                adapter = new FolderRecyclerAdapter(context, data);
                holder.child.setAdapter(adapter);
                break;
            case 2:
                holder.child.setLayoutManager(new LinearLayoutManager(context));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                adapter = new FolderRecyclerAdapter(context, data);
                holder.child.setAdapter(adapter);
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public RecyclerView child;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            child = (RecyclerView) view.findViewById(R.id.child);
        }
    }
}
