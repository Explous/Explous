package com.explous.explous.entity;

/**
 * Created by Administrator on 2016/10/18.
 */

public class MediaItemEntity {
    private int position;
    private int iconID;
    private String size;
    private String name;
    private boolean isEditStatus;

    public boolean isEditStatus() {
        return isEditStatus;
    }
    public void setEditStatus(boolean editStatus) {
        isEditStatus = editStatus;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }
    public int getIconID() {
        return iconID;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
