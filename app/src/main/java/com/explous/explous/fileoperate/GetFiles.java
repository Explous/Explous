package com.explous.explous.fileoperate;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import com.explous.explous.R;
import com.explous.explous.Value;
import com.explous.explous.entity.DocumentItemEntity;
import com.explous.explous.entity.FolderItemEntity;
import com.explous.explous.entity.MediaItemEntity;

import java.io.File;

/**
 * Created by Administrator on 2016/10/14.
 */

public class GetFiles {
    private Context context;

    public File[] files;
    private File basePath = Environment.getExternalStorageDirectory();
    public File currentPath = Environment.getExternalStorageDirectory();
    ContentResolver cr;
    Cursor cursor;
    String[] projection = {MediaStore.Audio.Media._ID};
    String[] projectionThumb = {MediaStore.Images.Thumbnails.DATA};


    public GetFiles(Context context) {
        this.context = context;
        cr = context.getContentResolver();
    }

    public void getFiles(char action, int position) {
        if (action == Value.ACTION_NEXT) {
            currentPath = files[position];
        } else if (action == Value.ACTION_PREV) {
            currentPath = new File(currentPath.getPath().toString().substring(0, currentPath.getPath().toString().lastIndexOf('/')) + '/');
        }

        files = currentPath.listFiles();
        if (files == null)
            return;

        Value.clearList();

        for (int i = 0; i < files.length; i ++ ) {
            File temp = files[i];
            if (temp.isHidden())
                continue;
            if (temp.isDirectory()) {
                FolderItemEntity entity = new FolderItemEntity();
                entity.setIcon(R.mipmap.ic_launcher);
                entity.setName(temp.getName());
                entity.setPosition(i);
                Value.folders.add(entity);
            } else {
                getFileType(temp, i);
            }
        }

        Value.checkTypes();
        Value.adapter.notifyDataSetChanged();

        if (currentPath.getName().equals("0"))
            Value.toolbar.setTitle("SDCard");
        else
            Value.toolbar.setTitle(currentPath.getName());
    }

    private void getFileType(File file, int position) {
        int flag = file.getName().lastIndexOf('.');

        if (flag > 0) {
            String extension = file.getName().substring(flag + 1);
            String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);

            if (mime != null) {
                String mimeHead = mime.substring(0, mime.lastIndexOf('/'));
                if (mimeHead.equals("image")) {
                    MediaItemEntity entity = new MediaItemEntity();
                    entity.setName(file.getName());
                    entity.setIconID(R.mipmap.ic_launcher);
                    entity.setPosition(position);
                    Value.images.add(entity);
                    return;
                }
                if (mimeHead.equals("audio")) {
                    MediaItemEntity entity = new MediaItemEntity();
                    entity.setName(file.getName());
                    entity.setIconID(R.mipmap.ic_launcher);
                    entity.setPosition(position);
                    Value.audios.add(entity);
                    return;
                }
                if (mimeHead.equals("video")) {
                    MediaItemEntity entity = new MediaItemEntity();
                    entity.setName(file.getName());
                    entity.setIconID(R.mipmap.ic_launcher);
                    entity.setPosition(position);
                    Value.videos.add(entity);
                    return;
                }

                addUnkownDocument(file, position);
                return;
            }
        }
        addUnkownDocument(file, position);
    }

    private void addUnkownDocument(File file, int position) {
        DocumentItemEntity entity = new DocumentItemEntity();
        entity.setPosition(position);
        entity.setName(file.getName());
        entity.setIconID(R.mipmap.ic_launcher);
        Value.documents.add(entity);
    }

    public boolean checkPathTop() {
        if (currentPath.getPath().equals(basePath.getPath()))
            return true;
        else return false;
    }
}
