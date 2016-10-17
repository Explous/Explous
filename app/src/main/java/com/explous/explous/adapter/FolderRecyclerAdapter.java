package com.explous.explous.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explous.explous.R;

import java.util.List;
import java.util.Map;

/**
 * Created by byte on 10/7/16.
 */

public class FolderRecyclerAdapter extends RecyclerView.Adapter<FolderRecyclerAdapter.ViewHolder> {
    public List<Map<String, Object>> datas;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public FolderRecyclerAdapter(Context context, List datas) {
        this.context = context;
        this.datas = datas;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup group, final int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_explous_folder, group, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Map<String, Object> temp = datas.get(position);
        holder.title.setText(temp.get("Name").toString());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(view, position);
            }
        });

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickListener.onItemLongClickListener(view, position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CardView container;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            container = (CardView) view.findViewById(R.id.container);
        }
    }
}
