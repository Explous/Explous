package com.explous.explous;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.explous.explous.adapter.RecyclerAdapter;
import com.explous.explous.entity.DocumentItemEntity;
import com.explous.explous.entity.FolderItemEntity;
import com.explous.explous.entity.MediaItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class Value{
    //Get file action
    public static final char ACTION_NEXT = '1';
    public static final char ACTION_PREV = '2';
    public static final char ACTION_NONE = '0';

    public static final char FOLDER = '0';
    public static final char IMAGE = '1';
    public static final char VIDEO = '2';
    public static final char AUDIO = '3';
    public static final char DOCUMENT = '4';

    public static RecyclerAdapter adapter;
    public static Toolbar toolbar;

    //User for check which file type should to be show on list
    public static List<Character> types = new ArrayList<>();
    //User for control the menu items
    public static MenuItem[] menuItems = new MenuItem[5];

    public static List<FolderItemEntity> folders = new ArrayList<>();
    public static List<MediaItemEntity> images = new ArrayList<>();
    public static List<MediaItemEntity> videos = new ArrayList<>();
    public static List<MediaItemEntity> audios = new ArrayList<>();
    public static List<DocumentItemEntity> documents = new ArrayList<>();

    public static void clearList() {
        types.clear();
        folders.clear();
        images.clear();
        videos.clear();
        audios.clear();
        documents.clear();
    }

    public static void checkTypes() {
        types.clear();
        if (folders.size() > 0) {
            menuItems[0].setVisible(true);
            types.add(FOLDER);
        } else
            menuItems[0].setVisible(false);
        if (images.size() > 0) {
            menuItems[1].setVisible(true);
            types.add(IMAGE);
        } else
            menuItems[1].setVisible(false);
        if (videos.size() > 0) {
            menuItems[2].setVisible(true);
            types.add(VIDEO);
        } else
            menuItems[2].setVisible(false);
        if (audios.size() > 0) {
            menuItems[3].setVisible(true);
            types.add(AUDIO);
        } else
            menuItems[3].setVisible(false);
        if (documents.size() > 0) {
            menuItems[4].setVisible(true);
            types.add(DOCUMENT);
        } else
            menuItems[4].setVisible(false);
    }

}
