package com.explous.explous;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.explous.explous.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Explous extends AppCompatActivity {
    private RecyclerView explous;
    MenuItem temp;
    RecyclerAdapter adapter;
    List<Map<String, Object>> datas = new ArrayList<>();
    List<Map<String, Object>> datass = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explous);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_actionbar));
        initUI();
        init();
    }

    private void initUI(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    private void init(){

        for (int i = 0; i < 15; i ++) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("Title", "Title " + i);
            datas.add(temp);
        }

        for (int i = 0; i < 3; i ++) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("Type", i);
            temp.put("Title", "Title " + i);
            temp.put("Datas", datas);
            datass.add(temp);
        }

        explous = (RecyclerView)findViewById(R.id.explous);
        explous.setLayoutManager(new LinearLayoutManager(this));
        explous.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerAdapter(this, datass);
        explous.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        temp = menu.findItem(R.id.action_audio);
        temp.setVisible(false);
        //searchView=(SearchView)menu.findItem(R.id.action_search).getActionView();
        //searchViewListener();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_move:
                temp.setVisible(true);
                break;
            //case R.id.action_mediascanner:fileManager.scanImage();break;
            default:
                break;
        }
        return true;
    }
}
