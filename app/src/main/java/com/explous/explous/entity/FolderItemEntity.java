package com.explous.explous.entity;

/**
 * Created by Administrator on 2016/10/18.
 */

public class FolderItemEntity {
    private int icon;
    private String name;
    private int count;
    private int position;

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

    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }
}
