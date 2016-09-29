package com.explous.explous;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        init();
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
