package com.wxine.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.wxine_online.wxine_online.R;

/**
 * Created by zz on 2016/4/5.
 */
public class EventSignShow extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_qd_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.qd_show_toolbar);
        toolbar.setTitle("签到");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    }





