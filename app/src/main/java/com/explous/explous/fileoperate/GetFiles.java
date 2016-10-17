package com.explous.explous.fileoperate;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */

public class GetFiles {
    private Context context;

    public List<Map<String, Object>> folders = new ArrayList<>();
    public List<Map<String, Object>> images = new ArrayList<>();
    public List<Map<String, Object>> videos = new ArrayList<>();
    public List<Map<String, Object>> audios = new ArrayList<>();
    public List<Map<String, Object>> documents = new ArrayList<>();

    public File[] files;
    public File currentPath = Environment.getExternalStorageDirectory();;
    ContentResolver cr;
    Cursor cursor;
    String[] projection = {MediaStore.Audio.Media._ID};
    String[] projectionThumb = {MediaStore.Images.Thumbnails.DATA};


    public GetFiles(Context context) {
        this.context = context;
        cr = context.getContentResolver();
    }

    public void getFiles() {
        files = currentPath.listFiles();
        folders.clear();
        images.clear();
        videos.clear();
        audios.clear();
        documents.clear();

        for (int i = 0; i < files.length; i ++ ) {
            File temp = files[i];
            if (temp.isHidden())
                continue;
            Map<String, Object> map = new HashMap<>();
            if (temp.isDirectory()) {
                map.put("Type", "Show");
                map.put("Name", temp.getName());
                map.put("Position", i);
                folders.add(map);
            } else {
                getFileType(temp);
            }
        }

       /* for (File temp : files) {
            if (temp.isHidden())
                continue;
            Map<String, Object> map = new HashMap<>();
            if (temp.isDirectory()) {
                map.put("Type", "Show");
                map.put("Name", temp.getName());
                folders.add(map);
            } else {
                getFileType(temp);
            }
        }*/
    }

    private void getFileType(File file) {
        int flag = file.getName().lastIndexOf('.');
        Map<String, Object> map = new HashMap<>();
        map.put("Name", file.getName());
        if (flag > 0) {
            String extension = file.getName().substring(flag + 1);
            String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            if (mime != null) {
                String mimeHead = mime.substring(0, mime.lastIndexOf('/'));
                if (mimeHead.equals("image")) {
                    images.add(map);
                    return;
                }
                if (mimeHead.equals("audio")) {
                    audios.add(map);
                    return;
                }
                if (mimeHead.equals("video")) {
                    videos.add(map);
                    return;
                }

                documents.add(map);
                return;
            }
        }
        documents.add(map);
    }
}
