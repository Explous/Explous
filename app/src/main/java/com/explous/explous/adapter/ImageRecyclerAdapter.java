package com.explous.explous.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explous.explous.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/8.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {
    private List<Map<String, Object>> datas;
    private Context context;

    public ImageRecyclerAdapter(Context context, List datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ImageRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup group, final int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_explous_image, group, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, Object> temp = datas.get(position);
        holder.title.setText(temp.get("Title").toString());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}
