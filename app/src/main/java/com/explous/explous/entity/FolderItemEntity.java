package com.explous.explous.entity;

/**
 * Created by Administrator on 2016/10/18.
 */

public class FolderItemEntity {
    private int icon;
    private String name;
    private int childCount;
    private int position;
    private boolean isEditStatus;

    public void setIcon(int icon) {
        this.icon = icon;
    }
    public int getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
    public int getChildCount() {
        return childCount;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }

    public void setEditStatus(boolean isEditStatus) {
        this.isEditStatus = isEditStatus;
    }
    public boolean getEditStatus(){
        return isEditStatus;
    }
}
