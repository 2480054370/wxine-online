package com.wxine.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wxine_online.wxine_online.R;

/**
 * Created by zz on 2016/6/15.
 */
public class SettingsSecret extends AppCompatActivity {

    private Toolbar SecretToolbar;
    private Button SecretUpdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_secret);

        SecretToolbar = (Toolbar) findViewById(R.id.SecretToolbar);
        SecretUpdata = (Button) findViewById(R.id.SecretUpdata);

        SecretUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "隐私确认更新",
                        Toast.LENGTH_SHORT).show();
            }
        });

        SecretToolbar.setTitle("返回");
        setSupportActionBar(SecretToolbar);
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
