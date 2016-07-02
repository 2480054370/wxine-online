package com.wxine.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wxine_online.wxine_online.R;
import com.wxine.android.auth.LoginActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Leeeeee on 2016/3/31.
 */
public class InitialActivity extends AppCompatActivity {
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MyApplication) getApplication();
        setContentView(R.layout.activity_initial);
        if (app.isLogin()) {
            final Intent intent = new Intent(InitialActivity.this, MainActivity.class);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            };
            timer.schedule(task, 1000 * 3);
            InitAsyncTask initAsyncTask = new InitAsyncTask();
            initAsyncTask.execute();

        } else {
            final Intent intent = new Intent(this, LoginActivity.class);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            };
            timer.schedule(task, 1000 * 3);

        }

    }

    class InitAsyncTask extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            try {
                CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
                URL uUrl = new URL("http://10.0.2.2:82/AndroidLogin/login.php");
                HttpURLConnection huc = (HttpURLConnection) uUrl.openConnection();
                huc.setRequestProperty("Cookie", app.getSessionId());
                huc.connect();
            }catch (MalformedURLException e){
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return null;
        }

    }
}
