package com.explous.explous;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.explous.explous.adapter.RecyclerAdapter;
import com.explous.explous.fileoperate.GetFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Explous extends AppCompatActivity{
    private RecyclerView explous;
    GetFiles  funcGetFiles;

    LinearLayoutManager linearLayoutManager;
    Toolbar toolbar;
    MenuItem[] menuItems = new MenuItem[5];
    RecyclerAdapter adapter;
    List<Map<String, Object>> datas = new ArrayList<>();
    List<Integer> menuIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explous);

        initWindow();
        init();
    }

    private void initWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_actionbar));
    }

    private void init(){

        funcGetFiles = new GetFiles(this);

        Value.types.add(Value.FOLDER);

        menuIds.add(R.id.action_folder);
        menuIds.add(R.id.action_image);
        menuIds.add(R.id.action_video);
        menuIds.add(R.id.action_audio);
        menuIds.add(R.id.action_document);

        explous = (RecyclerView)findViewById(R.id.explous);
        linearLayoutManager = new LinearLayoutManager(this);
        explous.setLayoutManager(linearLayoutManager);
        explous.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerAdapter(this, datas, funcGetFiles);
        adapter.setChildDatas(funcGetFiles.folders, funcGetFiles.images, funcGetFiles.audios, funcGetFiles.videos, funcGetFiles.documents);
        explous.setAdapter(adapter);
        funcGetFiles.getFiles();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tag, menu);
        //temp.setVisible(false);
        menuItems[0] = menu.findItem(R.id.action_folder);
        menuItems[1] = menu.findItem(R.id.action_image);
        menuItems[2] = menu.findItem(R.id.action_video);
        menuItems[3] = menu.findItem(R.id.action_audio);
        menuItems[4] = menu.findItem(R.id.action_document);
        return true;
    }

    private int getMenuIdPosition(int id) {
        int position;
        for (position = 0; position < menuIds.size(); position ++) {
            Integer temp = menuIds.get(position);
            if (temp == id) break;
        }
        return position;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_folder:
                linearLayoutManager.scrollToPosition(0);
               // temp.setVisible(true);
                break;
            case R.id.action_audio:
                linearLayoutManager.scrollToPosition(getMenuIdPosition(R.id.action_audio));
                linearLayoutManager.scrollToPosition(getMenuIdPosition(R.id.action_audio));
                break;
            case R.id.action_image:
                linearLayoutManager.scrollToPosition(getMenuIdPosition(R.id.action_image));
                linearLayoutManager.scrollToPosition(getMenuIdPosition(R.id.action_image));
                break;
            case R.id.action_video:
                linearLayoutManager.scrollToPosition(getMenuIdPosition(R.id.action_video));
                linearLayoutManager.scrollToPosition(getMenuIdPosition(R.id.action_video));
                break;
            case R.id.action_document:
                linearLayoutManager.scrollToPosition(getMenuIdPosition(R.id.action_document));
                linearLayoutManager.scrollToPosition(getMenuIdPosition(R.id.action_document));
                break;
            default:
                break;
        }
        return true;
    }
}
