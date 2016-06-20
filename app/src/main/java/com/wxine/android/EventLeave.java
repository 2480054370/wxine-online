package com.wxine.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wxine_online.wxine_online.R;

import java.io.File;

/**
 * Created by zz on 2016/4/5.
 */
public class EventLeave extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_qj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.qj_toolbar);
        toolbar.setTitle("请假");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Button btnCancel = (Button)findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
           }
      });

      ImageView imageView = (ImageView)findViewById(R.id.im_qj);
       imageView.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
               Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                      "head.jpg")));
               startActivityForResult(intent2, 2);//采用ForResult打开
          }
       });
    }


}



