package com.explous.explous.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.explous.explous.R;
import com.explous.explous.Value;
import com.explous.explous.entity.MediaItemEntity;

/**
 * Created by Administrator on 2016/10/8.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {
    private Context context;

    public ImageRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ImageRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup group, final int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_explous_image, group, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MediaItemEntity temp = Value.images.get(position);
        holder.title.setText(temp.getName());
        holder.icon.setImageResource(temp.getIconID());
    }

    @Override
    public int getItemCount() {
        return Value.images.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView icon;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            icon = (ImageView) view.findViewById(R.id.icon);
        }
    }
}
