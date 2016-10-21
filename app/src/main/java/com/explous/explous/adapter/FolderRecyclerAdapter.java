package com.explous.explous.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.explous.explous.R;
import com.explous.explous.Value;
import com.explous.explous.entity.FolderItemEntity;


/**
 * Created by byte on 10/7/16.
 */

public class FolderRecyclerAdapter extends RecyclerView.Adapter<FolderRecyclerAdapter.ViewHolder> {
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnTouchListener onTouchListener;

    public FolderRecyclerAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(View view, int position);
    }

    public interface OnTouchListener {
        void onTouchListener(View view, MotionEvent motionEvent, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup group, final int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_explous_folder, group, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        FolderItemEntity temp = Value.folders.get(position);
        holder.title.setText(temp.getName());
        holder.icon.setImageResource(temp.getIcon());

        if (temp.getEditStatus())
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.grey));
        else
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(view, position);
            }
        });

       /* holder.container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                onTouchListener.onTouchListener(view, motionEvent, position);
                return true;
            }
        });*/

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
        return Value.folders.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CardView container;
        public ImageView icon;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            container = (CardView) view.findViewById(R.id.container);
            icon = (ImageView) view.findViewById(R.id.icon);
        }
    }
}
