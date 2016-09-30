package com.explous.explous.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.explous.explous.R;

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
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;
        public ViewHolder(View view) {
            super(view);
            icon = (ImageView) view.findViewById(R.id.icon);
            title = (TextView) view.findViewById(R.id.title);

        }
    }
}
