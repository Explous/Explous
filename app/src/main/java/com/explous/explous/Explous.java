package com.explous.explous;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.explous.explous.Init.InitAdapter;
import com.explous.explous.adapter.RecyclerAdapter;
import com.explous.explous.dialog.NavigationPopup;
import com.explous.explous.fileoperate.GetFiles;

import static com.explous.explous.Value.adapter;
import static com.explous.explous.Value.linearLayoutManager;
import static com.explous.explous.Value.toolbar;

public class Explous extends AppCompatActivity {
    private RecyclerView explous;
    GetFiles funcGetFiles;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explous);

        initPermission();
        initWindow();
        init();
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
            }
        }
    }

    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_actionbar));
        Value.themeHead = (TextView) findViewById(R.id.theme_head);
    }

    private void init() {
        Value.explous = this;
        Value.menuInflater = getMenuInflater();
        funcGetFiles = new GetFiles(this);
        new InitAdapter(this, funcGetFiles);

        explous = (RecyclerView) findViewById(R.id.explous);
        linearLayoutManager = new LinearLayoutManager(this);
        explous.setLayoutManager(linearLayoutManager);
        explous.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerAdapter(this);
        explous.setAdapter(adapter);

        funcGetFiles.getFiles(Value.ACTION_NONE, 0);
    }

    private int getMenuIdPosition(int id) {
        int position;
        for (position = 0; position < Value.showTypes.size(); position++) {
            Integer temp = Value.showTypes.get(position);
            if (temp == id) break;
        }
        return position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_base, menu);
        //Value.initMenuItems();
        //Ensure the meuitems have benn init and than get files
        //funcGetFiles.getFiles(Value.ACTION_NONE, 0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_go:
                new NavigationPopup(Explous.this, toolbar);
                break;
            case R.id.action_mark:
                Value.markPath = funcGetFiles.currentPath;
                break;
            case R.id.action_folder:
                linearLayoutManager.scrollToPositionWithOffset(0, 0);
                break;
            case R.id.action_audio:
                linearLayoutManager.scrollToPositionWithOffset(getMenuIdPosition(R.id.action_audio), 0);
                break;
            case R.id.action_image:
                linearLayoutManager.scrollToPositionWithOffset(getMenuIdPosition(R.id.action_image), 0);
                break;
            case R.id.action_video:
                linearLayoutManager.scrollToPositionWithOffset(getMenuIdPosition(R.id.action_video), 0);
                break;
            case R.id.action_document:
                linearLayoutManager.scrollToPositionWithOffset(getMenuIdPosition(R.id.action_document), 0);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (Value.isEditStatus ) {
            Value.clearEditStatus();
            return;
        }

        if (funcGetFiles.checkPathTop()) {
            Value.clearEditStatus();
            finish();
            return;
        }

        funcGetFiles.getFiles(Value.ACTION_PREV, 0);
        int position = funcGetFiles.logPosition.size() - 1;
        if (position >= 0) {
            //reback the location from log and delete the used loaction log
            linearLayoutManager.scrollToPositionWithOffset(funcGetFiles.logPosition.get(position), funcGetFiles.logOffset.get(position));
            funcGetFiles.logPosition.remove(position);
            funcGetFiles.logOffset.remove(position);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    funcGetFiles.getFiles(Value.ACTION_NONE, 0);
                    return;
                }
                // Permission Denied
                Toast.makeText(Explous.this, "WRITE_CONTACTS Denied!App will not running……", Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
