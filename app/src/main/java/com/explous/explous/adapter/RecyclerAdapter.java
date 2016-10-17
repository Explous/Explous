package com.explous.explous.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explous.explous.R;
import com.explous.explous.Value;
import com.explous.explous.fileoperate.GetFiles;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/30.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Map<String, Object>> datas;
    private Context context;
    private GetFiles funcGetFiles;
    private List<Map<String, Object>> folders;
    private List<Map<String, Object>> images;
    private List<Map<String, Object>> audios;
    private List<Map<String, Object>> videos;
    private List<Map<String, Object>> documents;


    public RecyclerAdapter(Context context, List datas, GetFiles funcGetFiles) {
        this.context = context;
        this.datas = datas;
        this.funcGetFiles = funcGetFiles;
    }

    public void setChildDatas(List<Map<String, Object>> folders, List<Map<String, Object>> images, List<Map<String, Object>> audios, List<Map<String, Object>> videos, List<Map<String, Object>> documents) {
        this.folders = folders;
        this.images = images;
        this.audios = audios;
        this.videos = videos;
        this.documents = documents;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup group, final int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_explous, group, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        char type = Value.types.get(i);
        switch (type) {
            case Value.FOLDER:
                holder.child.setLayoutManager(new GridLayoutManager(context, 2));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                FolderRecyclerAdapter folderRecyclerAdapter = new FolderRecyclerAdapter(context, folders);
                folderRecyclerAdapter.setOnItemClickListener(new FolderRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(View view, int position) {
                        int i = (int)folders.get(position).get("Position");
                        funcGetFiles.currentPath = funcGetFiles.files[i];
                        funcGetFiles.getFiles();
                        notifyDataSetChanged();
                    }
                });
                holder.child.setAdapter(folderRecyclerAdapter);
                break;
            case Value.IMAGE:
               /* holder.child.setLayoutManager(new GridLayoutManager(context, 3));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                ImageRecyclerAdapter adapter00 = new ImageRecyclerAdapter(context, data);
                holder.child.setAdapter(adapter00);*/
                break;
            case Value.AUDIO:
               /* holder.child.setLayoutManager(new LinearLayoutManager(context));
                holder.child.setItemAnimator(new DefaultItemAnimator());
                DocumentRecyclerAdapter adapter01 = new DocumentRecyclerAdapter(context, data);
                holder.child.setAdapter(adapter01);*/
                break;
            case Value.VIDEO:
                break;
            case Value.DOCUMENT:
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return Value.types.size();
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
