package com.explous.explous.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.explous.explous.R;
import com.explous.explous.adapter.PopupNavigationAdapter;

/**
 * Created by Administrator on 2016/10/26.
 */

public class NavigationPopup {

    public NavigationPopup(Context context, View v) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_navigation, null);
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(context, 5));
        recycler.setItemAnimator(new DefaultItemAnimator());
        PopupNavigationAdapter adapter = new PopupNavigationAdapter(context);
        recycler.setAdapter(adapter);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(v);
    }
}
