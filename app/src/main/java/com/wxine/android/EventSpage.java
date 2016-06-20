package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.wxine_online.wxine_online.R;
import com.wxine.android.model.Comment;
import com.wxine.android.model.Info;
import com.wxine.android.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EventSpage extends AppCompatActivity {
    private Toolbar toolbar;
    private EventSpageAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private Button event_button;
    ArrayList<Info> list = new ArrayList<Info>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_s_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EventSpage.this,
                R.array.pass_type, R.layout.layout_drop_title);
        adapter.setDropDownViewResource(R.layout.layout_drop_list);

        Spinner mNavigationSpinner = new Spinner(getSupportActionBar().getThemedContext());
        mNavigationSpinner.setAdapter(adapter);
        mNavigationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        Intent intent = new Intent(EventSpage.this, EventSign.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(EventSpage.this, EventLeave.class);
                        startActivity(intent1);
                        break;
                    case 3:
                        Intent intent2 = new Intent(EventSpage.this, EventEvaluate.class);
                        startActivity(intent2);
                        break;
                    case 4:
                        Intent intent3 = new Intent(EventSpage.this, EventCheck.class);
                        startActivity(intent3);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toolbar.addView(mNavigationSpinner);
        View a = LayoutInflater.from(this).inflate(R.layout.event_s_item, null);
        datainit();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.EventSpage_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventSpageAdapter(this.getApplicationContext(), list);
        mRecyclerView.setAdapter(mAdapter);

        RecyclerView recyclerView = (RecyclerView)a.findViewById(R.id.pinglun_recycler_view);
        recyclerView.setHasFixedSize(true);
        FullyLinearLayoutManager mLayoutManager = new FullyLinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        final CommentsAdapter mAdapter = new CommentsAdapter(this, new ArrayList<Comment>(list.get(1).getComments()));
        //Log.v("==height======", String.valueOf(0));
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    public void datainit() {
        User user = new User();
        user.setName("aaa");
        user.setImage("http://img.teapic.com/thumbs/201305/28/101143gftcfpzwvukwqjsl.jpg.middle.jpg");
        user.setAddress("asgeq");
        Info info = new Info();
        info.setUser(user);
        info.setImage("http://icon.nipic.com/BannerPic/20160224/home/20160224113102.jpg");
        info.setAddress("aaa");
        info.setBrief("qiuehgqlehg");
        info.setCmcount(12);
        info.setContent("this is content");
        info.setTitle("this is title");
        info.setName("aaa");
        info.setCleancontent("this is c content1");

        Comment comment1 = new Comment();
        comment1.setContent("this is a comment1");
        comment1.setUser(user);
        comment1.setScore(60);
        comment1.setCtime(new java.sql.Timestamp(2014121123));
        Set<Comment> set1 = new HashSet<>();
        set1.add(comment1);

        Comment comment2 = new Comment();
        comment2.setContent("this is a comment1");
        comment2.setUser(user);
        comment2.setScore(60);
        comment2.setCtime(new java.sql.Timestamp(2014121123));
        set1.add(comment2);
        info.setComments(set1);

        Comment comment3 = new Comment();
        comment3.setContent("this is a comment1");
        comment3.setUser(user);
        comment3.setScore(60);
        comment3.setCtime(new java.sql.Timestamp(2014121123));
        set1.add(comment3);
        info.setComments(set1);


        User user2 = new User();
        user2.setName("bbb");
        user2.setImage("http://www.qqpk.cn/Article/UploadFiles/201112/20111228132051137.jpg");
        user2.setAddress("asgeq");
        Info info2 = new Info();
        info2.setUser(user2);
        info2.setImage("http://icon.nipic.com/BannerPic/20160224/home/20160224113102.jpg");
        info2.setAddress("aaa");
        info2.setBrief("this is brief");
        info2.setCmcount(12);
        info2.setTitle("this is title");
        info2.setName("bbb");
        info2.setContent("this is content2");
        info2.setCleancontent("this is c content");
        info2.setComments(set1);
        list.add(info);
        list.add(info2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        } else if (id == R.id.action_copylink) {
            return true;
        } else if (id == R.id.action_site) {
            return true;
        } else if (id == R.id.action_opinion) {
            return true;
        } else if (id == R.id.action_help) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
