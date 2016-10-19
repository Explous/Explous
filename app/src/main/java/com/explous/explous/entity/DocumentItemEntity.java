package com.explous.explous.entity;

/**
 * Created by Administrator on 2016/10/18.
 */

public class DocumentItemEntity {
    private int position = 0;
    private int iconID = 0;
    private String size = null;
    private String name = null;

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
    public String getname() {
        return name;
    }
}
