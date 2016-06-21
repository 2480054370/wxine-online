package com.wxine.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wxine_online.wxine_online.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wxine.android.model.Comment;
import com.wxine.android.model.Info;

import java.util.ArrayList;

/**
 * Created by zz on 2016/6/16.
 */
public class HomeDetails extends AppCompatActivity {

    private final ImageLoader imageLoader = ImageLoader.getInstance();
    private ImageView HomeDetailsUser;
    private TextView HomeDetailsName;
    private TextView HomeDetailsContent;
    private ImageView HomeDetailsImage;
    private ImageView HomeDetailsAgreeImg;
    private ImageView HomeDetailsShareImg;
    private ImageView HomeDetailsComment;
    private TextView HomeDetailsNum;
    private RecyclerView HomeDetailsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_details);

        HomeDetailsUser = (ImageView) findViewById(R.id.HomeDetailsUser);
        HomeDetailsName = (TextView) findViewById(R.id.HomeDetailsName);
        HomeDetailsContent = (TextView) findViewById(R.id.HomeDetailsContent);
        HomeDetailsImage = (ImageView) findViewById(R.id.HomeDetailsImage);
        HomeDetailsAgreeImg = (ImageView) findViewById(R.id.HomeDetailsAgreeImg);
        HomeDetailsShareImg = (ImageView) findViewById(R.id.HomeDetailsShareImg);
        HomeDetailsComment = (ImageView) findViewById(R.id.HomeDetailsComment);
        HomeDetailsNum = (TextView) findViewById(R.id.HomeDetailsNum);
        HomeDetailsRecyclerView = (RecyclerView) findViewById(R.id.HomeDetailsRecyclerView);
        HomeDetailsRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        HomeDetailsRecyclerView.setLayoutManager(mLayoutManager);

        Info info = (Info) getIntent().getSerializableExtra("info");
        String tag = getIntent().getStringExtra("tag");
        imageLoader.displayImage(info.getUser().getImage(), HomeDetailsUser);
        HomeDetailsName.setText(info.getUser().getName());
        HomeDetailsContent.setText(info.getCleancontent());
        imageLoader.displayImage(info.getImage(), HomeDetailsImage);
        HomeDetailsNum.setText(String.valueOf(info.getComments().size()));
        Log.i("InfoActivity.this", "aaa" + tag);

        final InfoCommentsAdapter mAdapter = new InfoCommentsAdapter(this, new ArrayList<Comment>(info.getComments()));
        //Log.v("==height======", String.valueOf(0));
        HomeDetailsRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
