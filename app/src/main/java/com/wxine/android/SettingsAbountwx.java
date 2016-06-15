package com.wxine.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.wxine_online.wxine_online.R;

/**
 * Created by zz on 2016/6/15.
 */
public class SettingsAbountwx extends AppCompatActivity implements View.OnClickListener {

    private Toolbar AboutwxToolbar;
    private TableRow aboutUs;
    private TableRow CheckForUpdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_aboutwx);

        AboutwxToolbar = (Toolbar) findViewById(R.id.AboutwxToolbar);
        aboutUs = (TableRow) findViewById(R.id.aboutUs);
        CheckForUpdates = (TableRow) findViewById(R.id.CheckForUpdates);

        aboutUs.setOnClickListener(this);
        CheckForUpdates.setOnClickListener(this);

        AboutwxToolbar.setTitle("返回");
        setSupportActionBar(AboutwxToolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aboutUs:
                Toast.makeText(this, "关于我们", Toast.LENGTH_SHORT).show();
                break;
            case R.id.CheckForUpdates:
                Toast.makeText(this, "当前已经是最新版本", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
