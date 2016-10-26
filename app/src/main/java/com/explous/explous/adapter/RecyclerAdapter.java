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
import com.explous.explous.Value;


/**
 * Created by Administrator on 2016/9/30.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;


    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup group, final int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_explous, group, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        int type = Value.showTypes.get(i);
        switch (type) {
            case Value.FOLDER:
                holder.title.setText(R.string.folder);
                holder.child.setLayoutManager(new GridLayoutManager(context, 2));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                holder.child.setAdapter(Value.folderRecyclerAdapter);
                break;
            case Value.IMAGE:
                holder.title.setText(R.string.image);
                holder.child.setLayoutManager(new GridLayoutManager(context, 3));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                holder.child.setAdapter(Value.imageRecyclerAdapter);
                break;
            case Value.AUDIO:
                holder.title.setText(R.string.audio);
                holder.child.setLayoutManager(new GridLayoutManager(context, 3));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                holder.child.setAdapter(Value.audioRecyclerAdapter);
                break;
            case Value.VIDEO:
                holder.title.setText(R.string.video);
                holder.child.setLayoutManager(new GridLayoutManager(context, 3));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                holder.child.setAdapter(Value.videoRecyclerAdapter);
                break;
            case Value.DOCUMENT:
                holder.title.setText(R.string.document);
                holder.child.setLayoutManager(new LinearLayoutManager(context));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                holder.child.setAdapter(Value.documentRecyclerAdapter);
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return Value.showTypes.size();
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
