package com.wxine.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

import com.example.wxine_online.wxine_online.R;
import com.wxine.android.model.Info;

import java.util.ArrayList;

/**
 * Created by zz on 2016/6/30.
 */
public class ManagementActivity extends AppCompatActivity {

    private ManagementAdapter mAdapter;
    private ArrayList<Info> list = null;
    private Toolbar ManageToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_main);

        ManageToolbar = (Toolbar) findViewById(R.id.ManageToolbar);

        RecyclerView ManageMainRecyclerView = (RecyclerView) findViewById(R.id.ManageMainRecyclerView);

        View a = LayoutInflater.from(this).inflate(R.layout.management_head, null);
        final ImageView ManagementCheckmark = (ImageView) a.findViewById(R.id.ManagementCheckmark);
        TableRow ManagementT1 = (TableRow) a.findViewById(R.id.ManagementT1);
        ManagementT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagementCheckmark.setVisibility(View.VISIBLE);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ManagementActivity.this);
        ManageMainRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ManagementAdapter(getApplicationContext(), list);
        ManageMainRecyclerView.setAdapter(mAdapter);

        ManageToolbar.setTitle("返回");
        setSupportActionBar(ManageToolbar);
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
