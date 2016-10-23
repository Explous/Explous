package com.explous.explous;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.explous.explous.adapter.AudioRecyclerAdapter;
import com.explous.explous.adapter.DocumentRecyclerAdapter;
import com.explous.explous.adapter.FolderRecyclerAdapter;
import com.explous.explous.adapter.ImageRecyclerAdapter;
import com.explous.explous.adapter.RecyclerAdapter;
import com.explous.explous.adapter.VideoRecyclerAdapter;
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

    public static final int FOLDER = R.id.action_folder;
    public static final int IMAGE = R.id.action_image;
    public static final int VIDEO = R.id.action_video;
    public static final int AUDIO = R.id.action_audio;
    public static final int DOCUMENT = R.id.action_document;

    public static boolean isEditStatus = false;

    public static MenuInflater menuInflater;
    public static Context explous = null;
    public static RecyclerAdapter adapter;
    public static Toolbar toolbar;
    public static TextView themeHead;
    public static LinearLayoutManager linearLayoutManager;

    //User for check which file type should to be show on list
    public static List<Integer> types = new ArrayList<>();
    //User for control the menu items
    public static MenuItem[] menuItems = new MenuItem[5];

    public static FolderRecyclerAdapter folderRecyclerAdapter;
    public static List<FolderItemEntity> folders = new ArrayList<>();
    public static List<Integer> folderEditList = new ArrayList<>();

    public static ImageRecyclerAdapter imageRecyclerAdapter;
    public static List<MediaItemEntity> images = new ArrayList<>();
    public static List<Integer> imageEditList = new ArrayList<>();

    public static VideoRecyclerAdapter videoRecyclerAdapter;
    public static List<MediaItemEntity> videos = new ArrayList<>();
    public static List<Integer> videoEditList = new ArrayList<>();

    public static AudioRecyclerAdapter audioRecyclerAdapter;
    public static List<MediaItemEntity> audios = new ArrayList<>();
    public static List<Integer> audioEditList = new ArrayList<>();

    public static DocumentRecyclerAdapter documentRecyclerAdapter;
    public static List<DocumentItemEntity> documents = new ArrayList<>();
    public static List<Integer> documentEditList = new ArrayList<>();

    public static void startEditStatus() {
        isEditStatus = true;
        themeHead.setBackgroundColor(explous.getResources().getColor(R.color.grey));
        toolbar.getMenu().clear();
        menuInflater.inflate(R.menu.menu_eidt, Value.toolbar.getMenu());
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEditStatus();
            }
        });
    }

    public static void checkEditStatus() {
        if (folderEditList.size() > 0)
            return;
        if (imageEditList.size() > 0)
            return;
        if(videoEditList.size() > 0)
            return;
        if(audioEditList.size() > 0)
            return;
        if(documentEditList.size() > 0)
            return;
        clearEditStatus();
    }

    public static void clearEditStatus() {
        isEditStatus = false;
        themeHead.setBackgroundColor(explous.getResources().getColor(R.color.base));
        toolbar.getMenu().clear();
        toolbar.setNavigationIcon(null);
        menuInflater.inflate(R.menu.menu_tag, toolbar.getMenu());
        checkMenuItems();
        if (folderEditList.size() > 0) {
            for (Integer temp : folderEditList)
                folders.get(temp).setEditStatus(false);
            folderRecyclerAdapter.notifyDataSetChanged();
            folderEditList.clear();
        }
        if (imageEditList.size() > 0) {
            for (Integer temp : imageEditList)
                images.get(temp).setEditStatus(false);
            imageRecyclerAdapter.notifyDataSetChanged();
            imageEditList.clear();
        }
        if (videoEditList.size() > 0) {
            for (Integer temp : videoEditList)
                videos.get(temp).setEditStatus(false);
            videoRecyclerAdapter.notifyDataSetChanged();
            videoEditList.clear();
        }
        if (audioEditList.size() > 0) {
            for (Integer temp : audioEditList)
                audios.get(temp).setEditStatus(false);
            audioRecyclerAdapter.notifyDataSetChanged();
            audioEditList.clear();
        }
        if (documentEditList.size() > 0) {
            for (Integer temp : documentEditList)
                images.get(temp).setEditStatus(false);
            documentRecyclerAdapter.notifyDataSetChanged();
            documentEditList.clear();
        }

    }

    public static void clearList() {
        types.clear();
        folders.clear();
        images.clear();
        videos.clear();
        audios.clear();
        documents.clear();
    }

    public static void checkMenuItems() {
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
