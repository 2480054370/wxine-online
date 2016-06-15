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
public class SettingsAccount extends AppCompatActivity {

    private Toolbar AccountToolbar;
    private Button AccountUpdata;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_account);

        AccountToolbar = (Toolbar) findViewById(R.id.AccountToolbar);
        AccountUpdata = (Button) findViewById(R.id.AccountUpdata);

        AccountUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "登录认证确认更新",
                        Toast.LENGTH_SHORT).show();
            }
        });

        AccountToolbar.setTitle("返回");
        setSupportActionBar(AccountToolbar);
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
