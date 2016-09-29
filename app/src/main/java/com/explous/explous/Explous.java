package com.explous.explous;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Explous extends AppCompatActivity {
    private ListView explous;
    SimpleAdapter adapter;
    List<Map<String, Object>> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explous);
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
        explous = (ListView)findViewById(R.id.explous);

        for (int i = 0; i < 15; i ++) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("Title", "" + i);
            datas.add(temp);
        }

        adapter = new SimpleAdapter(this, datas, R.layout.item_explous, new String[]{"Title"}, new int[]{R.id.title});
        explous.setAdapter(adapter);
    }
}
