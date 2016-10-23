package com.explous.explous.Init;

import android.content.Context;
import android.view.View;

import com.explous.explous.Value;
import com.explous.explous.adapter.AudioRecyclerAdapter;
import com.explous.explous.adapter.DocumentRecyclerAdapter;
import com.explous.explous.adapter.FolderRecyclerAdapter;
import com.explous.explous.adapter.ImageRecyclerAdapter;
import com.explous.explous.adapter.VideoRecyclerAdapter;
import com.explous.explous.fileoperate.GetFiles;

/**
 * Created by Administrator on 2016/10/21.
 */

public class InitAdapter {
    public InitAdapter(final Context context, final GetFiles funcGetFiles) {
        ////////////////////////////////////////////////////////////////////////////////////////////
        Value.folderRecyclerAdapter = new FolderRecyclerAdapter(context);
        Value.folderRecyclerAdapter.setOnItemClickListener(new FolderRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (Value.isEditStatus) {
                    if (Value.folders.get(position).getEditStatus()) {
                        Value.folders.get(position).setEditStatus(false);
                        //change the position from int to a object and remove
                        Value.folderEditList.remove((Integer)position);
                        Value.folderRecyclerAdapter.notifyItemChanged(position);
                        Value.checkEditStatus();
                        return;
                    }
                    Value.folders.get(position).setEditStatus(true);
                    Value.folderEditList.add(position);
                    Value.folderRecyclerAdapter.notifyItemChanged(position);
                    return;
                }

                View temp = Value.linearLayoutManager.getChildAt(0);
                //log the location for reback
                funcGetFiles.logPosition.add(Value.linearLayoutManager.getPosition(temp));
                funcGetFiles.logOffset.add(temp.getTop());
                //get the true position and open dir
                funcGetFiles.getFiles(Value.ACTION_NEXT, Value.folders.get(position).getPosition());
            }
        });

        Value.folderRecyclerAdapter.setOnItemLongClickListener(new FolderRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View view, int position) {
                if (Value.isEditStatus)
                    return;

                Value.startEditStatus();
                Value.folders.get(position).setEditStatus(true);
                Value.folderEditList.add(position);
                Value.folderRecyclerAdapter.notifyItemChanged(position);
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Value.imageRecyclerAdapter = new ImageRecyclerAdapter(context);

        ////////////////////////////////////////////////////////////////////////////////////////////
        Value.videoRecyclerAdapter = new VideoRecyclerAdapter(context);

        ////////////////////////////////////////////////////////////////////////////////////////////
        Value.audioRecyclerAdapter = new AudioRecyclerAdapter(context);

        ////////////////////////////////////////////////////////////////////////////////////////////
        Value.documentRecyclerAdapter = new DocumentRecyclerAdapter(context);
    }
}
