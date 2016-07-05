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
 * Created by zz on 2016/6/23.
 */
public class ManagementAccount extends AppCompatActivity {

    private Toolbar ManagementToolbar;
    private TableRow ManagementT1;
    private TableRow ManagementAdd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_account);

        ManagementToolbar = (Toolbar) findViewById(R.id.ManagementToolbar);
        ManagementT1 = (TableRow) findViewById(R.id.ManagementT1);
        ManagementAdd = (TableRow) findViewById(R.id.ManagementAdd);

        ManagementT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "本账号很帅",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ManagementAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "添加账号吗",
                        Toast.LENGTH_SHORT).show();

            }
        });

        ManagementToolbar.setTitle("返回");
        setSupportActionBar(ManagementToolbar);
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
